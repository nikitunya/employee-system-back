package com.example.employee.services;

import com.example.employee.Utils.Role;
import com.example.employee.entity.UserEntity;
import com.example.employee.repasitory.UserRepository;
import com.example.employee.requests.AuthenticationRequest;
import com.example.employee.requests.RegisterRequest;
import com.example.employee.response.AuthenticationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtServiceImpl jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        UserEntity user = new UserEntity();
        user.setFirstname(request.getFirstname());
        user.setLastname(request.getLastname());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.USER);

        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) throws Exception {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        var user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new Exception("No Such User"));
        var jwtToken = jwtService.generateToken((UserDetails) user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }
}
