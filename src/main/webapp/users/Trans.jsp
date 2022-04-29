<!--
 * @Description: 
 * @Version: 
 * @Autor: Zhangchunhao
 * @Date: 2022-04-29 13:34:15
 * @LastEditors: Zhanchunhao
 * @LastEditTime: 2022-04-29 21:04:06
-->
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ page import="
java.io.*
,java.util.*
,com.alibaba.fastjson.JSON
,com.alibaba.fastjson.JSONObject
,com.example.demo.Model.Transhandle
" %>

<!DOCTYPE html>
<%! 
String data;
JSONObject json;
List<JSONObject> unfinlist;
List<JSONObject> finlist;
%>
<html>
  <head>
    <meta charset="utf-8" />
    <title>事务界面</title>
    <style>
        body{
          background-image: url(/getpic?pic=transbg);
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
    <h2 style="font-size: large;text-align: center;">事务界面</h2>

    <form name="launchtrans" action="/Team" method="post">
        <div id="4" style="text-align:center;">
            <input style="font-size: large;text-align: center;" type="text" name="teamname" placeholder="团队名" required="required"/>
        </div>

        <br />

        <div id="5" style="text-align:center;">
            <input style="font-size: large;text-align: center;" type="text" name="transtype" placeholder="事务类型" required="required"/>
        </div>

        <br />

        <div id="6" style="text-align:center;">
            <input style="font-size: large;text-align: center;" type="text" name="value" placeholder="事务内容" required="required"/>
        </div>

        <br />

        <div id="6"  align="center">
          <button  type="submit" style="color: aqua;">发起事务</button>
        </div>

      </form>
      
    <% 
        data=(String)session.getAttribute("data"); 
        json = JSON.parseObject(data);
        unfinlist=(List<JSONObject>)json.get("unfinlist");
        finlist=(List<JSONObject>)json.get("finlist");
    %>
    <br><br><br>
    <h3 style="font-size: large;text-align: center;">未处理事务</h3>
    <table border="1" align="center" style="width: 1000px;">
        <tr>
            <th>事务编号</th>
            <th>事务类型</th>
            <th>事务内容</th>
            <th></th>
        </tr>

        <% for(JSONObject unfin:unfinlist){%>
            <tr>
                <td> <% unfin.get("transid"); %> </td>
                <td> <% unfin.get("transtype"); %> </td>
                <td> <% unfin.get("value"); %> </td>
                <td>
                <% if(unfin.get("transtype").equals("message")){ %>    
                    <form name="altertrans" action="/Trans/id" method="post">
                        <input type="hidden" name="_method" value="put" />
                        <input type="hidden" name="transid" value= <% unfin.get("transid"); %>
                        <button type="submit" style="color: brown;">收到</button>
                    </form>  
                <% } %>
                <% if(unfin.get("transtype").equals("question")){ %>
                    <form name="altertrans" action="/Trans/id" method="post">
                        <input type="hidden" name="_method" value="put" />
                        <input type="hidden" name="transid" value= <% unfin.get("transid"); %>
                        <input style="font-size: large;text-align: center;" type="text" name="value" placeholder="回答" required="required"/>
                        <button type="submit" style="color: brown;">收到</button>
                    </form>  
                <% } %>
                </td>
            </tr>
        <%}%>
    </table>

    <br><br><br>
    <h3 style="font-size: large;text-align: center;">历史事务</h3>
    <table border="1" align="center" style="width: 1000px;">
        <tr>
            <th>事务编号</th>
            <th>事务类型</th>
            <th>事务内容</th>
            <th></th>
        </tr>

        <% for(JSONObject fin:finlist){%>
            <tr>
                <td> <% fin.get("transid"); %> </td>
                <td> <% fin.get("transtype"); %> </td>
                <td> <% fin.get("value"); %> </td>
                <td>
                    <form name="altertrans" action="/Trans/history/id" method="post">
                        <input type="hidden" name="_method" value="put" />
                        <input type="hidden" name="transid" value= <% fin.get("transid"); %>
                        <button type="submit" style="color: brown;">撤销此事务</button>
                    </form>
                </td>
            </tr>
        <%}%>
    </table>
    
  </body>
</html>
