package com.vegeta.my.dealer.model.product;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UploadImageResponse implements Parcelable
{

    @SerializedName("$id")
    @Expose
    private String $id;
    @SerializedName("Message")
    @Expose
    private String message;
    public final static Parcelable.Creator<UploadImageResponse> CREATOR = new Creator<UploadImageResponse>() {


        @SuppressWarnings({
                "unchecked"
        })
        public UploadImageResponse createFromParcel(Parcel in) {
            return new UploadImageResponse(in);
        }

        public UploadImageResponse[] newArray(int size) {
            return (new UploadImageResponse[size]);
        }

    }
            ;
    private final static long serialVersionUID = -1610422882892649076L;

    protected UploadImageResponse(Parcel in) {
        this.$id = ((String) in.readValue((String.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
    }

    public UploadImageResponse() {
    }

    public String get$id() {
        return $id;
    }

    public void set$id(String $id) {
        this.$id = $id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue($id);
        dest.writeValue(message);
    }

    public int describeContents() {
        return 0;
    }

}