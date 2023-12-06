package com.hadef.sakani.domain.service.impl;

import com.hadef.sakani.domain.entity.User;
import com.hadef.sakani.domain.repository.UserRepository;
import com.hadef.sakani.domain.service.UserService;
import com.hadef.sakani.domain.value.dto.UserDTO;
import com.hadef.sakani.exceptions.CustomException;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper mapper;
    private final String serviceName;
    public UserServiceImpl(UserRepository userRepository, ModelMapper mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.serviceName = this.getClass().getName();
    }

    public User searchByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public List<UserDTO> findAllUsers() {
        List<User> usersList = userRepository.findAll();

        return usersList
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public UserDTO findUserById(final Long id) {
        var user = userRepository
                .findById(id);
        if(user.isPresent()){
            return convertToDto(user.get());
        }
        throw new CustomException("User by id " + id + " was not found");
    }

    @Override
    public UserDTO createUser(UserDTO userDto, String password) {
        User user = convertToEntity(userDto);
        List<String> errors = new ArrayList<>();
        if (password.isBlank()) {
            errors.add("Password is required");
        }

        boolean existsEmail = userRepository.existsByEmail(user.getEmail());
        if (existsEmail) {
            errors.add("Email is already available");
        }
        if(!errors.isEmpty()){
            throw new CustomException(errors.toString(), HttpStatus.INTERNAL_SERVER_ERROR.value(),serviceName);
        }
        byte[] salt = createSalt();
        byte[] hashedPassword = createPasswordHash(password, salt);

        user.setStoredSalt(salt);
        user.setStoredHash(hashedPassword);

        userRepository.save(user);

        return convertToDto(user);
    }

    public void updateUser(Long id, UserDTO userDto, String password) {
        User user = findOrThrow(id);
        User userParam = convertToEntity(userDto);

        user.setEmail(userParam.getEmail());
        user.setMobilePhone(userParam.getMobilePhone());

        if (!password.isBlank()) {
            byte[] salt = createSalt();
            byte[] hashedPassword = createPasswordHash(password, salt);

            user.setStoredSalt(salt);
            user.setStoredHash(hashedPassword);
        }

        userRepository.save(user);
    }

    public void removeUserById(Long id) {
        findOrThrow(id);
        userRepository.deleteById(id);
    }

    private byte[] createSalt() {
        var random = new SecureRandom();
        var salt = new byte[128];
        random.nextBytes(salt);

        return salt;
    }

    private byte[] createPasswordHash(String password, byte[] salt) {

        try{
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(salt);
            return md.digest(password.getBytes(StandardCharsets.UTF_8));
        }catch (NoSuchAlgorithmException e){
            throw new CustomException("");
        }

    }

    private User findOrThrow(final Long id) {
        Optional<User> byId = userRepository
                .findById(id);
        if(byId.isPresent()){
            return byId.get();
        }
        throw new CustomException("User by id " + id + " was not found");
    }

    private UserDTO convertToDto(User entity) {
        return mapper.map(entity, UserDTO.class);
    }

    private User convertToEntity(UserDTO dto) {
        return mapper.map(dto, User.class);
    }
}
