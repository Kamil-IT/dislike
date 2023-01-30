package com.dislike.backend.security.auth;

import com.dislike.backend.persistance.user.UserRepository;
import com.dislike.backend.persistance.user.model.UserPersistence;
import com.dislike.backend.security.api.model.AuthenticationRequest;
import com.dislike.backend.security.api.model.AuthenticationResponse;
import com.dislike.backend.security.api.model.RegisterRequest;
import com.dislike.backend.security.config.JwtService;
import com.dislike.backend.security.excpetion.UserExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        var user = UserPersistence.builder()
                .username(request.getLogin())
                .password(passwordEncoder.encode(request.getPassword()))
                .accountNonExpired(true)
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .enabled(true)
//    TODO:            .roles(Role.USER)
                .build();
        repository.findById(request.getLogin()).ifPresentOrElse(
                (u) -> {throw new UserExistsException("User already registered: " + user);},
                () -> repository.save(user));
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getLogin(),
                        request.getPassword()
                )
        );
        var user = repository.findFirstByUsername(request.getLogin())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
