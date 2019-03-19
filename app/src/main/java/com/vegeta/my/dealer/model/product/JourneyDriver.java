package com.vegeta.my.dealer.model.product;
import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class JourneyDriver implements  Parcelable
{

    @SerializedName("$id")
    @Expose
    public String $id;
    @SerializedName("Id")
    @Expose
    public String id;
    @SerializedName("Name")
    @Expose
    public String name;
    @SerializedName("UserName")
    @Expose
    public String userName;
    @SerializedName("Address")
    @Expose
    public Object address;
    @SerializedName("PhoneNo")
    @Expose
    public String phoneNo;
    @SerializedName("mageUrl")
    @Expose
    public Object mageUrl;
    public final static Parcelable.Creator<JourneyDriver> CREATOR = new Creator<JourneyDriver>() {


        @SuppressWarnings({
                "unchecked"
        })
        public JourneyDriver createFromParcel(Parcel in) {
            return new JourneyDriver(in);
        }

        public JourneyDriver[] newArray(int size) {
            return (new JourneyDriver[size]);
        }

    }
            ;
    private final static long serialVersionUID = 1627268062155981457L;

    protected JourneyDriver(Parcel in) {
        this.$id = ((String) in.readValue((String.class.getClassLoader())));
        this.id = ((String) in.readValue((String.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.userName = ((String) in.readValue((String.class.getClassLoader())));
        this.address = ((Object) in.readValue((Object.class.getClassLoader())));
        this.phoneNo = ((String) in.readValue((String.class.getClassLoader())));
        this.mageUrl = ((Object) in.readValue((Object.class.getClassLoader())));
    }

    public JourneyDriver() {
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue($id);
        dest.writeValue(id);
        dest.writeValue(name);
        dest.writeValue(userName);
        dest.writeValue(address);
        dest.writeValue(phoneNo);
        dest.writeValue(mageUrl);
    }

    public int describeContents() {
        return 0;
    }

}