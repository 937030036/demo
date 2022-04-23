/*
 * @Description: Service for display page.
 * @Version: 
 * @Autor: Zhangchunhao
 * @Date: 2022-04-23 17:42:38
 * @LastEditors: Zhanchunhao
 * @LastEditTime: 2022-04-23 22:52:27
 */

package com.example.demo.Service.interfaces;

import javax.servlet.http.HttpServletRequest;

import com.example.demo.Msg.Msg;

public interface PageService {
    /**
     * @description: Display user main page.
     * @param {HttpServletRequest} request
     * @return {Msg}
     * @author: Zhangchunhao
     */
    Msg IndexPageService(HttpServletRequest request);

    /**
     * @description: Display user's transctions page.
     * @param {HttpServletRequest} request
     * @return {Msg}
     * @author: Zhangchunhao
     */
    Msg TransPageService(HttpServletRequest request);

    /**
     * @description: Display user's team members page.
     * @param {HttpServletRequest} request
     * @return {Msg}
     * @author: Zhangchunhao
     */
    Msg TeamMemPageService(HttpServletRequest request);
}
