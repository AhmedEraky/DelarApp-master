package com.vegeta.my.dealer.model.chat;


import com.vegeta.my.dealer.model.login.LoginResponse;


/**
 * Created by Eraky on 1/18/2019.
 */

public class ChatModel {

    private String id;
    private LoginResponse userModel;
    private String message;
    private String timeStamp;

    public ChatModel() {
    }

    public ChatModel(LoginResponse userModel, String message, String timeStamp) {
        this.userModel = userModel;
        this.message = message;
        this.timeStamp = timeStamp;
    }

    public ChatModel(LoginResponse userModel, String timeStamp) {
        this.userModel = userModel;
        this.timeStamp = timeStamp;
    }

    public LoginResponse getUserModel() {
        return userModel;
    }

    public void setUserModel(LoginResponse userModel) {
        this.userModel = userModel;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    @Override
    public String toString() {
        return "ChatModel{" +
                ", timeStamp='" + timeStamp + '\'' +
                ", message='" + message + '\'' +
                ", userModel=" + userModel +
                '}';
    }
}
