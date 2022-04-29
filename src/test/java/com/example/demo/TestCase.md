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


|测试name|测试password|期望输出|结果|
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
|1|X|ac|ad|Msg.LAUNCHTRANS_SUCC  Trans.TeamId=1  Trans.Transid=2  Trans.TranceType=ad  Trance.Value=ac||
|3|Z|bc|bd|Msg.LAUNCHTRANS_FAIL||

