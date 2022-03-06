package com.metaprofile.api.security.services;

import com.metaprofile.api.security.exceptions.SecurityAuthorizationUserUndefinedException;
import com.metaprofile.api.security.exceptions.SecurityLoginSessionNotFound;
import com.metaprofile.api.security.jwt.JwtUtils;
import com.metaprofile.api.security.models.User;
import com.metaprofile.api.security.models.UserDetailsImpl;
import com.metaprofile.api.security.models.UserLoginSession;
import com.metaprofile.api.security.repositories.UserLoginSessionsRepository;
import com.metaprofile.api.security.repositories.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserLoginSessionsServiceImpl  implements UserLoginSessionsService{

    private final UserLoginSessionsRepository userLoginSessionsRepository;
    private final UserRepository userRepository;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;

    public UserLoginSessionsServiceImpl(UserLoginSessionsRepository userLoginSessionsRepository, UserRepository userRepository, JwtUtils jwtUtils, AuthenticationManager authenticationManager) {
        this.userLoginSessionsRepository = userLoginSessionsRepository;
        this.userRepository = userRepository;
        this.jwtUtils = jwtUtils;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public UserLoginSession get(Long sessionId) throws SecurityLoginSessionNotFound {
        return userLoginSessionsRepository.findById(sessionId).orElseThrow(SecurityLoginSessionNotFound::new);
    }

    @Override
    public UserLoginSession get(String token) throws SecurityLoginSessionNotFound {
        return userLoginSessionsRepository.findByToken(token).orElseThrow(SecurityLoginSessionNotFound::new);
    }

    @Override
    public Boolean close(Long sessionId) throws SecurityLoginSessionNotFound {
        UserLoginSession loginSession = this.get(sessionId);
        loginSession.setStatus(2);
        loginSession.updateEditTime();
        userLoginSessionsRepository.save(loginSession);
        return true;
    }

    @Override
    public Boolean close(String token) throws SecurityLoginSessionNotFound {
        UserLoginSession loginSession = this.get(token);
        loginSession.setStatus(2);
        loginSession.updateEditTime();
        userLoginSessionsRepository.save(loginSession);
        return true;
    }

    /**
     * Создает сессию авторизации
     * @param emailOrMetaId
     * @param password
     * @return
     */
    @Override
    public UserLoginSession create(String emailOrMetaId, String password, String ip, String agent, String country, String fingerPrint) {
        // Поиск пользователя
        User user = userRepository.findByEmailOrMetaId(emailOrMetaId)
                .orElseThrow(SecurityAuthorizationUserUndefinedException::new);

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), password));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        UserLoginSession userLoginSession = new UserLoginSession();
        userLoginSession.setToken(jwt);
        userLoginSession.setUser(user);
        userLoginSession.setCreateTime(Timestamp.from(Instant.now()));
        userLoginSession.setExpiredTime(Timestamp.from(Instant.now().plusMillis(86400000)));
        userLoginSession.setStatus(1);
        userLoginSession.setIp(ip);
        userLoginSession.setAgent(agent);
        userLoginSession.setCountry(country);
        userLoginSession.setFp(fingerPrint);

        return userLoginSessionsRepository.save(userLoginSession);
    }
}
