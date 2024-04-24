package com.deadlineforce.backend.service;

import com.deadlineforce.backend.entity.User;
import com.deadlineforce.backend.repository.UserRepository;
import com.deadlineforce.backend.security.IdUserDetails;
import com.deadlineforce.backend.service.user.ServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserFromSecurityContext() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null) {
            Object principal = authentication.getPrincipal();
            if (principal != null) {
                IdUserDetails userDetails = (IdUserDetails) principal;
                return this.userRepository.findById(userDetails.getId()).orElseThrow();
            }
        }

        throw new ServiceException();
    }
}