package com.vegeta.my.dealer.model.AddTrips;

public class ProductDriverRequest {
    private String SourceAdress;
    private String launchTime ;
    private String launchdate ;
    private String DestinationAddress ;
    private String NumOfSeats ;
    private String CostPerSeat  ;
    private String DriverName   ;


    public String getSourceAdress() {
        return SourceAdress;
    }

    public void setSourceAdress(String sourceAdress) {
        SourceAdress = sourceAdress;
    }

    public String getLaunchTime() {
        return launchTime;
    }

    public void setLaunchTime(String launchTime) {
        this.launchTime = launchTime;
    }

    public String getLaunchdate() {
        return launchdate;
    }

    public void setLaunchdate(String launchdate) {
        this.launchdate = launchdate;
    }

    public String getDestinationAddress() {
        return DestinationAddress;
    }

    public void setDestinationAddress(String destinationAddress) {
        DestinationAddress = destinationAddress;
    }

    public String getNumOfSeats() {
        return NumOfSeats;
    }

    public void setNumOfSeats(String numOfSeats) {
        NumOfSeats = numOfSeats;
    }

    public String getCostPerSeat() {
        return CostPerSeat;
    }

    public void setCostPerSeat(String costPerSeat) {
        CostPerSeat = costPerSeat;
    }

    public String getDriverName() {
        return DriverName;
    }

    public void setDriverName(String driverName) {
        DriverName = driverName;
    }
}
