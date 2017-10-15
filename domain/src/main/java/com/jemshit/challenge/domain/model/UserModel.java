package com.jemshit.challenge.domain.model;

public class UserModel {

    private String profileId;
    private String profileFullname;
    private String pictureUrl;
    private String address;

    public UserModel() {
    }

    public UserModel(String profileId, String profileFullname, String pictureUrl, String address) {
        this.profileId = profileId;
        this.profileFullname = profileFullname;
        this.pictureUrl = pictureUrl;
        this.address = address;
    }

    public String getProfileId() {
        return profileId;
    }

    public void setProfileId(String profileId) {
        this.profileId = profileId;
    }

    public String getProfileFullname() {
        return profileFullname;
    }

    public void setProfileFullname(String profileFullname) {
        this.profileFullname = profileFullname;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
