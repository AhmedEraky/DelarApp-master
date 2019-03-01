package com.vegeta.my.dealer.model.product;
import java.io.Serializable;
import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductAddBody implements Parcelable
{

    @SerializedName("Id")
    @Expose
    private int id;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("Address")
    @Expose
    private String address;
    @SerializedName("MobileNo")
    @Expose
    private String mobileNo;
    @SerializedName("PhoneNo")
    @Expose
    private String phoneNo;
    @SerializedName("IsAcceptedByManager")
    @Expose
    private boolean isAcceptedByManager;
    @SerializedName("JoinedDate")
    @Expose
    private String joinedDate;
    @SerializedName("MoreInformation")
    @Expose
    private String moreInformation;
    @SerializedName("Location")
    @Expose
    private Location location;
    @SerializedName("Username")
    @Expose
    private String username;
    @SerializedName("IsAvailable")
    @Expose
    private boolean isAvailable;
    @SerializedName("SpecialistId")
    @Expose
    private int specialistId;
    @SerializedName("Specialist")
    @Expose
    private Specialist specialist;
    @SerializedName("Days")
    @Expose
    private String days;
    @SerializedName("StartTime")
    @Expose
    private String startTime;
    @SerializedName("EndTime")
    @Expose
    private String endTime;
    @SerializedName("HospitalSpecialists")
    @Expose
    private List<AddHospitalSpecialist> addHospitalSpecialist = null;

    public final static Parcelable.Creator<ProductAddBody> CREATOR = new Creator<ProductAddBody>() {


        @SuppressWarnings({
                "unchecked"
        })
        public ProductAddBody createFromParcel(Parcel in) {
            return new ProductAddBody(in);
        }

        public ProductAddBody[] newArray(int size) {
            return (new ProductAddBody[size]);
        }

    };
    private final static long serialVersionUID = -4480943430055735852L;


    protected ProductAddBody(Parcel in) {
        this.id = ((int) in.readValue((int.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.address = ((String) in.readValue((String.class.getClassLoader())));
        this.mobileNo = ((String) in.readValue((String.class.getClassLoader())));
        this.phoneNo = ((String) in.readValue((String.class.getClassLoader())));
        this.isAcceptedByManager = ((boolean) in.readValue((boolean.class.getClassLoader())));
        this.joinedDate = ((String) in.readValue((String.class.getClassLoader())));
        this.moreInformation = ((String) in.readValue((String.class.getClassLoader())));
        this.location = ((Location) in.readValue((Location.class.getClassLoader())));
        this.username = ((String) in.readValue((String.class.getClassLoader())));
        this.isAvailable = ((boolean) in.readValue((boolean.class.getClassLoader())));
        this.specialistId = ((int) in.readValue((int.class.getClassLoader())));
        this.specialist = ((Specialist) in.readValue((Specialist.class.getClassLoader())));
        this.days = ((String) in.readValue((String.class.getClassLoader())));
        this.startTime = ((String) in.readValue((String.class.getClassLoader())));
        this.endTime = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.addHospitalSpecialist, (AddHospitalSpecialist.class.getClassLoader()));
    }

    public ProductAddBody() {
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public boolean isIsAcceptedByManager() {
        return isAcceptedByManager;
    }

    public void setIsAcceptedByManager(boolean isAcceptedByManager) {
        this.isAcceptedByManager = isAcceptedByManager;
    }

    public String getJoinedDate() {
        return joinedDate;
    }

    public void setJoinedDate(String joinedDate) {
        this.joinedDate = joinedDate;
    }

    public String getMoreInformation() {
        return moreInformation;
    }

    public void setMoreInformation(String moreInformation) {
        this.moreInformation = moreInformation;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
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

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public boolean isAcceptedByManager() {
        return isAcceptedByManager;
    }

    public void setAcceptedByManager(boolean acceptedByManager) {
        isAcceptedByManager = acceptedByManager;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public List<AddHospitalSpecialist> getAddHospitalSpecialist() {
        return addHospitalSpecialist;
    }

    public void setAddHospitalSpecialist(List<AddHospitalSpecialist> addHospitalSpecialist) {
        this.addHospitalSpecialist = addHospitalSpecialist;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(name);
        dest.writeValue(address);
        dest.writeValue(mobileNo);
        dest.writeValue(phoneNo);
        dest.writeValue(isAcceptedByManager);
        dest.writeValue(joinedDate);
        dest.writeValue(moreInformation);
        dest.writeValue(location);
        dest.writeValue(username);
        dest.writeValue(isAvailable);
        dest.writeValue(specialistId);
        dest.writeValue(specialist);
        dest.writeValue(days);
        dest.writeValue(startTime);
        dest.writeValue(endTime);
        dest.writeList(addHospitalSpecialist);
    }

    public int describeContents() {
        return 0;
    }

}