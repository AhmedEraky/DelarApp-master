package com.vegeta.my.dealer.model.product;

/**
 * Created by Eraky on 1/18/2019.
 */
import java.io.Serializable;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Product implements Parcelable
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
    @SerializedName("IsAvailable")
    @Expose
    private boolean isAvailable;
    @SerializedName("Days")
    @Expose
    private String days;
    @SerializedName("StartTime")
    @Expose
    private String startTime;
    @SerializedName("EndTime")
    @Expose
    private String endTime;
    @SerializedName("Username")
    @Expose
    private String username;
    @SerializedName("ServiceProviderImages")
    @Expose
    private List<ServiceProviderImage> serviceProviderImages = null;
    @SerializedName("HospitalSpecialists")
    @Expose
    private List<HospitalSpecialist> hospitalSpecialists = null;
    @SerializedName("CafeMenus")
    @Expose
    private List<CafeMenu> cafeMenus = null;
    @SerializedName("Specialist")
    @Expose
    private Specialist specialist;
    public final static Parcelable.Creator<Product> CREATOR = new Creator<Product>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        public Product[] newArray(int size) {
            return (new Product[size]);
        }

    }
            ;
    private final static long serialVersionUID = -560700160655393112L;

    protected Product(Parcel in) {
        this.$id = ((String) in.readValue((String.class.getClassLoader())));
        this.id = ((int) in.readValue((int.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.address = ((String) in.readValue((String.class.getClassLoader())));
        this.mobileNo = ((String) in.readValue((String.class.getClassLoader())));
        this.phoneNo = ((String) in.readValue((String.class.getClassLoader())));
        this.isAcceptedByManager = ((boolean) in.readValue((boolean.class.getClassLoader())));
        this.joinedDate = ((String) in.readValue((String.class.getClassLoader())));
        this.moreInformation = ((String) in.readValue((String.class.getClassLoader())));
        this.location = ((Location) in.readValue((Location.class.getClassLoader())));
        this.isAvailable = ((boolean) in.readValue((boolean.class.getClassLoader())));
        this.days = ((String) in.readValue((String.class.getClassLoader())));
        this.startTime = ((String) in.readValue((String.class.getClassLoader())));
        this.endTime = ((String) in.readValue((String.class.getClassLoader())));
        this.username = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.serviceProviderImages, (java.lang.Object.class.getClassLoader()));
        in.readList(this.hospitalSpecialists, (HospitalSpecialist.class.getClassLoader()));
        in.readList(this.cafeMenus, (CafeMenu.class.getClassLoader()));
        this.specialist = ((Specialist) in.readValue((Specialist.class.getClassLoader())));
    }

    public Product() {
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

    public boolean isIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<ServiceProviderImage> getServiceProviderImages() {
        return serviceProviderImages;
    }

    public void setServiceProviderImages(List<ServiceProviderImage> serviceProviderImages) {
        this.serviceProviderImages = serviceProviderImages;
    }

    public List<HospitalSpecialist> getHospitalSpecialists() {
        return hospitalSpecialists;
    }

    public void setHospitalSpecialists(List<HospitalSpecialist> hospitalSpecialists) {
        this.hospitalSpecialists = hospitalSpecialists;
    }

    public List<CafeMenu> getCafeMenus() {
        return cafeMenus;
    }

    public void setCafeMenus(List<CafeMenu> cafeMenus) {
        this.cafeMenus = cafeMenus;
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
        dest.writeValue(name);
        dest.writeValue(address);
        dest.writeValue(mobileNo);
        dest.writeValue(phoneNo);
        dest.writeValue(isAcceptedByManager);
        dest.writeValue(joinedDate);
        dest.writeValue(moreInformation);
        dest.writeValue(location);
        dest.writeValue(isAvailable);
        dest.writeValue(days);
        dest.writeValue(startTime);
        dest.writeValue(endTime);
        dest.writeValue(username);
        dest.writeList(serviceProviderImages);
        dest.writeList(hospitalSpecialists);
        dest.writeList(cafeMenus);
        dest.writeValue(specialist);
    }

    public int describeContents() {
        return 0;
    }

}