package com.vegeta.my.dealer.model.product;
import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CafeMenu implements Serializable, Parcelable
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
    @SerializedName("CafeId")
    @Expose
    private int cafeId;
    @SerializedName("Cafe")
    @Expose
    private String cafe;
    public final static Parcelable.Creator<CafeMenu> CREATOR = new Creator<CafeMenu>() {


        @SuppressWarnings({
                "unchecked"
        })
        public CafeMenu createFromParcel(Parcel in) {
            return new CafeMenu(in);
        }

        public CafeMenu[] newArray(int size) {
            return (new CafeMenu[size]);
        }

    }
            ;
    private final static long serialVersionUID = -495280029072687559L;

    protected CafeMenu(Parcel in) {
        this.$id = ((String) in.readValue((String.class.getClassLoader())));
        this.id = ((int) in.readValue((int.class.getClassLoader())));
        this.imageUrl = ((String) in.readValue((String.class.getClassLoader())));
        this.cafeId = ((int) in.readValue((int.class.getClassLoader())));
        this.cafe = ((String) in.readValue((Object.class.getClassLoader())));
    }

    public CafeMenu() {
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

    public int getCafeId() {
        return cafeId;
    }

    public void setCafeId(int cafeId) {
        this.cafeId = cafeId;
    }

    public String getCafe() {
        return cafe;
    }

    public void setCafe(String cafe) {
        this.cafe = cafe;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue($id);
        dest.writeValue(id);
        dest.writeValue(imageUrl);
        dest.writeValue(cafeId);
        dest.writeValue(cafe);
    }

    public int describeContents() {
        return 0;
    }

}