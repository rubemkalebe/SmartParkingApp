package br.ufrn.imd.smartparkingapp.model;

import java.io.Serializable;

/**
 * Created by andre on 21/03/2016.
 */
public class Sensor implements Serializable {

    private Integer id = null;
    private String identify = null;
    private Boolean busy = false;
    private Double latitude = null;
    private Double longitude = null;

    public Sensor() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIdentify() {
        return identify;
    }

    public void setIdentify(String identify) {
        this.identify = identify;
    }

    public Boolean getBusy() {
        return busy;
    }

    public void setBusy(Boolean busy) {
        this.busy = busy;
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
}
