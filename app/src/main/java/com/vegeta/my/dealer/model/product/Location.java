package com.vegeta.my.dealer.model.product;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Location implements  Parcelable
{

    @SerializedName("$id")
    @Expose
    private String $id;
    @SerializedName("Longitude")
    @Expose
    private float longitude;
    @SerializedName("Latitude")
    @Expose
    private float latitude;
    public final static Parcelable.Creator<Location> CREATOR = new Creator<Location>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Location createFromParcel(Parcel in) {
            return new Location(in);
        }

        public Location[] newArray(int size) {
            return (new Location[size]);
        }

    }
            ;
    private final static long serialVersionUID = 2907711528855439103L;

    protected Location(Parcel in) {
        this.$id = ((String) in.readValue((String.class.getClassLoader())));
        this.longitude = ((float) in.readValue((float.class.getClassLoader())));
        this.latitude = ((float) in.readValue((float
                .class.getClassLoader())));
    }

    public Location() {
    }

    public String get$id() {
        return $id;
    }

    public void set$id(String $id) {
        this.$id = $id;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue($id);
        dest.writeValue(longitude);
        dest.writeValue(latitude);
    }

    public int describeContents() {
        return 0;
    }

}