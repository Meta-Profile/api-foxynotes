package com.metaprofile.api.security.controllers;

import com.metaprofile.api.core.ControllerResponse;
import com.metaprofile.api.security.models.UserDetailsImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Users", description = "Работа с пользователями")
@RestController
@RequestMapping("/v1/user")
public class UserController {

    @Operation(summary = "Возвращает текущего пользователя по токену авторизации")
    @GetMapping("/me")
    @PreAuthorize("hasRole('USER')")
    public ControllerResponse<UserDetailsImpl> userAccess(Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        return ControllerResponse.ok(userDetails);
    }

}
