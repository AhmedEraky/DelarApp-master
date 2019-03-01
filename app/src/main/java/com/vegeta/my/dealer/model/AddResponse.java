package com.vegeta.my.dealer.model;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddResponse implements Parcelable
{

    @SerializedName("$id")
    @Expose
    private String $id;
    @SerializedName("Id")
    @Expose
    private int id;
    public final static Parcelable.Creator<AddResponse> CREATOR = new Creator<AddResponse>() {


        @SuppressWarnings({
                "unchecked"
        })
        public AddResponse createFromParcel(Parcel in) {
            return new AddResponse(in);
        }

        public AddResponse[] newArray(int size) {
            return (new AddResponse[size]);
        }

    }
            ;
    private final static long serialVersionUID = 7668106460296174337L;

    protected AddResponse(Parcel in) {
        this.$id = ((String) in.readValue((String.class.getClassLoader())));
        this.id = ((int) in.readValue((int.class.getClassLoader())));
    }

    public AddResponse() {
    }

    public String get$id() {
        return $id;
    }

    public void set$id(String $id) {
        this.$id = $id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue($id);
        dest.writeValue(id);
    }

    public int describeContents() {
        return 0;
    }

}