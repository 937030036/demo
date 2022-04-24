/*
 * @Description: 
 * @Version: 
 * @Autor: Zhangchunhao
 * @Date: 2022-04-23 17:43:03
 * @LastEditors: Zhanchunhao
 * @LastEditTime: 2022-04-24 17:31:26
 */
package com.example.demo.Service.interfaces;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import com.example.demo.Msg.Msg;

public interface TeamService {
    /**
     * @description: Register a team for current user.
     * @param {HttpServletRequest} request
     * @return {Msg}
     * @author: Zhangchunhao
     */
    Msg RegisterTeamService(HttpServletRequest request) throws IOException;

    /**
     * @description: Add the user into a team.
     * @param {HttpServletRequest} request
     * @return {Msg}
     * @author: Zhangchunhao
     */
    Msg JoinTeamService(HttpServletRequest request) throws IOException;

    /**
     * @description: Disband team the user own.
     * @param {HttpServletRequest} request
     * @return {Msg}
     * @author: Zhangchunhao
     */
    Msg DisbandTeamService(HttpServletRequest request) throws IOException;
}
