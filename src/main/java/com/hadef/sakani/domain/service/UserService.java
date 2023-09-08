package com.hadef.sakani.domain.service;

import com.hadef.sakani.domain.entity.User;
import com.hadef.sakani.domain.value.dto.UserDTO;

import java.util.List;

public interface UserService {

    User searchByEmail(String email);
    List<UserDTO> findAllUsers();
    UserDTO findUserById(Long id);
    UserDTO createUser(UserDTO userDto, String password);
    void updateUser(Long id, UserDTO userDto, String password);
    void removeUserById(Long id);
}
