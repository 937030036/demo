/*
 * @Description: 
 * @Version: 
 * @Autor: Zhangchunhao
 * @Date: 2022-04-13 17:40:20
 * @LastEditors: Zhanchunhao
 * @LastEditTime: 2022-05-04 13:48:25
 */
package com.example.demo.Service.interfaces;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import com.example.demo.Msg.Msg;

public interface SignService {
    /**
     * @description: Signing in verify.
     * @param {HttpServletRequest} request
     * @return {Msg}
     * @author: Zhangchunhao
     */
    public Msg signinHandleService(HttpServletRequest request) throws IOException;

    /**
     * @description: Signing up verify.
     * @param {HttpServletRequest} request
     * @return {Msg}
     * @author: Zhangchunhao
     */
    public Msg signupHandleService(HttpServletRequest request) throws IOException;
}
