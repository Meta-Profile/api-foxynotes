package com.metaprofile.api.security.controllers;

import com.metaprofile.api.core.ControllerResponse;
import com.metaprofile.api.security.exceptions.AuthEmailIsTakenException;
import com.metaprofile.api.security.exceptions.AuthRoleNotFoundException;
import com.metaprofile.api.security.exceptions.AuthUsernameIsTakenException;
import com.metaprofile.api.security.models.Role;
import com.metaprofile.api.security.models.User;
import com.metaprofile.api.security.models.UserRoleName;
import com.metaprofile.api.security.payloads.request.LoginRequest;
import com.metaprofile.api.security.payloads.request.SignupRequest;
import com.metaprofile.api.security.payloads.response.JwtResponse;
import com.metaprofile.api.security.repositories.RoleRepository;
import com.metaprofile.api.security.repositories.UserRepository;
import com.metaprofile.api.security.services.AuthService;
import com.metaprofile.api.security.services.UserLoginSessionsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

@RestController
@RequestMapping("/v1/auth")
@Tag(name = "Auth", description = "Авторизация и регистрация пользователей")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @Operation(summary = "Выполняет авторизацию пользователя и возаращет JWT токен")
    @PostMapping("/signin")
    public ControllerResponse<JwtResponse> authenticateUser(
            @Valid @RequestBody LoginRequest loginRequest,
            HttpServletRequest request,
            Locale locale
    ) {
        String remoteAddr = request.getHeader("X-FORWARDED-FOR");
        if (remoteAddr == null || "".equals(remoteAddr)) remoteAddr = request.getRemoteAddr();

        String agent = request.getHeader("User-Agent");
        String country = locale.getLanguage();
        String fp = request.getHeader("fp");

        // Возвращает ответ пользователя
        return ControllerResponse.ok(authService.signin(loginRequest.getUsername(), loginRequest.getPassword(), remoteAddr, agent, country, fp));
    }

    @Operation(summary = "Создает нового пользователя")
    @PostMapping("/signup")
    public ControllerResponse<User> registerUser(
            @Valid @RequestBody SignupRequest signUpRequest,
            HttpServletRequest request,
            Locale locale
    )  {
        String remoteAddr = request.getHeader("X-FORWARDED-FOR");
        if (remoteAddr == null || "".equals(remoteAddr)) remoteAddr = request.getRemoteAddr();

        String regAgent = request.getHeader("User-Agent");
        String regCountry = locale.getLanguage();
        String fp = request.getHeader("fp");

        return ControllerResponse.ok(authService.signup(
                signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                signUpRequest.getPassword(),
                remoteAddr,
                regAgent,
                regCountry,
                fp
        ));
    }
}

