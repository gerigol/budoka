package com.gerigol.budoka.service;

import com.gerigol.budoka.controller.dto.NewUser;
import com.gerigol.budoka.controller.dto.UserDTO;
import com.gerigol.budoka.domain.User;
import com.gerigol.budoka.repository.UserRepository;
import com.gerigol.budoka.utility.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.firewall.RequestRejectedException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User doesn't exist with email: " + email));
    }

    public UserDTO registerUser(NewUser newUser) {
        if (userRepository.existsByEmail(newUser.email()))
            throw new RequestRejectedException("User already exists with email: " + newUser.email()); // TODO: create custom exception
        User user = UserMapper.toUser(newUser);
        userRepository.save(user);
        return UserMapper.toUserDTO(user);
    }
}
