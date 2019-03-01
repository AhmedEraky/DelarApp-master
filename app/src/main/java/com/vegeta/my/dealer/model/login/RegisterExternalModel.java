package com.vegeta.my.dealer.model.login;

public class RegisterExternalModel {


    private String UserName ;
    private String Email ;
    private String imgurl;
    private String firstName;
    private String SecondName ;
    private String Provider;
    private String ExternalAccessToken ;

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return SecondName;
    }

    public void setSecondName(String secondName) {
        SecondName = secondName;
    }

    public String getProvider() {
        return Provider;
    }

    public void setProvider(String provider) {
        Provider = provider;
    }

    public String getExternalAccessToken() {
        return ExternalAccessToken;
    }

    public void setExternalAccessToken(String externalAccessToken) {
        ExternalAccessToken = externalAccessToken;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}
