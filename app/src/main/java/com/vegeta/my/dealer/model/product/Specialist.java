package com.vegeta.my.dealer.model.product;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Specialist implements  Parcelable
{

    @SerializedName("$id")
    @Expose
    private String $id;
    @SerializedName("Id")
    @Expose
    private int id;
    @SerializedName("Name")
    @Expose
    private String name;
    public final static Parcelable.Creator<Specialist> CREATOR = new Creator<Specialist>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Specialist createFromParcel(Parcel in) {
            return new Specialist(in);
        }

        public Specialist[] newArray(int size) {
            return (new Specialist[size]);
        }

    }
            ;
    private final static long serialVersionUID = -6642107478277143693L;

    protected Specialist(Parcel in) {
        this.$id = ((String) in.readValue((String.class.getClassLoader())));
        this.id = ((int) in.readValue((int.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Specialist() {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue($id);
        dest.writeValue(id);
        dest.writeValue(name);
    }

    public int describeContents() {
        return 0;
    }

}