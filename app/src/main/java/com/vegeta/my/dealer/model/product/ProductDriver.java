package com.vegeta.my.dealer.model.product;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductDriver implements  Parcelable {

    @SerializedName("$id")
    @Expose
    public String $id;
    @SerializedName("Id")
    @Expose
    public int id;
    @SerializedName("SourceAdress")
    @Expose
    public String sourceAdress;
    @SerializedName("launchTime")
    @Expose
    public String launchTime;
    @SerializedName("DestinationAddress")
    @Expose
    public String destinationAddress;
    @SerializedName("EndTime")
    @Expose
    public String endTime;
    @SerializedName("SourceLocation")
    @Expose
    public Location sourceLocation;
    @SerializedName("DestinationLocation")
    @Expose
    public Location destinationLocation;
    @SerializedName("launchDate")
    @Expose
    public String launchdate;
    @SerializedName("DriverName")
    @Expose
    public String DriverName;
    @SerializedName("NumOfSeats")
    @Expose
    public int numOfSeats;
    @SerializedName("CostPerSeat")
    @Expose
    public int costPerSeat;
    @SerializedName("ExpectedCost")
    @Expose
    public String expectedCost;
    @SerializedName("ExpectedDistance")
    @Expose
    public String expectedDistance;
    @SerializedName("ApplicationUerId")
    @Expose
    public String applicationUerId;
    @SerializedName("JourneyDriver")
    @Expose
    public JourneyDriver journeyDriver;


    public String getDriverName() {
        return DriverName;
    }

    public void setDriverName(String driverName) {
        DriverName = driverName;
    }

    public final static Parcelable.Creator<ProductDriver> CREATOR = new Creator<ProductDriver>() {


        @SuppressWarnings({
                "unchecked"
        })
        public ProductDriver createFromParcel(Parcel in) {
            return new ProductDriver(in);
        }

        public ProductDriver[] newArray(int size) {
            return (new ProductDriver[size]);
        }

    };
    private final static long serialVersionUID = -7346651200117859880L;

    protected ProductDriver(Parcel in) {
        this.$id = ((String) in.readValue((String.class.getClassLoader())));
        this.id = ((int) in.readValue((int.class.getClassLoader())));
        this.sourceAdress = ((String) in.readValue((String.class.getClassLoader())));
        this.launchTime = ((String) in.readValue((String.class.getClassLoader())));
        this.destinationAddress = ((String) in.readValue((String.class.getClassLoader())));
        this.endTime = ((String) in.readValue((String.class.getClassLoader())));
        this.sourceLocation = ((Location) in.readValue((Location.class.getClassLoader())));
        this.destinationLocation = ((Location) in.readValue((Location.class.getClassLoader())));
        this.launchdate = ((String) in.readValue((String.class.getClassLoader())));
        this.numOfSeats = ((int) in.readValue((int.class.getClassLoader())));
        this.costPerSeat = ((int) in.readValue((int.class.getClassLoader())));
        this.expectedCost = ((String) in.readValue((String.class.getClassLoader())));
        this.expectedDistance = ((String) in.readValue((String.class.getClassLoader())));
        this.applicationUerId = ((String) in.readValue((String.class.getClassLoader())));
        this.journeyDriver = ((JourneyDriver) in.readValue((JourneyDriver.class.getClassLoader())));
    }

    public ProductDriver() {
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue($id);
        dest.writeValue(id);
        dest.writeValue(sourceAdress);
        dest.writeValue(launchTime);
        dest.writeValue(destinationAddress);
        dest.writeValue(endTime);
        dest.writeValue(sourceLocation);
        dest.writeValue(destinationLocation);
        dest.writeValue(launchdate);
        dest.writeValue(numOfSeats);
        dest.writeValue(costPerSeat);
        dest.writeValue(expectedCost);
        dest.writeValue(expectedDistance);
        dest.writeValue(applicationUerId);
        dest.writeValue(journeyDriver);
    }

    public int describeContents() {
        return 0;
    }

}
