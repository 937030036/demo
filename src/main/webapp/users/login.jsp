<!--
 * @Description: 
 * @Version: 
 * @Autor: Zhangchunhao
 * @Date: 2022-04-22 16:45:11
 * @LastEditors: Zhanchunhao
 * @LastEditTime: 2022-04-28 17:55:45
-->
<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <meta charset="UTF-8" />
    <title>登录</title>
  </head>
  <body>
    <br />
    <br />
    <div align="center">
      <font size="5"> <strong>登录</strong></font>
    </div>
    <br />
    <div class="login">
      <form action="/SigninHandle" method="get">
        <div align="center">
          <input
            type="text"
            name="username"
            placeholder="用户名"
            required="required"
          />
        </div>
        <br />
        <div align="center">
          <input
            type="password"
            name="password"
            placeholder="登录密码"
            required="required"
          />
        </div>
        <br />
        <div align="center"><button type="submit">确定</button></div>
      </form>
    </div>
    <div align="center"><font size="2"> ${msg}</font></div>
  </body>
</html>
