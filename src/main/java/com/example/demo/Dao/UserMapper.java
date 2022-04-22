package com.example.demo.Dao;

import java.util.List;

import com.example.demo.Model.User;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper  {
    @Select("select * from user")
    List<User> getUserList();

    @Insert("insert into user(username,password) values (#{username},#{password})")
    int insertUser(User user);

    @Delete("delete from user where userid=#{userid}")
    int deleteUser(User user);

    @Update("update user set password=#{password} where userid=#{userid}")
    int updateUser(User user);
}
