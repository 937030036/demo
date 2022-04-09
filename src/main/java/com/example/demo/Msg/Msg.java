package com.example.demo.Msg;

public enum Msg {
    SIGNIN_SUCC("./users/msg/signinsucc.js"),
    SIGNUP_SUCC("./users/msg/signupsucc.js"),
    USERNAME_EXIST("./users/msg/unameexist.js"),
    USERNAME_NOEXIST("./users/msg/unamenoexist.js"),
    PASSWORD_WRONG("./users/msg/wrongpsw.js"),
    NO_MSG("./users/msg/nomsg.js");

    private String sourcepath;

    private Msg(String msg) {
        sourcepath = new String(msg);
    }

    public String getpath() {
        return sourcepath;
    }
}
