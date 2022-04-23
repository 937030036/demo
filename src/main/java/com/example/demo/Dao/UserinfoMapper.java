/*
 * @Description: userinfo relations of users and teams.
 * @Version: 
 * @Autor: Zhangchunhao
 * @Date: 2022-04-23 12:33:49
 * @LastEditors: Zhanchunhao
 * @LastEditTime: 2022-04-23 22:46:14
 */

package com.example.demo.Dao;

import java.util.List;

import com.example.demo.Model.Userinfo;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserinfoMapper {
  
    @Select("select * from user_info where userid=#{userid}")
    /**
     * @description: Get attribute of user is a leader or not,
     * and teamid of user.
     * @param {int} userid
     * @return {List<Userinfo>}
     * @author: Zhangchunhao
     */    
    List<Userinfo> getUserinfoListByUserid(int userid);
   
    @Select("select * from user_info where teamid=#{teamid}")
    /**
     * @description: Get all member of a team.
     * @param {int} teamid
     * @return {List<Userinfo>}
     * @author: Zhangchunhao
     */    
    List<Userinfo> getUserinfoListByTeamid(int teamid);
    
    @Insert("insert into user_info value(#{userid},#{teamid},#{isleader}")
    /**
     * @description: Add a record of relation of user and team.
     * @param {Userinfo} userinfo
     * @return {int}
     * @author: Zhangchunhao
     */    
    int insertUserinfo(Userinfo userinfo);

    @Delete("delete from user_info where userid=#{userid} and teamid=#{teamid}")
    /**
     * @description: Delete a record of relation of user and team.
     * @param {Userinfo} userinfo
     * @return {int}
     * @author: Zhangchunhao
     */    
    int deleteUserinfoByObj(Userinfo userinfo);
   
    @Delete("delete from user_info where userid=#{userid}")
    /**
     * @description: Delete all records of a user
     * @param {int} userid
     * @return {int}
     * @author: Zhangchunhao
     */    
    int deleteUserinfoByUserid(int userid);
  
    @Delete("delete from user_info where teamid=#{teamid}")
    /**
     * @description: Delete all records of a team
     * @param {int} teamid
     * @return {int}
     * @author: Zhangchunhao
     */    
    int deleteUserinfoByTeamid(int teamid);

    @Select("select * from user_info where userid=#{userid} and teamid=#{teamid}")
    /**
     * @description: Get a complete infomation of a record by part infomation
     * @param {Userinfo} userinfo
     * @return {Userinfo}
     * @author: Zhangchunhao
     */    
    Userinfo getUserinfoByObj(Userinfo userinfo);
}
