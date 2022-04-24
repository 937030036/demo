# Service API Design

## 界面相关服务

* IndexPageService

|     入参      |                出参                 | 返回值 |      描述      |
| :-----------: | :---------------------------------: | :----: | :------------: |
| request(userid) | request(teamlist,unfintranslistnum) |  Msg   | 用户主界面数据 |


* TransPageService

|     入参      |                 出参                 | 返回值 |       描述       |
| :-----------: | :----------------------------------: | :----: | :--------------: |
| request(userid) | request(fintranslist,unfintranslist) |  Msg   | 事务处理界面数据 |

* TeamMemPageService

|     入参      |       出参        | 返回值 |       描述       |
| :-----------: | :---------------: | :----: | :--------------: |
| request(userid) | request(userlist) |  Msg   | 团队成员界面数据 |

---
## 团队相关服务

* RegisterTeamService

|           入参           |  出参   | 返回值 |   描述   |
| :----------------------: | :-----: | :----: | :------: |
| request(userid,tname,tpassword) | request |  Msg   | 注册团队 |

* JoinTeamService

|           入参           |  出参   | 返回值 |   描述   |
| :----------------------: | :-----: | :----: | :------: |
| request(userid,tname,tpassword) | request |  Msg   | 加入团队 |

* DisbandTeamService

|           入参           |  出参   | 返回值 |   描述   |
| :----------------------: | :-----: | :----: | :------: |
| request(userid,tname,tpassword) | request |  Msg   | 解散团队 |

---
## 事务处理服务

* LaunchTransService

|             入参              |  出参   | 返回值 |   描述   |
| :---------------------------: | :-----: | :----: | :------: |
| request(userid,tname,trantype,value) | request |  Msg   | 发起事务 |

* TransHandleService

|      入参      |  出参   | 返回值 |   描述   |
| :------------: | :-----: | :----: | :------: |
| request(transid,userid,value) | request |  Msg   | 处理事务 |

* TransHistoryService

|     入参      |          出参           | 返回值 |   描述   |
| :-----------: | :---------------------: | :----: | :------: |
| request(userid,transid) | request |  Msg   | 更改历史事务 |