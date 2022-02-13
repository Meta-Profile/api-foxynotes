package com.metaprofile.api.controllers;

import com.metaprofile.api.core.ControllerResponse;
import com.metaprofile.api.security.exceptions.AuthEmailIsTakenException;
import com.metaprofile.api.security.exceptions.AuthRoleNotFoundException;
import com.metaprofile.api.security.exceptions.AuthUsernameIsTakenException;
import com.metaprofile.api.security.models.Role;
import com.metaprofile.api.security.models.User;
import com.metaprofile.api.security.models.UserDetailsImpl;
import com.metaprofile.api.security.models.UserRoleName;
import com.metaprofile.api.payloads.request.LoginRequest;
import com.metaprofile.api.payloads.request.SignupRequest;
import com.metaprofile.api.payloads.response.JwtResponse;
import com.metaprofile.api.payloads.response.MessageResponse;
import com.metaprofile.api.repository.RoleRepository;
import com.metaprofile.api.repository.UserRepository;
import com.metaprofile.api.security.jwt.JwtUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder encoder;
    private final JwtUtils jwtUtils;

    public AuthController(AuthenticationManager authenticationManager, UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder encoder, JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        // Возвращает ответ пользователя
        return new ControllerResponse<>(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles), 200).response();
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            throw new AuthUsernameIsTakenException(signUpRequest.getUsername());
        }
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new AuthEmailIsTakenException(signUpRequest.getEmail());
        }
        // Create new user's account
        User user = new User(signUpRequest.getUsername(), signUpRequest.getEmail(), encoder.encode(signUpRequest.getPassword()));
        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();


        Role userRole = roleRepository.findByName(UserRoleName.ROLE_USER)
                .orElseThrow(() -> new AuthRoleNotFoundException(UserRoleName.ROLE_USER));
        roles.add(userRole);

        Role filesUploadRole = roleRepository.findByName(UserRoleName.ROLE_FILES_UPLOAD)
                .orElseThrow(() -> new AuthRoleNotFoundException(UserRoleName.ROLE_FILES_UPLOAD));
        roles.add(filesUploadRole);

        user.setRoles(roles);
        userRepository.save(user);

        return new ControllerResponse<Boolean>(true, 200).response();
    }
}

