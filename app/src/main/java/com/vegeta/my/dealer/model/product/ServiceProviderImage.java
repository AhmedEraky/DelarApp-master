package com.vegeta.my.dealer.model.product;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ServiceProviderImage implements Parcelable
{

    @SerializedName("$id")
    @Expose
    private String $id;
    @SerializedName("Id")
    @Expose
    private int id;
    @SerializedName("ImageUrl")
    @Expose
    private String imageUrl;
    @SerializedName("ServiceProviderId")
    @Expose
    private int serviceProviderId;
    public final static Parcelable.Creator<ServiceProviderImage> CREATOR = new Creator<ServiceProviderImage>() {


        @SuppressWarnings({
                "unchecked"
        })
        public ServiceProviderImage createFromParcel(Parcel in) {
            return new ServiceProviderImage(in);
        }

        public ServiceProviderImage[] newArray(int size) {
            return (new ServiceProviderImage[size]);
        }

    }
            ;
    private final static long serialVersionUID = 6913150759583409603L;

    protected ServiceProviderImage(Parcel in) {
        this.$id = ((String) in.readValue((String.class.getClassLoader())));
        this.id = ((int) in.readValue((int.class.getClassLoader())));
        this.imageUrl = ((String) in.readValue((String.class.getClassLoader())));
        this.serviceProviderId = ((int) in.readValue((int.class.getClassLoader())));
    }

    public ServiceProviderImage() {
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getServiceProviderId() {
        return serviceProviderId;
    }

    public void setServiceProviderId(int serviceProviderId) {
        this.serviceProviderId = serviceProviderId;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue($id);
        dest.writeValue(id);
        dest.writeValue(imageUrl);
        dest.writeValue(serviceProviderId);
    }

    public int describeContents() {
        return 0;
    }

}