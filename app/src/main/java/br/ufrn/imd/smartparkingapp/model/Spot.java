package br.ufrn.imd.smartparkingapp.model;

import java.io.Serializable;

/**
 * @author André, Rubem
 * @version 18/08/2016
 */
public class Spot implements Serializable {

    private Integer id = null;

    private Double latitude = null;

    private Double longitude = null;

    private String ipAddress = null;

    private Boolean reserved = null;

    private Boolean busy = null;

    private String uid = null;


    public Spot() {
        super();
    }

    public Spot(Integer id, Double latitude, Double longitude, String ipAddress,
                Boolean reserved, Boolean busy, String uid, Boolean _new) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.ipAddress = ipAddress;
        this.reserved = reserved;
        this.busy = busy;
        this.uid = uid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
        return reserved;
    }

    public void setReserved(Boolean reserved) {
        this.reserved = reserved;
    }

    public Boolean getBusy() {
        return busy;
    }

    public void setBusy(Boolean busy) {
        this.busy = busy;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    @Override
    public String toString() {
        /*return "Spot{" +
                "id=" + id +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", ipAddress='" + ipAddress + '\'' +
                ", reserved=" + reserved +
                ", busy=" + busy +
                ", uid='" + uid + '\'' +
                '}';*/
        String str = "Vaga " + id;
        /*if(reserved && !busy) {
            str += " Reservada Livre";
        } else if(reserved && busy) {
            str += " Reservada Ocupada";
        } else if(busy) {
            str += " Ocupada";
        } else {
            str += " Livre";
        }*/
        return str;
    }

}
