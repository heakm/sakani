package com.hadef.sakani.controller;

import com.hadef.sakani.domain.entity.AuthRequest;
import com.hadef.sakani.domain.entity.AuthResponse;
import com.hadef.sakani.domain.entity.User;
import com.hadef.sakani.domain.service.UserService;
import com.hadef.sakani.domain.service.impl.ApplicationUserDetailsService;
import com.hadef.sakani.domain.value.FailureEnum;
import com.hadef.sakani.domain.value.dto.UserDTO;
import com.hadef.sakani.exceptions.BadRequestException;
import com.hadef.sakani.exceptions.CustomException;
import com.hadef.sakani.util.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/auth")
public class RegistrationController {

    private final UserService userService;
    private final String serviceName;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtTokenUtil;
    private final ApplicationUserDetailsService userDetailsService;


    public RegistrationController(UserService userService, AuthenticationManager authenticationManager, JwtUtil jwtTokenUtil, ApplicationUserDetailsService userDetailsService) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailsService = userDetailsService;
        this.serviceName = this.getClass().getName();
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> addRegistration(
            @RequestHeader(name = "CHN") @Valid String chn,
            @RequestHeader(name = "LNG") @Valid String lang,
            @RequestBody UserDTO dto){
        UserDTO userDTO = userService.createUser(dto, "test");
        return ResponseEntity.ok(userDTO);
    }
    @PostMapping("/authenticate")
    @ResponseStatus(HttpStatus.CREATED)
    public AuthResponse authenticate(
            @RequestHeader(name = "CHN") @Valid String chn,
            @RequestHeader(name = "LNG") @Valid String lang,
            @RequestBody AuthRequest req){

        try {
            User user = userDetailsService.authenticate(req.getEmail(), req.getPassword());
            UserDetails userDetails = userDetailsService.loadUserByUsername(user.getEmail());

            String jwt = jwtTokenUtil.generateToken(userDetails);

            return new AuthResponse(jwt);
        } catch (BadCredentialsException e) {
            throw new CustomException("Incorrect username or password", HttpStatus.UNAUTHORIZED.value(),serviceName);
        }
    }
    private void validateBindingResult(BindingResult bindingResult, FailureEnum failureEnum){
        if(bindingResult.hasErrors()){
            throw new BadRequestException(bindingResult.getFieldErrors().stream()
                    .map(FieldError::getDefaultMessage)
                    .collect(Collectors.joining(","))
                    ,failureEnum,serviceName);
        }
    }

}
