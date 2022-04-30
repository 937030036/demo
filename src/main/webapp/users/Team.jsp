<!--
 * @Description: 
 * @Version: 
 * @Autor: Zhangchunhao
 * @Date: 2022-04-29 13:44:59
 * @LastEditors: Zhanchunhao
 * @LastEditTime: 2022-04-30 22:24:02
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
List<List<String>> namelist;
%>
<html>
  <head>
    <meta charset="utf-8" />
    <title>团队界面</title>
    <style>
      body{
        background-image: url(/getpic?pic=teambg);
        background-repeat: no-repeat;
        background-attachment: fixed;
        background-size: 100%;
      }
      .hover{
        font-size: larger;
        background-color: darkorange;
    }
    </style>
  </head>
  
  <body>
    <h2 style="font-size: large;text-align: center;">团队界面</h2>
    <% 
      data=(String)session.getAttribute("data"); 
      json = JSON.parseObject(data);
      namelist=(List<List<String>>)json.get("memlist");
    %>
    <p id="1" style="color:rgb(235, 235, 240);font-size:20px;text-align:center;">团队成员：</p>
    <form action="/Index/page" method="get">
      <div style="position:absolute;left:530px;top:50px">
        <button name="flush" type="submit" style="color: rgb(236, 20, 52); width: 100px;height: 100px;">返回主界面</button>
      </div>
    </form>
    <%for(List<String> sameteam:namelist){ %>
      <%for(String name:sameteam){ %>
        <% if(sameteam.get(0).equals(name)){ %>
          <p style="color:rgb(115, 132, 16);font-size:30px;text-align:center;"><%= name %></p>
          
        <%continue;}%>

        <p style="color:rgb(113, 18, 202);font-size:20px;text-align:center;"><%= name %></p>

      <%}%>
      <br><br>
    <%}%>
    <div align="center"><font size="2"> ${msg}</font></div>
  </body>
</html>
