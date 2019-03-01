package com.vegeta.my.dealer.model.login;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserInfoResponse implements Parcelable
{

    @SerializedName("$id")
    @Expose
    private String $id;
    @SerializedName("Id")
    @Expose
    private String id;
    @SerializedName("HasRegistered")
    @Expose
    private boolean hasRegistered;
    @SerializedName("ImageURl")
    @Expose
    private String imageURl;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("LoginProvider")
    @Expose
    private Object loginProvider;
    @SerializedName("IsProvider")
    @Expose
    private boolean isProvider;
    public final static Parcelable.Creator<UserInfoResponse> CREATOR = new Creator<UserInfoResponse>() {


        @SuppressWarnings({
                "unchecked"
        })
        public UserInfoResponse createFromParcel(Parcel in) {
            return new UserInfoResponse(in);
        }

        public UserInfoResponse[] newArray(int size) {
            return (new UserInfoResponse[size]);
        }

    }
            ;
    private final static long serialVersionUID = -3370565949738575302L;

    protected UserInfoResponse(Parcel in) {
        this.$id = ((String) in.readValue((String.class.getClassLoader())));
        this.id = ((String) in.readValue((String.class.getClassLoader())));
        this.hasRegistered = ((boolean) in.readValue((boolean.class.getClassLoader())));
        this.imageURl = ((String) in.readValue((String.class.getClassLoader())));
        this.username = ((String) in.readValue((String.class.getClassLoader())));
        this.loginProvider = ((Object) in.readValue((Object.class.getClassLoader())));
        this.isProvider = ((boolean) in.readValue((boolean.class.getClassLoader())));
    }

    public UserInfoResponse() {
    }

    public String get$id() {
        return $id;
    }

    public void set$id(String $id) {
        this.$id = $id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isHasRegistered() {
        return hasRegistered;
    }

    public void setHasRegistered(boolean hasRegistered) {
        this.hasRegistered = hasRegistered;
    }

    public String getImageURl() {
        return imageURl;
    }

    public void setImageURl(String imageURl) {
        this.imageURl = imageURl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Object getLoginProvider() {
        return loginProvider;
    }

    public void setLoginProvider(Object loginProvider) {
        this.loginProvider = loginProvider;
    }

    public boolean isIsProvider() {
        return isProvider;
    }

    public void setIsProvider(boolean isProvider) {
        this.isProvider = isProvider;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue($id);
        dest.writeValue(id);
        dest.writeValue(hasRegistered);
        dest.writeValue(imageURl);
        dest.writeValue(username);
        dest.writeValue(loginProvider);
        dest.writeValue(isProvider);
    }

    public int describeContents() {
        return 0;
    }

}