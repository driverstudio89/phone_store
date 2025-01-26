package com.exercise.phone_store.session;

import com.exercise.phone_store.data.UserRepository;
import com.exercise.phone_store.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserHelperService {
    private static final String ROLE_PREFIX = "ROLE_";

    private final UserRepository userRepository;

    public UserHelperService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUser() {
        return userRepository.findByEmail(getUserDetails().getUsername()).orElse(null);
    }

    public boolean hasRole(String role) {
        return getUserDetails().getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals(ROLE_PREFIX + role));
    }

    public UserDetails getUserDetails() {
        return (UserDetails) getAuthentication().getPrincipal();
    }

    public boolean isAuthenticated() {
        return getAuthentication().getAuthorities().stream()
                .noneMatch(grantedAuthority -> grantedAuthority.getAuthority().equals(ROLE_PREFIX + "ANONYMOUS"));
    }

    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
