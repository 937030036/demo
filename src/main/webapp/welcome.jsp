<!--
 * @Description: 
 * @Version: 
 * @Autor: Zhangchunhao
 * @Date: 2022-04-22 16:40:33
 * @LastEditors: Zhanchunhao
 * @LastEditTime: 2022-05-01 11:28:31
-->
<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <meta charset="UTF-8" />
    <title>TeamManager</title>
  </head>
  <body>
    <br />
    <br />
    <div class="welcome">
      <div align="center">
        <font size="5"> <strong>欢迎访问</strong></font>
      </div>
      <br />
      <br />
      <form action="/Signup/page" method="get">
        <div align="center"><button type="submit">新用户</button></div>
      </form>
      <br />
      <form action="/Signin/page" method="get">
        <div align="center"><button type="submit">已有账号</button></div>
      </form>
    </div>
    <div align="center"><font size="2"> ${msg}</font></div>
  </body>
</html>
