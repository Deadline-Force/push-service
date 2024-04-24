package com.deadlineforce.backend.model.auth;

import com.deadlineforce.backend.entity.User;
import com.deadlineforce.backend.repository.UserRepository;
import com.deadlineforce.backend.security.jwt.JWTUtils;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class AuthModel {
    private final UserRepository userRepository;
    private final JWTUtils JWTUtils;

    public AuthModel(UserRepository userRepository, JWTUtils JWTUtils) {
        this.userRepository = userRepository;
        this.JWTUtils = JWTUtils;
    }

    public void signup(AuthUser authUser) {
        User user = new User(authUser.username(), authUser.login(), authUser.password());
        this.userRepository.save(user);
    }

    public String createJWT(AuthUser authUser) {
        User user = this.userRepository.findUserByLogin(authUser.login()).orElse(null);
        if (user != null && user.getPassword().equals(authUser.password())) {
            return JWTUtils
                    .createJWT(user.getId(), user.getUsername(), user.getRole().name(), 10, TimeUnit.MINUTES);
        } else {
            throw new AuthException();
        }
    }
}
