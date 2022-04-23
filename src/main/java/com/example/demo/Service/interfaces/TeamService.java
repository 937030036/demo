/*
 * @Description: 
 * @Version: 
 * @Autor: Zhangchunhao
 * @Date: 2022-04-23 17:43:03
 * @LastEditors: Zhanchunhao
 * @LastEditTime: 2022-04-23 22:51:57
 */
package com.example.demo.Service.interfaces;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import com.example.demo.Msg.Msg;

public interface TeamService {
    /**
     * @description:
     * @param {HttpServletRequest} request
     * @return {Msg}
     * @author: Zhangchunhao
     */
    Msg RegisterTeamService(HttpServletRequest request) throws IOException;

    /**
     * @description:
     * @param {HttpServletRequest} request
     * @return {Msg}
     * @author: Zhangchunhao
     */
    Msg JoinTeamService(HttpServletRequest request) throws IOException;

    /**
     * @description:
     * @param {HttpServletRequest} request
     * @return {Msg}
     * @author: Zhangchunhao
     */
    Msg DisbandTeamService(HttpServletRequest request) throws IOException;
}
