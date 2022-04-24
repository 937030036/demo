/*
 * @Description: 
 * @Version: 
 * @Autor: Zhangchunhao
 * @Date: 2022-04-23 17:43:15
 * @LastEditors: Zhanchunhao
 * @LastEditTime: 2022-04-24 22:05:32
 */
package com.example.demo.Service.interfaces;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import com.example.demo.Msg.Msg;

public interface TransService {
    /**
     * @description: Add a transction for user's team.
     * @param {HttpServletRequest} request
     * @return {Msg}
     * @author: Zhangchunhao
     * @throws IOException
     */
    Msg LaunchTransService(HttpServletRequest request) throws IOException;

    /**
     * @description: Handle a user's action to a transction.
     * @param {HttpServletRequest} request
     * @return {Msg}
     * @author: Zhangchunhao
     * @throws IOException
     */
    Msg TransHandleService(HttpServletRequest request) throws IOException;

    /**
     * @description: alter user's handled transctions statu.
     * @param {HttpServletRequest} request
     * @return {Msg}
     * @author: Zhangchunhao
     * @throws IOException
     */
    Msg TransHistoryService(HttpServletRequest request) throws IOException;

}
