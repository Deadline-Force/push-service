package com.deadlineforce.backend.controller;

import com.deadlineforce.backend.model.auth.AuthModel;
import com.deadlineforce.backend.model.auth.AuthUser;
import com.deadlineforce.backend.model.auth.JWT;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Tag(name = "AuthController")
public class AuthController {
    private final AuthModel authModel;

    public AuthController(AuthModel authModel) {
        this.authModel = authModel;
    }

    @PostMapping("/signup")
    public HttpStatus signup(@RequestBody AuthUser user) {
        this.authModel.signup(user);
        return HttpStatus.OK;
    }

    @PostMapping("/login")
    public JWT login(@RequestBody AuthUser user) {
        String JWT = this.authModel.createJWT(user);
        return new JWT(JWT);
    }

    @PostMapping("/logout")
    public HttpStatus logout() {
        return HttpStatus.NOT_IMPLEMENTED;
    }
}
