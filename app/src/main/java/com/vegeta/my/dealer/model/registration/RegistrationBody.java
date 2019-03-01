package com.vegeta.my.dealer.model.registration;

/**
 * Created by Eraky on 1/19/2019.
 */

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegistrationBody implements Serializable, Parcelable
{

    @SerializedName("Email")
    @Expose
    private String email;
    @SerializedName("UserName")
    @Expose
    private String userName;
    @SerializedName("IsProvider")
    @Expose
    private boolean isProvider;
    @SerializedName("Password")
    @Expose
    private String password;
    @SerializedName("ConfirmPassword")
    @Expose
    private String confirmPassword;
    public final static Parcelable.Creator<RegistrationBody> CREATOR = new Creator<RegistrationBody>() {


        @SuppressWarnings({
                "unchecked"
        })
        public RegistrationBody createFromParcel(Parcel in) {
            return new RegistrationBody(in);
        }

        public RegistrationBody[] newArray(int size) {
            return (new RegistrationBody[size]);
        }

    }
            ;
    private final static long serialVersionUID = 4501833916058029146L;

    protected RegistrationBody(Parcel in) {
        this.email = ((String) in.readValue((String.class.getClassLoader())));
        this.userName = ((String) in.readValue((String.class.getClassLoader())));
        this.isProvider = ((boolean) in.readValue((boolean.class.getClassLoader())));
        this.password = ((String) in.readValue((String.class.getClassLoader())));
        this.confirmPassword = ((String) in.readValue((String.class.getClassLoader())));
    }

    public RegistrationBody() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean isIsProvider() {
        return isProvider;
    }

    public void setIsProvider(boolean isProvider) {
        this.isProvider = isProvider;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(email);
        dest.writeValue(userName);
        dest.writeValue(isProvider);
        dest.writeValue(password);
        dest.writeValue(confirmPassword);
    }

    public int describeContents() {
        return 0;
    }

}