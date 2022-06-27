package com.codegym.test.service.user;

import com.codegym.test.model.entity.User;
import com.codegym.test.service.IGeneralService;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;


public interface IUserService extends IGeneralService<User>, UserDetailsService {
    User saveAdmin(User user);
    Optional<User> findByUsername(String username);
}
