package com.example.demo.Dao;

import java.util.List;

import com.example.demo.Model.User;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserlistMapper {
    @Select("select * from user")
    List<User> getUserList();

    @Insert("insert into user(username,password) values (#{username},#{password})")
    int insertUser(User user);
}
