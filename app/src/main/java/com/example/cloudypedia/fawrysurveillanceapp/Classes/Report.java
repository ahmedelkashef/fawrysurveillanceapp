package com.example.cloudypedia.fawrysurveillanceapp.Classes;

/**
 * Created by dev3 on 12/15/2016.
 */

public class Report {

    private  String name ;
    private String location;
    private String  merchantID;
    private String GISLocation;
    private String range ;
    private String salesName;
    private String salesEmail;
    private String salesID;

    public String getSalesName() {
        return salesName;
    }

    public void setSalesName(String salesName) {
        this.salesName = salesName;
    }

    public String getMerchantID() {
        return merchantID;
    }

    public void setMerchantID(String merchantID) {
        this.merchantID = merchantID;
    }

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }

    public String getGISLocation() {
        return GISLocation;
    }

    public void setGISLocation(String GISLocation) {
        this.GISLocation = GISLocation;
    }

    public String getSalesEmail() {
        return salesEmail;
    }

    public void setSalesEmail(String salesEmail) {
        this.salesEmail = salesEmail;
    }

    public String getSalesID() {
        return salesID;
    }

    public void setSalesID(String salesID) {
        this.salesID = salesID;
    }

    public String getTerminalID() {
        return terminalID;
    }

    public void setTerminalID(String terminalID) {
        this.terminalID = terminalID;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    private String terminalID;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
