package ru.job4j.media.security.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.job4j.media.security.dto.request.LoginRequestDTO;
import ru.job4j.media.security.dto.request.SignupRequestDTO;
import ru.job4j.media.security.dto.response.JwtResponseDTO;
import ru.job4j.media.security.dto.response.MessageResponseDTO;
import ru.job4j.media.security.dto.response.RegisterDTO;
import ru.job4j.media.security.jwt.JwtUtils;
import ru.job4j.media.security.userdetails.UserDetailsImpl;
import ru.job4j.media.security.service.UserSecurityService;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    private final UserSecurityService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    @PostMapping("/signup")
    public ResponseEntity<MessageResponseDTO> registerUser(@Valid @RequestBody SignupRequestDTO signUpRequest) {
        RegisterDTO registerDTO = userService.signUp(signUpRequest);
        return ResponseEntity.status(registerDTO.getStatus())
                .body(new MessageResponseDTO(registerDTO.getMessage()));
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtResponseDTO> authenticateUser(@Valid @RequestBody LoginRequestDTO loginRequestDTO) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequestDTO.getUsername(), loginRequestDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();
        return ResponseEntity
                .ok(new JwtResponseDTO(jwt, userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(), roles));
    }
}