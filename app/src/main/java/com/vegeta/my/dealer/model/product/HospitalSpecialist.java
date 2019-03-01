package com.vegeta.my.dealer.model.product;
import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HospitalSpecialist implements Parcelable
{

    @SerializedName("$id")
    @Expose
    private String $id;
    @SerializedName("Id")
    @Expose
    private int id;
    @SerializedName("HospitalId")
    @Expose
    private int hospitalId;
    @SerializedName("Hospital")
    @Expose
    private String hospital;
    @SerializedName("SpecialistId")
    @Expose
    private int specialistId;
    @SerializedName("Specialist")
    @Expose
    private Specialist specialist;
    public final static Parcelable.Creator<HospitalSpecialist> CREATOR = new Creator<HospitalSpecialist>() {


        @SuppressWarnings({
                "unchecked"
        })
        public HospitalSpecialist createFromParcel(Parcel in) {
            return new HospitalSpecialist(in);
        }

        public HospitalSpecialist[] newArray(int size) {
            return (new HospitalSpecialist[size]);
        }

    }
            ;
    private final static long serialVersionUID = 8380319312291923394L;

    protected HospitalSpecialist(Parcel in) {
        this.$id = ((String) in.readValue((String.class.getClassLoader())));
        this.id = ((int) in.readValue((int.class.getClassLoader())));
        this.hospitalId = ((int) in.readValue((int.class.getClassLoader())));
        this.hospital = ((String) in.readValue((Object.class.getClassLoader())));
        this.specialistId = ((int) in.readValue((int.class.getClassLoader())));
        this.specialist = ((Specialist) in.readValue((Specialist.class.getClassLoader())));

    }

    public HospitalSpecialist() {
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

    public int getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(int hospitalId) {
        this.hospitalId = hospitalId;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public int getSpecialistId() {
        return specialistId;
    }

    public void setSpecialistId(int specialistId) {
        this.specialistId = specialistId;
    }

    public Specialist getSpecialist() {
        return specialist;
    }

    public void setSpecialist(Specialist specialist) {
        this.specialist = specialist;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue($id);
        dest.writeValue(id);
        dest.writeValue(hospitalId);
        dest.writeValue(hospital);
        dest.writeValue(specialistId);
        dest.writeValue(specialist);
    }

    public int describeContents() {
        return 0;
    }

}