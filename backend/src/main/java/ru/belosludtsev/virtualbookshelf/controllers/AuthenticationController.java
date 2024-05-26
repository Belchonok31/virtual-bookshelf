package ru.belosludtsev.virtualbookshelf.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.belosludtsev.virtualbookshelf.dao.request.SignInRequest;
import ru.belosludtsev.virtualbookshelf.dao.request.SignUpRequest;
import ru.belosludtsev.virtualbookshelf.dao.response.JWTAuthenticationResponse;
import ru.belosludtsev.virtualbookshelf.services.AuthenticationServices;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationServices authenticationServices;

    @PostMapping("/signUp")
    public ResponseEntity<JWTAuthenticationResponse> signUp(@RequestBody SignUpRequest request) {
        return ResponseEntity.ok(authenticationServices.signUp(request));
    }
    @PostMapping("/signIn")
    public ResponseEntity<JWTAuthenticationResponse> signIn(@RequestBody SignInRequest request) {
        return ResponseEntity.ok(authenticationServices.signIn(request));
    }
}
