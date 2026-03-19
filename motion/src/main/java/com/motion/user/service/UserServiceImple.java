package com.motion.user.service;

import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.motion.mapper.user.UserMapper;
import com.motion.user.model.UserDTO;

import jakarta.servlet.http.HttpSession;

@Service
public class UserServiceImple implements UserService{

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private UserMapper userMapper;

    @Override
    public void sendAuthCode(String email, HttpSession session) {
        
        String code = createCode();

        session.setAttribute("authCode", code);
        session.setAttribute("email", email);

        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("tjwnsqja65@naver.com"); 
        message.setTo(email);
        message.setSubject("Motion 이메일 인증 코드");
        message.setText("인증코드 : " + code);

        mailSender.send(message);
    }
        
    @Override
    public String createCode(){

        Random random = new Random();

        int number = random.nextInt(900000) + 100000;

        return String.valueOf(number);
    }
    
    @Override
    public int insertUser(String email, String name, String profile) {

        int result = userMapper.insertUser(email, name, profile);
        return result;
    }

    @Override
    public UserDTO selectUser(String email) {
        UserDTO dto = userMapper.selectUser(email);
        return dto;
    }
}
