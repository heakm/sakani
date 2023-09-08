package com.hadef.sakani.domain.service.impl;

import com.hadef.sakani.domain.entity.User;
import com.hadef.sakani.domain.entity.UserPrincipleSecondary;
import com.hadef.sakani.domain.service.UserService;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class ApplicationUserDetailsService implements UserDetailsService {
    private final UserService userService;

    public ApplicationUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        User user = userService.searchByEmail(email);

        return new UserPrincipleSecondary(user);
    }

    public User authenticate(String email, String password)
            throws NoSuchAlgorithmException {
        if (
                email.isEmpty() || password.isEmpty()
        ) throw new BadCredentialsException("Unauthorized");

        User user = userService.searchByEmail(email);

        if (user == null) throw new BadCredentialsException("Unauthorized");

        var verified = verifyPasswordHash(
                password,
                user.getStoredHash(),
                user.getStoredSalt()
        );

        if (!verified) throw new BadCredentialsException("Unauthorized");

        return user;
    }

    private Boolean verifyPasswordHash(
            String password,
            byte[] storedHash,
            byte[] storedSalt
    ) throws NoSuchAlgorithmException {
        if (password.isBlank() || password.isEmpty()) throw new IllegalArgumentException(
                "Password cannot be empty or whitespace only string."
        );

        if (storedHash.length != 64) throw new IllegalArgumentException(
                "Invalid length of password hash (64 bytes expected)"
        );

        if (storedSalt.length != 128) throw new IllegalArgumentException(
                "Invalid length of password salt (64 bytes expected)."
        );

        var md = MessageDigest.getInstance("SHA-512");
        md.update(storedSalt);

        var computedHash = md.digest(password.getBytes(StandardCharsets.UTF_8));

        for (int i = 0; i < computedHash.length; i++) {
            if (computedHash[i] != storedHash[i]) return false;
        }

        // The above for loop is the same as below

        return MessageDigest.isEqual(computedHash, storedHash);
    }
}
