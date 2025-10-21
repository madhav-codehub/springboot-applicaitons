package com.codehub.financetracker.service;

import com.codehub.financetracker.dto.RegisterDTO;
import com.codehub.financetracker.entity.User;
import com.codehub.financetracker.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void register(RegisterDTO registerDTO) {
        User user = new User();
        user.setUsername(registerDTO.getUsername());
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
        user.setEmail(registerDTO.getEmail());
        userRepository.save(user);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("User not found!")
        );
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("User not found!")
        );
    }
}
