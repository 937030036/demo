<!--
 * @Description: 
 * @Version: 
 * @Autor: Zhangchunhao
 * @Date: 2022-04-22 15:45:10
 * @LastEditors: Zhanchunhao
 * @LastEditTime: 2022-04-28 21:18:37
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
List<String> teamlist;
int unfinnum;
%>
<html>
  <head>
    <meta charset="utf-8" />
    <title>主界面</title>
  </head>
  
  <body>
    <h2 style="font-size: large;text-align: center;">主界面</h2>
    <% 
      data=(String)session.getAttribute("data"); 
      json = JSON.parseObject(data);
      teamlist=(List<String>)json.get("teamlist");
      unfinnum=(int)json.get("unfinnum");
    %>
    <p id="1" style="color:rgb(235, 235, 240);font-size:20px;text-align:center;">所在团队：</p>
    <%for(String teamname:teamlist){ %>
      <p id="2" style="color:rgb(145, 138, 139);font-size:20px;text-align:center;"><%= unfinnum %></p>
    <%}%>
    <p id="3" style="color:rgb(225, 18, 18);font-size:20px;text-align:center;">未处理事务：<%= unfinnum %></p>
    <form name="teaminfo" method="post">
      <div id="4" style="text-align:center;">
        <input style="font-size: large;text-align: center;" type="text" name="teamname" placeholder="团队名" required="required"/>
      </div>
      <br />
      <div id="5" style="text-align:center;">
        <input style="font-size: large;text-align: center;" type="password" name="teampassword" placeholder="团队口令" required="required"/>
      </div>
      <br />
      <div id="6"  align="center">
        <input type="hidden" name="_method" value="" />
        <button  type="button" onclick="launch()" style="color: aqua;">创建团队</button>
        <button  type="button" onclick="join()" style="color: aqua;">加入团队</button>
        <button  type="button" onclick="disband()" style="color: aqua;">解散团队</button>
      </div>
    </form>
    <%for(int i=0;i<6;i++){ %>
      <br>
    <%}%>
    <p align="center">
        <button name="transhandle" type="button" onclick="transhandle()" style="color: rgb(166, 53, 194);width: 500px;;height: 30px;">处理你的事务</button>
    </p>
    <%for(int i=0;i<6;i++){ %>
      <br>
    <%}%>
    
  </body>
</html>
<script>
  function launch(){
    document.teaminfo.method="post";
    document.teaminfo.action="/Team";
    document.teaminfo.submit();
  }
  function join(){
    document.teaminfo.action="/Team/userid";
    document.teaminfo._method.value="put";
    document.teaminfo.submit();
  }
  function disband(){
    document.teaminfo.action="/Team/id";
    document.teaminfo._method.value="delete";
    document.teaminfo.submit();
  }
  function transhandle(){
    document.transhandle.method="get";
    document.transhandle.action="/Trans/page";
    document.transhandle.submit();
  }
</script>