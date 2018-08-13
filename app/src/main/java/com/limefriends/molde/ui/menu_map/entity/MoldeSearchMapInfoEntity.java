package com.limefriends.molde.ui.menu_map.entity;


import java.io.Serializable;

public class MoldeSearchMapInfoEntity implements Serializable{
    private String mapLat;
    private String mapLng;
    private String name;
    private String mainAddress;
    private String streetAddress;
    private String bizName;
    private String telNo;

    public MoldeSearchMapInfoEntity(String mapLat, String mapLng, String name, String mainAddress, String streetAddress, String bizName, String telNo) {
        this.mapLat = mapLat;
        this.mapLng = mapLng;
        this.name = name;
        this.mainAddress = mainAddress;
        this.streetAddress = streetAddress;
        this.bizName = bizName;
        this.telNo = telNo;
    }

    public String getMapLat() {
        return mapLat;
    }

    public void setMapLat(String mapLat) {
        this.mapLat = mapLat;
    }

    public String getMapLng() {
        return mapLng;
    }

    public void setMapLng(String mapLng) {
        this.mapLng = mapLng;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMainAddress() {
        return mainAddress;
    }

    public void setMainAddress(String mainAddress) {
        this.mainAddress = mainAddress;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getBizName() {
        return bizName;
    }

    public void setBizName(String bizName) {
        this.bizName = bizName;
    }

    public String getTelNo() {
        return telNo;
    }

    public void setTelNo(String telNo) {
        this.telNo = telNo;
    }

    @Override
    public String toString() {
        return name + ", " + mainAddress + ", " + bizName;
    }
}
