package com.zhuanghou.videos.web.model;

/**

 * Created by duhui on 2017/11/23.
 */
public class LoginResult {
    private int code;
    private String message;


    public LoginResult(int code, String message) {
        this.code = code;
        this.message = message;
    }



    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
