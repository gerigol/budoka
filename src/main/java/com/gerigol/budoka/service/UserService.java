package com.gerigol.budoka.service;

import com.gerigol.budoka.controller.dto.NewUser;
import com.gerigol.budoka.controller.dto.UserDTO;
import com.gerigol.budoka.domain.User;
import com.gerigol.budoka.exception.BadRequestException;
import com.gerigol.budoka.repository.UserRepository;
import com.gerigol.budoka.utility.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User doesn't exist with email: " + email));
    }

    public UserDTO registerUser(NewUser newUser) {
        if (userRepository.existsByEmail(newUser.email()))
            throw new BadRequestException("User already exists with email: " + newUser.email());
        User user = userRepository.save(UserMapper.toUser(newUser, passwordEncoder.encode(newUser.password())));
        return UserMapper.toUserDTO(user);
    }
}
