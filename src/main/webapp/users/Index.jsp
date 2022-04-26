<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ page import="java.io.*,java.util.*" %>

<!DOCTYPE html>
<%! String data; %>
<html>
  <head>
    <meta charset="utf-8" />
    <title>主界面</title>
  </head>
  <body>
    <h2>HTTP 头部请求实例</h2>
    <% data=(String)session.getAttribute("data"); %> 
    <% JSONObject jsonObject =  JSON.parseObject(data);%>
    <font  size="3"> <%= jsonObject %> </font><br />
  </body>
</html>
