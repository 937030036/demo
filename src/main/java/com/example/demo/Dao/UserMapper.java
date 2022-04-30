/*
 * @Description: User info.
 * @Version: 
 * @Autor: Zhangchunhao
 * @Date: 2022-04-13 16:40:38
 * @LastEditors: Zhanchunhao
 * @LastEditTime: 2022-04-30 19:48:55
 */

package com.example.demo.Dao;

import java.util.List;

import com.example.demo.Model.User;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {

    @Select("select * from user")
    /**
     * @description: Get all user for signing.
     * @param {*}
     * @return {List<User>}
     * @author: Zhangchunhao
     */
    List<User> getUserList();

    @Select("select * from user where userid=#{userid}")
    /**
     * @description: Get a user info by its id.
     * @param {int} userid
     * @return {User}
     * @author: Zhangchunhao
     */
    User getUserById(int userid);

    @Select("select userid from user where username=#{username}")
    /**
     * @description: Get userid by username.
     * @param {String} username
     * @return {int}
     * @author: Zhangchunhao
     */    
    int getUseridByName(String username);

    @Insert("insert into user values (#{userid},#{username},#{password})")
    /**
     * @description: Add a user info.
     * @param {User} user
     * @return {int}
     * @author: Zhangchunhao
     */
    int insertUser(User user);

    @Delete("delete from user where userid=#{userid}")
    /**
     * @description: Delete a user info.
     * @param {User} user
     * @return {int}
     * @author: Zhangchunhao
     */
    int deleteUser(User user);

    @Update("update user set username=#{username},password=#{password} where userid=#{userid}")
    /**
     * @description: Alter a user info.
     * @param {User} user
     * @return {int}
     * @author: Zhangchunhao
     */
    int updateUser(User user);
}
