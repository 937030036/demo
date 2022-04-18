# 团队在线表格

* 平台：Windows
* 框架：Spring Boot
* java版本：18
* 相关依赖详见[maven依赖文件][1]

----

# DemoApplication 

* 项目启动项

----

# BeanUtils  

* 当含有注入项的接口类被其他组件注入时不得实例化，而不实例化又会导致接口类为空指针，所以在这里实现了一个手动装载Bean库文件，以解决这个问题。


[1]:/pom.xml