<!--
 * @Description: 
 * @Version: 
 * @Autor: Zhangchunhao
 * @Date: 2022-04-22 15:45:10
 * @LastEditors: Zhanchunhao
 * @LastEditTime: 2022-04-27 11:22:50
-->
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ page import="java.io.*,java.util.*,com.alibaba.fastjson.JSON,com.alibaba.fastjson.JSONObject" %>

<!DOCTYPE html>
<%! 
String data;
JSONObject json;
 %>
<html>
  <head>
    <meta charset="utf-8" />
    <title>主界面</title>
  </head>
  <body>
    <h2>HTTP 头部请求实例</h2>
    <% 
      data=(String)session.getAttribute("data"); 
      JSONObject json =  JSON.parseObject(data);
    %>
    <font  size="3"> <%= json %> </font><br />
  </body>
</html>
