package com.motion.user.service;

import com.motion.user.model.UserDTO;

import jakarta.servlet.http.HttpSession;

public interface UserService {

    public void sendAuthCode(String email, HttpSession session);
    public String createCode();
    
    public int insertUser(String email, String name, String profile);
    public UserDTO selectUser(String email);
    
}
