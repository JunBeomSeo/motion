package com.motion.mapper.user;

import org.apache.ibatis.annotations.Mapper;

import com.motion.user.model.UserDTO;


@Mapper
public interface UserMapper {

    public int insertUser(String email, String name, String profile);
    public UserDTO selectUser(String email);
    
}
