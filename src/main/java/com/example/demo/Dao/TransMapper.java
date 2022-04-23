/*
 * @Description: Transctions info.
 * @Version: 
 * @Autor: Zhangchunhao
 * @Date: 2022-04-23 12:50:26
 * @LastEditors: Zhanchunhao
 * @LastEditTime: 2022-04-23 22:44:44
 */

package com.example.demo.Dao;

import java.util.List;

import com.example.demo.Model.Trans;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface TransMapper {

    @Select("select * from trans")
    /**
     * @description: Get all transctions info.
     * @param {*}
     * @return {List<Trans>}
     * @author: Zhangchunhao
     */
    List<Trans> getAllTransList();

    @Select("select * from trans where teamid=#{teamid}")
    /**
     * @description: Get all transctions of the team.
     * @param {int} teamid
     * @return {List<Trans>}
     * @author: Zhangchunhao
     */
    List<Trans> getTransListByTeamid(int teamid);

    @Insert("insert into trans value(#{transid},#{teamid},#{transtype},#{value})")
    /**
     * @description: Add a transction info.
     * @param {Trans} trans
     * @return {int}
     * @author: Zhangchunhao
     */
    int insertTrans(Trans trans);

    @Delete("delete from trans where teamid=#{teamid}")
    /**
     * @description: Delete mult transctions of the team.
     * @param {int} teamid
     * @return {int}
     * @author: Zhangchunhao
     */
    int deleteTransByTeamid(int teamid);

    @Delete("delete from trans where teamid=#{teamid} and transid=#{transid}")
    /**
     * @description: Delete a transctions by its part info.
     * @param {Trans} trans
     * @return {int}
     * @author: Zhangchunhao
     */
    int deleteTransByObj(Trans trans);
}
