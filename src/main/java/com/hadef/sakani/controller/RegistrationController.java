package com.hadef.sakani.controller;

import com.hadef.sakani.domain.service.UserService;
import com.hadef.sakani.domain.value.dto.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/register")
public class RegistrationController {

    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserDTO> addRegistration(
            @RequestHeader(name = "CHN") @Valid String chn,
            @RequestHeader(name = "LNG") @Valid String lang,
            @RequestBody UserDTO dto){
        UserDTO userDTO = userService.createUser(dto, "test");
        return ResponseEntity.ok(userDTO);
    }
}
