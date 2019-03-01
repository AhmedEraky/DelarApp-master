package com.vegeta.my.dealer.model.product;
import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddHospitalSpecialist implements Parcelable
{

    @SerializedName("SpecialistId")
    @Expose
    private int specialistId;
    public final static Parcelable.Creator<AddHospitalSpecialist> CREATOR = new Creator<AddHospitalSpecialist>() {


        @SuppressWarnings({
                "unchecked"
        })
        public AddHospitalSpecialist createFromParcel(Parcel in) {
            return new AddHospitalSpecialist(in);
        }

        public AddHospitalSpecialist[] newArray(int size) {
            return (new AddHospitalSpecialist[size]);
        }

    }
            ;
    private final static long serialVersionUID = -1551540512119542303L;

    protected AddHospitalSpecialist(Parcel in) {
        this.specialistId = ((int) in.readValue((int.class.getClassLoader())));
    }

    public AddHospitalSpecialist() {
    }

    public int getSpecialistId() {
        return specialistId;
    }

    public void setSpecialistId(int specialistId) {
        this.specialistId = specialistId;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(specialistId);
    }

    public int describeContents() {
        return 0;
    }

}
