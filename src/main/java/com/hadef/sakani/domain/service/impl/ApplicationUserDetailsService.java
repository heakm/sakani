package com.hadef.sakani.domain.service.impl;

import com.hadef.sakani.domain.entity.User;
import com.hadef.sakani.domain.entity.UserPrincipleSecondary;
import com.hadef.sakani.domain.service.UserService;

import com.hadef.sakani.exceptions.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ApplicationUserDetailsService implements UserDetailsService {
    private final UserService userService;
    private final String serviceName;

    public ApplicationUserDetailsService(UserService userService) {
        this.userService = userService;
        this.serviceName = this.getClass().getName();
    }

    @Override
    public UserDetails loadUserByUsername(String email) {
        User user = userService.searchByEmail(email);

        return new UserPrincipleSecondary(user);
    }

    public User authenticate(String email, String password) {

        List<String> errors = new ArrayList<>();
        if (email.isEmpty() || password.isEmpty()) {
            errors.add("Unauthorized - Empty rest body");
        }

        User user = userService.searchByEmail(email);

        if (user == null) {
            errors.add("Unauthorized - User is null");
        }

        var verified = verifyPasswordHash(
                password,
                user.getStoredHash(),
                user.getStoredSalt()
        );

        if (!verified){
            errors.add("Unauthorized - User is not verified");
        }
        if(!errors.isEmpty()){
            throw new CustomException(errors.toString(),HttpStatus.UNAUTHORIZED.value(),serviceName);
        }
        return user;
    }

    private Boolean verifyPasswordHash(String password, byte[] storedHash, byte[] storedSalt) {
        List<String> errors = new ArrayList<String>();
        if (password.isBlank() || password.isEmpty()) {
            errors.add("Password cannot be empty or whitespace only string.");
        }

        if (storedHash.length != 64) {
            errors.add("Invalid length of password hash (64 bytes expected)");
        }

        if (storedSalt.length != 128) {
            errors.add("Invalid length of password salt (64 bytes expected).");
        }

        if(!errors.isEmpty()){
            throw new CustomException(errors.toString(),HttpStatus.INTERNAL_SERVER_ERROR.value(),serviceName);
        }
        try{
            var md = MessageDigest.getInstance("SHA-512");
            md.update(storedSalt);
            var computedHash = md.digest(password.getBytes(StandardCharsets.UTF_8));

            for (int i = 0; i < computedHash.length; i++) {
                if (computedHash[i] != storedHash[i]) return false;
            }
            return MessageDigest.isEqual(computedHash, storedHash);
        }catch (NoSuchAlgorithmException e){
            throw new CustomException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), serviceName);
        }
    }
}
