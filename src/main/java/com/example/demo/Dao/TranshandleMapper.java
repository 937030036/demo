/*
 * @Description: Transctions handle status.
 * @Version: 
 * @Autor: Zhangchunhao
 * @Date: 2022-04-23 13:07:08
 * @LastEditors: Zhanchunhao
 * @LastEditTime: 2022-04-30 19:01:33
 */

package com.example.demo.Dao;

import java.util.List;

import com.example.demo.Model.Transhandle;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface TranshandleMapper {
    @Select("select * from trans_handle where userid=#{userid} and ishandled=false")
    /**
     * @description: Get unfinished transctions by userid.
     * @param {int} userid
     * @return {List<Transhandle>}
     * @author: Zhangchunhao
     */
    List<Transhandle> getUnfinishTranshandleListByUserid(int userid);

    @Select("select * from trans_handle where userid=#{userid} and ishandled=true")
    /**
     * @description: Get finished transctions by userid.
     * @param {int} userid
     * @return {List<Transhandle>}
     * @author: Zhangchunhao
     */
    List<Transhandle> getFinishTranshandleListByUserid(int userid);

    @Update("update trans_handle set ishandled=#{ishandled},value=#{value} where transid=#{transid} and userid=#{userid}")
    /**
     * @description: Alter handled status.
     * @param {Transhandle} transhandle
     * @return {int}
     * @author: Zhangchunhao
     */
    int updateTranshandleStatu(Transhandle transhandle);

    @Insert("insert into trans_handle value(#{transid},#{userid},#{ishandled},#{value})")
    /**
     * @description: Add transction handled statu.
     * @param {Transhandle} transhandle
     * @return {int}
     * @author: Zhangchunhao
     */
    int insertTranshandle(Transhandle transhandle);

    @Select("select count(*) from trans_handle where userid=#{userid} and ishandled=false")
    /**
     * @description: Get unfinish transctions num by userid.
     * @param {int} userid
     * @return {int}
     * @author: Zhangchunhao
     */
    int getUnfinishNumByUserid(int userid);

    @Delete("delete from trans_handle where transid=#{transid}")
    /**
     * @description: Delete a transction record by transid.
     * @param {int} transid
     * @return {int}
     * @author: Zhangchunhao
     */
    int deleteTranshandleByTransid(int transid);
}
