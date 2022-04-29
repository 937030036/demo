<!--
 * @Description: 
 * @Version: 
 * @Autor: Zhangchunhao
 * @Date: 2022-04-29 13:44:59
 * @LastEditors: Zhanchunhao
 * @LastEditTime: 2022-04-29 13:51:45
-->
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ page import="
java.io.*
,java.util.*
,com.alibaba.fastjson.JSON
,com.alibaba.fastjson.JSONObject
" %>

<!DOCTYPE html>
<%! 
String data;
JSONObject json;
List<String> namelist;
%>
<html>
  <head>
    <meta charset="utf-8" />
    <title>团队界面</title>
  </head>
  
  <body>
    <h2 style="font-size: large;text-align: center;">团队界面</h2>
    <% 
      data=(String)session.getAttribute("data"); 
      json = JSON.parseObject(data);
      namelist=(List<String>)json.get("memlist");
    %>
    <p id="1" style="color:rgb(235, 235, 240);font-size:20px;text-align:center;">团队成员：</p>
    <%for(String name:namelist){ %>
      <p id="2" style="color:rgb(145, 138, 139);font-size:20px;text-align:center;"><%= name %></p>
    <%}%>
  </body>
</html>
