<!--
 * @Description: 
 * @Version: 
 * @Autor: Zhangchunhao
 * @Date: 2022-04-29 13:34:15
 * @LastEditors: Zhanchunhao
 * @LastEditTime: 2022-04-30 21:17:13
-->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ page import="
java.io.*
,java.util.*
,com.alibaba.fastjson.JSON
,com.alibaba.fastjson.JSONObject
,com.example.demo.Model.Transhandle
" %>

        <!DOCTYPE html>
        <%! String data; JSONObject json; List<JSONObject> unfinlist;
            List<JSONObject> finlist;
                %>
                <html>

                <head>
                    <meta charset="utf-8" />
                    <title>事务界面</title>
                    <style>
                        body {
                            background-image: url(/getpic?pic=transbg);
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
                    <h2 style="font-size: large;text-align: center;">事务界面</h2>

                    <form name="launchtrans" action="/Trans" method="post" accept-charset="UTF-8">
                        <div id="1" style="text-align:center;">
                            <input style="font-size: large;text-align: center;" type="text" name="teamname"
                                placeholder="团队名" required="required" />
                        </div>

                        <br />

                        <div id="2" style="text-align:center;">
                            <input style="font-size: large;text-align: center;" type="text" name="transtype"
                                placeholder="事务类型" required="required" />
                        </div>

                        <br />

                        <div id="3" style="text-align:center;">
                            <input style="font-size: large;text-align: center;" type="text" name="value"
                                placeholder="事务内容" required="required" />
                        </div>

                        <br />

                        <div id="4" align="center">
                            <button type="submit" style="color: aqua;">发起事务</button>
                        </div>

                    </form>

                    <% data=(String)session.getAttribute("data"); json=JSON.parseObject(data);
                        unfinlist=(List<JSONObject>)json.get("unfinlist");
                        finlist=(List<JSONObject>)json.get("finlist");
                            %>
                            <form action="/Index/page" method="get">
                                <div style="position:absolute;left:530px;top:50px">
                                    <button name="flush" type="submit"
                                        style="color: rgb(236, 20, 52); width: 100px;height: 100px;">返回主界面</button>
                                </div>
                            </form>
                            <form action="/Trans/page" method="get">
                                <input id="5" type="hidden" name="flush" value="">
                                <div style="position:absolute;left:530px;top:200px">
                                    <button name="flush" type="submit"
                                        style="color: rgb(236, 20, 52); width: 100px;height: 100px;">刷新</button>
                                </div>
                            </form>
                            <br><br><br>
                            <h3 style="font-size: large;text-align: center;">未处理事务</h3>
                            <form name="unfinform" action="/Trans/id" method="post" accept-charset="UTF-8">
                                <input type="hidden" name="_method" value="put" />
                                <input type="hidden" name="transid" value="">
                                <table border="1" align="center" style="width: 1000px;">
                                    <tr>
                                        <th>事务编号</th>
                                        <th>事务类型</th>
                                        <th>事务内容</th>
                                        <th></th>
                                    </tr>

                                    <% for(JSONObject unfin:unfinlist){%>
                                        <tr align="center">
                                            <td>
                                                <%= unfin.get("transid") %>
                                            </td>
                                            <td>
                                                <%= unfin.get("transtype") %>
                                            </td>
                                            <td>
                                                <%= unfin.get("value") %>
                                            </td>
                                            <td>
                                                <% if(unfin.get("transtype").equals("message")){ %>
                                                    <button type="button" onclick="toUnfinTransid(this.name)"
                                                        style="color: brown;"
                                                        name='<%= unfin.get("transid") %>'>收到</button>
                                                    <% } %>
                                                        <% if(unfin.get("transtype").equals("question")){ %>
                                                            <input style="font-size: large;text-align: center;"
                                                                type="text" name='value<%= unfin.get("transid") %>'
                                                                placeholder="回答" required="required" />
                                                            <button type="button" onclick="toUnfinTransid(this.name)"
                                                                name='<%= unfin.get("transid") %>'
                                                                style="color: brown;">提交</button>
                                                            <% } %>
                                            </td>
                                        </tr>
                                        <%}%>
                                </table>
                            </form>


                            <br><br><br>
                            <h3 style="font-size: large;text-align: center;">历史事务</h3>
                            <form name="finform" action="/Trans/history/id" method="post">
                                <input type="hidden" name="_method" value="put" />
                                <input type="hidden" name="transid" value="">
                                <input type="hidden" name="username" value="">
                                <table border="1" align="center" style="width: 1000px;">
                                    <tr>
                                        <th>事务编号</th>
                                        <th>事务类型</th>
                                        <th>成员名</th>
                                        <th>事务内容</th>
                                        <th></th>
                                    </tr>

                                    <% for(JSONObject fin:finlist){%>
                                        <tr align="center">
                                            <td>
                                                <%= fin.get("transid")%>
                                            </td>
                                            <td>
                                                <%= fin.get("transtype") %>
                                            </td>
                                            <td>
                                                <%= fin.get("username") %>
                                            </td>
                                            <td>
                                                <%= fin.get("value") %>
                                            </td>
                                            <td>
                                                <button type="button" onclick="toFinTransid(this.name)"
                                                    style="color: brown;"
                                                    name='<%= fin.get("transid") %>+<%= fin.get("username") %>'>撤销此事务</button>
                                            </td>
                                        </tr>
                                        <%}%>
                                </table>
                            </form>

                            <div align="center">
                                <font size="2"> ${msg}</font>
                            </div>
                </body>

                </html>
                <script>
                    function toUnfinTransid(name) {
                        document.unfinform.transid.value = name;
                        document.unfinform.submit();
                    }
                    function toFinTransid(name) {
                        str = name.split("+");
                        document.finform.transid.value = str[0];
                        document.finform.username.value = str[1];
                        document.finform.submit();
                    }
                </script>