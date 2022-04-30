<!--
 * @Description: 
 * @Version: 
 * @Autor: Zhangchunhao
 * @Date: 2022-04-29 09:47:26
 * @LastEditors: Zhanchunhao
 * @LastEditTime: 2022-04-29 09:50:45
-->
# SignService测试
测试用userList

|user|userid|username|password|
|--|--|--|--|

|user1|55|aa|bb|
|user2|56|cc|dd|
|user3|57|ee|ff|
## signinHandleService测试lll



|测试name|测试password|期望输出|结果|
|--|--|--|--|

|aa|bb|Msg.SIGNIN_SUCC|Msg.SIGNIN_SUCC|
|aa|ba|Msg.PASSWORD_WRONG|Msg.PASSWORD_WRONG|
|ae|bb|Msg.USERNAME_NOEXIST|Msg.USERNAME_NOEXIST|

## signupHandleService测试


|测试name|测试password|期望输出|实际输出|
|--|--|--|--|
|gg|hh|Msg.SIGNUP_SUCC|Msg.SIGNUP_SUCC|
|cc|hh|Msg.USERNAME_EXIST|Msg.USERNAME_EXIST|

# TransService测试
测试用transList
|TransId|TeamId|TransType|TransValue|
|--|--|--|--|
|1|2|aa|aaaa|
|3|2|aa|bbbb|
|4|3|cc|ddd|


测试用TeamList
|TeamId|TeamName|TeamPassword|
|--|--|--|
|1|X|UUU|
|2|Y|VVV|
|3|Z|WWW|
测试用User
|UserId|UserName|UserPassWord|
|--|--|--|
|1|11|111|
|2|22|222|
|3|33|333|

测试用UserInfo
|Userid|TeamId|Isleader|
|--|--|--|
|1|1|True|
|2|1|false|
|3|3|false|


## LaunchTransService测试
|user|入参TeamName|入参Value|入参TransType|期望输出|实际输出|
|--|--|--|--|--|--|
|1|X|ac|ad|Msg.LAUNCHTRANS_SUCC  Trans.TeamId=1  Trans.Transid=2  Trans.TranceType=ad  Trance.Value=ac|Msg.LAUNCHTRANS_SUCC Trans.TeamId=1 Trans.Transid=2 Trans.TranceType=ad Trance.Value=ac|
|3|Z|bc|bd|Msg.LAUNCHTRANS_FAIL|Msg.LAUNCHTRANS_FAIL|

## TransHandleService测试
|user|入参Transid|入参value|期望输出|实际输出|
|--|--|--|--|--|
|2|1|aaaa|Msg.TRANSHANDLE_SUCC|Msg.TRANSHANDLE_SUCC|
## TransHistoryService测试
|user|入参transidstr|期望输出|实际输出|
|--|--|--|--|
|2|1|Msg.HISTORYTRANS_SUCC|Msg.HISTORYTRANS_SUCC|
# TeamService测试
测试用TeamList
|TeamId|TeamName|TeamPassword|
|--|--|--|
|2|22|222|
|3|33|333|
测试用UserList
|UserId|UserName|UserPassword|
|--|--|--|
|1|a|aa|
|2|b|bb|
测试用UserInfolist
|UserId|TeamId|IsLeader|
|--|--|--|
|1|2|true|
|2|3|false|
## RegisterTeamService测试
|user|入参Teamname|入参TeamPassword|期望输出|实际输出|
|--|--|--|--|--|

