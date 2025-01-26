package com.exercise.phone_store.session;

import com.exercise.phone_store.data.UserRepository;
import com.exercise.phone_store.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AppUserDetailService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).map(this::mapToUserDetails)
                .orElseThrow(() -> new UsernameNotFoundException("User with " + email + " not found!"));
    }

    private UserDetails mapToUserDetails(User user) {
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getEmail())
                .password((user.getPassword()))
                .authorities((mapGrantedAuthorities(user)))
                .build();
    }

    private List<GrantedAuthority> mapGrantedAuthorities(User user) {
        System.out.println();
        return user.getRoles().stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role.getRole().name()))
                .collect(Collectors.toUnmodifiableList());
    }
}
