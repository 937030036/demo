<!--
 * @Description: 
 * @Version: 
 * @Autor: Zhangchunhao
 * @Date: 2022-04-22 15:45:10
 * @LastEditors: Zhanchunhao
 * @LastEditTime: 2022-04-30 20:09:09
-->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <%@ page import="
java.io.*
,java.util.*
,com.alibaba.fastjson.JSON
,com.alibaba.fastjson.JSONObject
" %>

    <!DOCTYPE html>
    <%! String data; JSONObject json; List<String> teamlist;
      int unfinnum;
      %>
      <html>

      <head>
        <meta charset="utf-8" />
        <title>主界面</title>
        <style>
          body {
            background-image: url(/getpic?pic=indexbg);
            background-repeat: no-repeat;
            background-attachment: fixed;
            background-size: 100%;
          }

          .hover {
            font-size: larger;
            background-color: darkorange;
          }
        </style>
      </head>

      <body>
        <h2 style="font-size: large;text-align: center;">主界面</h2>
        <% data=(String)session.getAttribute("data"); json=JSON.parseObject(data); teamlist=(List<String>
          )json.get("teamlist");
          unfinnum=(int)json.get("unfinnum");
          %>
          <p id="1" style="color:rgb(54, 98, 66);font-size:20px;text-align:center;">所在团队：</p>
          <%for(String teamname:teamlist){ %>
            <p id="2" style="color:rgb(145, 138, 139);font-size:20px;text-align:center;">
              <%= teamname %>
            </p>
            <%}%>
              <p id="3" style="color:rgb(225, 18, 18);font-size:20px;text-align:center;">未处理事务：<%= unfinnum %>
              </p>
              <form name="teaminfo" method="post" accept-charset="UTF-8">
                <div id="4" style="text-align:center;">
                  <input style="font-size: large;text-align: center;" type="text" name="teamname" placeholder="团队名"
                    required="required" />
                </div>
                <br />
                <div id="5" style="text-align:center;">
                  <input style="font-size: large;text-align: center;" type="password" name="teampassword"
                    placeholder="团队口令" required="required" />
                </div>
                <br />
                <div id="6" align="center">
                  <input type="hidden" name="_method" value="" />
                  <button type="button" onclick="launch()" style="color: aqua;">创建团队</button>
                  <button type="button" onclick="join()" style="color: aqua;">加入团队</button>
                  <button type="button" onclick="disband()" style="color: aqua;">解散团队</button>
                </div>
              </form>
              <form action="/Index/page" method="get">
                <input type="hidden" name="flush" value="">
                <div style="position:absolute;left:550px;top:150px">
                  <button name="flush" type="submit"
                    style="color: rgb(236, 20, 52); width: 100px;height: 100px;">刷新</button>
                </div>
              </form>
              <%for(int i=0;i<6;i++){ %>
                <br>
                <%}%>
                  <form action="/Trans/page" method="get" align="center">
                    <div>
                      <button name="transhandle" type="submit"
                        style="color: rgb(166, 53, 194);width: 500px;;height: 30px;">处理你的事务</button>
                    </div>
                  </form>
                  <br>
                  <form action="/Team/page" method="get" align="center">
                    <div>
                      <button name="TeamPage" type="submit"
                        style="color: rgb(100, 233, 102);width: 500px;;height: 30px;">团队信息</button>
                    </div>
                  </form>
                  <div align="center">
                    <font size="2"> ${msg}</font>
                  </div>
      </body>

      </html>
      <script>
        function launch() {
          document.teaminfo.method = "post";
          document.teaminfo.action = "/Team";
          document.teaminfo.submit();
        }
        function join() {
          document.teaminfo.action = "/Team/userid";
          document.teaminfo._method.value = "put";
          document.teaminfo.submit();
        }
        function disband() {
          document.teaminfo.action = "/Team/id";
          document.teaminfo._method.value = "delete";
          document.teaminfo.submit();
        }
      </script>