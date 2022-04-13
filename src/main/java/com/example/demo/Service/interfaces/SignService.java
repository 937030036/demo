package com.example.demo.Service.interfaces;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import com.example.demo.Msg.Msg;

public interface SignService {

    public Msg signinHandleService(HttpServletRequest request) throws IOException;

    public Msg signupHandleService(HttpServletRequest request) throws IOException;
}
