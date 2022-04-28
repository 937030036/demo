# SignServiceTest
测试用userList

|user|userid|username|password|
|--|--|--|--|

|user1|55|aa|bb|
|user2|56|cc|dd|
|user3|57|ee|ff|
## signinHandleService测试



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



