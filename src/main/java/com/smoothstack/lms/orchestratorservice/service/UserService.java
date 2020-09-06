package com.smoothstack.lms.orchestratorservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.smoothstack.lms.orchestratorservice.dao.UserDAO;
import com.smoothstack.lms.orchestratorservice.entity.User;

@Service
public class UserService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public void createAdmin(User user) {
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));
        user.setRole(User.Role.ROLE_ADMIN);
        this.userDAO.save(user);
    }

    public void createLibrarian(User user) {
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));
        user.setRole(User.Role.ROLE_LIBRARIAN);
        this.userDAO.save(user);
    }

    public List<User> getAllUsers() {
        return this.userDAO.findAll();
    }

    public User getUser(String email) {
        return this.userDAO.findByEmail(email);
    }

}
