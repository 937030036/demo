# DataBase Schema Design

----

1. user

|             | userid |  username   | userpassword |
| :---------: | :----: | :---------: | :----------: |
| Primary Key |   Y    |             |              |
|    Type     |  int   | varchar(20) | varchar(20)  |
|     Ref     |        |             |              |

* desc:Table storing registered user's infomation for login verification.

---

2. team

|             | teamid |  teamname   | teampassword |
| :---------: | :----: | :---------: | :----------: |
| Primary Key |   Y    |             |              |
|    Type     |  int   | varchar(20) | varchar(20)  |
|     Ref     |        |             |              |

* desc:Table storing registered team's information. 

---

3. user_info
   
|             |    userid    |    teamid    | isleader |
| :---------: | :----------: | :----------: | :------: |
| Primary Key |      Y       |      Y       |          |
|    Type     |     int      |     int      | Boolean  |
|     Ref     | user(userid) | team(teamid) |          |

* Table storing relations of users and teams.

---

4. trans

|             | transid |    teamid    |  transtype  |    value     |
| :---------: | :-----: | :----------: | :---------: | :----------: |
| Primary Key |    Y    |              |             |              |
|    Type     |   int   |     int      | varchar(20) | varchar(200) |
|     Ref     |         | team(teamid) |             |              |

* Table storing registered transactions information.
* transtype in(message,question,file,table)

---

5. trans_handle

|             |    transid     |    userid    | ishandled |    value     |
| :---------: | :------------: | :----------: | :-------: | :----------: |
| Primary Key |                |              |           |              |
|    Type     |      int       |     int      |  Boolean  | varchar(200) |
|     Ref     | trans(transid) | user(userid) |           |              |

* Table storing transactions records distributed all users.