package com.example.employee.controller;

import com.example.employee.Utils.Url;
import com.example.employee.requests.AuthenticationRequest;
import com.example.employee.requests.RegisterRequest;
import com.example.employee.response.AuthenticationResponse;
import com.example.employee.services.AuthenticationServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "https://localhost:3000")
@RestController
@RequestMapping("/employee-system")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationServiceImpl authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request){
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) throws Exception {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
}
