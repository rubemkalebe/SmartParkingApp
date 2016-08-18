package br.ufrn.imd.smartparkingapp.model;

import java.io.Serializable;

/**
 * Created by andre on 21/03/2016.
 * Updated by Rubem on 08/18/2016.
 */
public class Spot implements Serializable {

    private Integer spotID = null;

    private Double latitude = null;

    private Double longitude = null;

    private String ipAddress = null;

    private Boolean isReserved = null;

    private Boolean busy = null;

    private String UID = null;

    private Boolean _new = null;


    public Spot() {
        super();
    }

    public Spot(Integer spotID, Double latitude, Double longitude, String ipAddress,
                Boolean isReserved, Boolean busy, String UID, Boolean _new) {
        this.spotID = spotID;
        this.latitude = latitude;
        this.longitude = longitude;
        this.ipAddress = ipAddress;
        this.isReserved = isReserved;
        this.busy = busy;
        this.UID = UID;
        this._new = _new;
    }

    public Integer getSpotID() {
        return spotID;
    }

    public void setSpotID(Integer spotID) {
        this.spotID = spotID;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public Boolean getReserved() {
        return isReserved;
    }

    public void setReserved(Boolean reserved) {
        isReserved = reserved;
    }

    public Boolean getBusy() {
        return busy;
    }

    public void setBusy(Boolean busy) {
        this.busy = busy;
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public Boolean get_new() {
        return _new;
    }

    public void set_new(Boolean _new) {
        this._new = _new;
    }

}
