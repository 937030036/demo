/*
 * @Description: 
 * @Version: 
 * @Autor: Zhangchunhao
 * @Date: 2022-04-23 17:43:15
 * @LastEditors: Zhanchunhao
 * @LastEditTime: 2022-04-23 22:52:18
 */
package com.example.demo.Service.interfaces;

import javax.servlet.http.HttpServletRequest;

import com.example.demo.Msg.Msg;

public interface TransService {
    /**
     * @description:
     * @param {HttpServletRequest} request
     * @return {Msg}
     * @author: Zhangchunhao
     */
    Msg LaunchTransService(HttpServletRequest request);

    /**
     * @description:
     * @param {HttpServletRequest} request
     * @return {Msg}
     * @author: Zhangchunhao
     */
    Msg TransHandleService(HttpServletRequest request);

    /**
     * @description:
     * @param {HttpServletRequest} request
     * @return {Msg}
     * @author: Zhangchunhao
     */
    Msg TransHistoryService(HttpServletRequest request);

}
