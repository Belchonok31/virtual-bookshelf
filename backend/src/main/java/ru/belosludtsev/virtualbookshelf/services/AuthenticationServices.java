package ru.belosludtsev.virtualbookshelf.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.belosludtsev.virtualbookshelf.dao.request.SignInRequest;
import ru.belosludtsev.virtualbookshelf.dao.request.SignUpRequest;
import ru.belosludtsev.virtualbookshelf.dao.response.JWTAuthenticationResponse;
import ru.belosludtsev.virtualbookshelf.entities.MeUserDetails;
import ru.belosludtsev.virtualbookshelf.entities.Role;
import ru.belosludtsev.virtualbookshelf.entities.User;
import ru.belosludtsev.virtualbookshelf.repositories.UserRepositories;

@Service
@RequiredArgsConstructor
public class AuthenticationServices {

    private final UserRepositories userRepositories;

    private final JwtServices jwtServices;

    private final AuthenticationManager authenticationManager;

    private final PasswordEncoder passwordEncoder;

    public JWTAuthenticationResponse signUp(SignUpRequest request) {
        var user = User.builder()
                .firstName(request.getFirstName())
                .middleName(request.getMiddleName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.ROLE_USER)
                .build();
        userRepositories.save(user);
        var jwt = jwtServices.generateToken(new MeUserDetails(user));
        return JWTAuthenticationResponse.builder()
                .token(jwt)
                .build();
    }

    public JWTAuthenticationResponse signIn(SignInRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var user = userRepositories.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));
        var jwt = jwtServices.generateToken(new MeUserDetails(user));
        return JWTAuthenticationResponse.builder()
                .token(jwt)
                .build();
    }
}
