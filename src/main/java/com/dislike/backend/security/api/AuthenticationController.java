package com.dislike.backend.security.api;

import com.dislike.backend.security.api.model.AuthenticationRequest;
import com.dislike.backend.security.api.model.AuthenticationResponse;
import com.dislike.backend.security.api.model.UserExists;
import com.dislike.backend.security.auth.AuthenticationService;
import com.dislike.backend.security.api.model.RegisterRequest;
import com.dislike.backend.security.excpetion.UserExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(service.authenticate(request));
    }

    @ExceptionHandler({ UserExistsException.class })
    public ResponseEntity<UserExists> handleException(UserExistsException e) {
        UserExists response = new UserExists(e.getMessage(), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(response, response.status());
    }

}
