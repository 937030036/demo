<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <meta charset="UTF-8" />
    <title>Sign up</title>
  </head>
  <body>
    <br />
    <br />
    <div align="center">
      <font size="5"> <strong>注册</strong></font>
    </div>
    <br />
    <div class="login">
      <form action="SignupHandle" method="post">
        <div align="center">
          <input
            type="text"
            name="user"
            placeholder="用户名"
            required="required"
          />
        </div>
        <br />
        <div align="center">
          <input
            type="password"
            name="password"
            placeholder="用户密码"
            required="required"
          />
        </div>
        <br />
        <div align="center"><button type="submit">注册</button></div>
      </form>
    </div>
    <div align="center"><font size="2"> ${msg}</font></div>
  </body>
</html>
