package com.metaprofile.api.security.services;

import com.metaprofile.api.core.ControllerResponse;
import com.metaprofile.api.security.exceptions.*;
import com.metaprofile.api.security.models.Role;
import com.metaprofile.api.security.models.User;
import com.metaprofile.api.security.models.UserLoginSession;
import com.metaprofile.api.security.models.UserRoleName;
import com.metaprofile.api.security.payloads.response.JwtResponse;
import com.metaprofile.api.security.repositories.RoleRepository;
import com.metaprofile.api.security.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserLoginSessionsService userLoginSessionsService;
    private final PasswordEncoder encoder;


    public AuthServiceImpl(UserRepository userRepository, RoleRepository roleRepository, UserLoginSessionsService userLoginSessionsService, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userLoginSessionsService = userLoginSessionsService;
        this.encoder = encoder;
    }

    @Override
    public JwtResponse signin(String emailOrMetaId, String password, String ip, String agent, String country, String fingerPrint) throws SecurityAuthorizationUserUndefinedException {

        UserLoginSession userLoginSession = userLoginSessionsService.create(emailOrMetaId, password, ip, agent, country, fingerPrint);
        User user = userLoginSession.getUser();

        JwtResponse jwtResponse = new JwtResponse(userLoginSession.getToken(),
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getAvatar());
        return jwtResponse;
    }

    @Override
    public User signup(String metaId, String email, String password, String regIp, String regAgent, String regCountry, String regFingerPrint) throws SecurityEmailAlreadyTakenException, SecurityMetaIdAlreadyTakenException {

        // Проверки на нахождение такого же пользователя
        if (userRepository.existsByUsername(metaId))  throw new SecurityMetaIdAlreadyTakenException(metaId);
        if (userRepository.existsByEmail(email))  throw new SecurityEmailAlreadyTakenException(metaId);

        // Create new user's account
        User user = new User(metaId, email, encoder.encode(password));

        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.findByName(UserRoleName.ROLE_USER).orElseThrow(() -> new SecurityRoleNotFoundException(UserRoleName.ROLE_USER)));
        roles.add(roleRepository.findByName(UserRoleName.ROLE_USER).orElseThrow(() -> new SecurityRoleNotFoundException(UserRoleName.ROLE_FILES_UPLOAD)));
        roles.add(roleRepository.findByName(UserRoleName.ROLE_USER).orElseThrow(() -> new SecurityRoleNotFoundException(UserRoleName.ROLE_META_PROFILES_CREATE)));
        roles.add(roleRepository.findByName(UserRoleName.ROLE_USER).orElseThrow(() -> new SecurityRoleNotFoundException(UserRoleName.ROLE_META_PROFILES_EDIT)));
        roles.add(roleRepository.findByName(UserRoleName.ROLE_USER).orElseThrow(() -> new SecurityRoleNotFoundException(UserRoleName.ROLE_META_PROFILES_LOCAL_VIEW)));
        roles.add(roleRepository.findByName(UserRoleName.ROLE_USER).orElseThrow(() -> new SecurityRoleNotFoundException(UserRoleName.ROLE_META_PROFILES_GLOBAL_VIEW)));
        roles.add(roleRepository.findByName(UserRoleName.ROLE_USER).orElseThrow(() -> new SecurityRoleNotFoundException(UserRoleName.ROLE_META_PROFILES_PUBLIC_VIEW)));
        user.setRoles(roles);

        user.setRegAgent(regAgent);
        user.setRegCountry(regCountry);
        user.setRegFp(regFingerPrint);
        user.setRegIp(regIp);

        // Создание пользователя
        return userRepository.save(user);
    }
}
