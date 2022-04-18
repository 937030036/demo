# Controller

* 控制层
* 对客户端请求路由控制，调用对应服务层

---

## WelcomeCtrler

* 欢迎界面的路由控制
* 此界面不经过服务层控制

---

## UserSignCtler

* 签入签出界面路由控制
* 调用[对应服务层接口][1]处理业务，并拿到返回消息
* 根据服务层的返回消息，处理response

----

## UserMeauCtler

* 菜单界面路由控制
* 对图片和视频等资源文件实体返回业务(**此部分应在服务层处理**)  


[1]:../Service/interfaces/SignService.java