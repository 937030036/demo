<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
    <head>
        <meta charset="UTF-8">
        <title>WebServer</title>
    </head>
    <body>
    <br/>
    <br/>
    <div align="center"><font size="5"> <strong>是时候做出选择了</strong></font></div>
	<br/>
		<br/>
		<form action="Meau/pic" method="post">
 			<div align="center"><button type="submit">显示图片</button></div>
                </form>
		<br/>
                <form action="Meau/video" method="post">
                        <div align="center"><button type="submit" >显示视频</button></div>
                </form>
		<br/>
		<form action="Meau/secret" method="post">
 			<div align="center"><button type="submit">绝密档案</button></div>
                </form>
		
        </div>
        <input type='hidden' value='${data}'>
        <div align="center"><font size="2"> ${data}</font></div>
    </body>
</html>
