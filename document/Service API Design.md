<!--
 * @Description: 
 * @Version: 
 * @Autor: Zhangchunhao
 * @Date: 2022-04-23 14:57:45
 * @LastEditors: Zhanchunhao
 * @LastEditTime: 2022-04-28 21:19:49
-->
# Service API Design

## 界面相关服务

* IndexPageService

|      入参       |                      出参                       | 返回值 |      描述      |
| :-------------: | :---------------------------------------------: | :----: | :------------: |
| request(userid) | request(data(json){teamlist,unfintranslistnum}) |  Msg   | 用户主界面数据 |


* TransPageService

|      入参       |                       出参                       | 返回值 |       描述       |
| :-------------: | :----------------------------------------------: | :----: | :--------------: |
| request(userid) | request(data(json){fintranslist,unfintranslist}) |  Msg   | 事务处理界面数据 |

* TeamMemPageService

|      入参       |             出参              | 返回值 |       描述       |
| :-------------: | :---------------------------: | :----: | :--------------: |
| request(userid) | request(data(json){namelist}) |  Msg   | 团队成员界面数据 |

---
## 团队相关服务

* RegisterTeamService

|              入参               |  出参   | 返回值 |   描述   |
| :-----------------------------: | :-----: | :----: | :------: |
| request(userid,tname,tpassword) | request |  Msg   | 注册团队 |

* JoinTeamService

|              入参               |  出参   | 返回值 |   描述   |
| :-----------------------------: | :-----: | :----: | :------: |
| request(userid,tname,tpassword) | request |  Msg   | 加入团队 |

* DisbandTeamService

|              入参               |  出参   | 返回值 |   描述   |
| :-----------------------------: | :-----: | :----: | :------: |
| request(userid,tname,tpassword) | request |  Msg   | 解散团队 |

---
## 事务处理服务

* LaunchTransService

|                 入参                 |  出参   | 返回值 |   描述   |
| :----------------------------------: | :-----: | :----: | :------: |
| request(userid,tname,trantype,value) | request |  Msg   | 发起事务 |

* TransHandleService

|             入参              |  出参   | 返回值 |   描述   |
| :---------------------------: | :-----: | :----: | :------: |
| request(transid,userid,value) | request |  Msg   | 处理事务 |

* TransHistoryService

|          入参           |  出参   | 返回值 |     描述     |
| :---------------------: | :-----: | :----: | :----------: |
| request(userid,transid) | request |  Msg   | 更改历史事务 |

## 签处理服务

* signinHandleService

|            入参            |                     出参                     | 返回值 |   描述   |
| :------------------------: | :------------------------------------------: | :----: | :------: |
| request(username,password) | request(user(obj){userid,username,password}) |  Msg   | 签入认证 |

* signupHandleService

|            入参            |  出参   | 返回值 |   描述   |
| :------------------------: | :-----: | :----: | :------: |
| request(username,password) | request |  Msg   | 注册认证 |