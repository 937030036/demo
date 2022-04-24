/*
 * @Description: Team infomation.
 * @Version: 
 * @Autor: Zhangchunhao
 * @Date: 2022-04-23 10:43:26
 * @LastEditors: Zhanchunhao
 * @LastEditTime: 2022-04-24 18:00:56
 */

package com.example.demo.Dao;

import java.util.List;

import com.example.demo.Model.Team;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface TeamMapper {
    @Select("select * from team")
    /**
     * @description: Get all team info for sign.
     * @param {*}
     * @return {List<Team>}
     * @author: Zhangchunhao
     */
    List<Team> getTeamList();

    @Select("select * from team where teamid=#{teamid}")
    /**
     * @description: Get a team info by its id.
     * @param {int} teamid
     * @return {Team}
     * @author: Zhangchunhao
     */
    Team getTeamById(int teamid);

    @Select("select teamid from team where teamname=#{teamname}")
    /**
     * @description: Get a teamid by its name.
     * @param {String} teamname
     * @return {int}
     * @author: Zhangchunhao
     */
    int getTeamIdByName(String teamname);

    @Insert("insert into team value(#{teamid},#{teamname},#{teampassword}")
    /**
     * @description: Add a team info.
     * @param {Team} team
     * @return {int}
     * @author: Zhangchunhao
     */
    int insertTeam(Team team);

    @Update("update team set teamname=#{teamname},teampassword=#{teampassword} where teamid=#{teamid}")
    /**
     * @description: Alter team info.
     * @param {Team} team
     * @return {int}
     * @author: Zhangchunhao
     */
    int updateTeam(Team team);

    @Delete("delete from team where teamid=#{teamid}")
    /**
     * @description: Delete a team info by id.
     * @param {int} teamid
     * @return {int}
     * @author: Zhangchunhao
     */
    int deleteTeam(int teamid);
}
