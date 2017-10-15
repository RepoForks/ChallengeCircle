package com.jemshit.challenge.domain.model;

public class ProfileModel {

    private String profileId;
    private String name;
    private String surname;
    private String fullname;
    private String pictureUrl;
    private String cellphoneNumber;
    private String address;
    private String birthDate;
    private int age;

    public ProfileModel() {
    }

    public ProfileModel(String profileId, String name, String surname, String fullname,
                        String pictureUrl, String cellphoneNumber, String address, String birthDate, int age) {
        this.profileId = profileId;
        this.name = name;
        this.surname = surname;
        this.fullname = fullname;
        this.pictureUrl = pictureUrl;
        this.cellphoneNumber = cellphoneNumber;
        this.address = address;
        this.birthDate = birthDate;
        this.age = age;
    }

    public String getProfileId() {
        return profileId;
    }

    public void setProfileId(String profileId) {
        this.profileId = profileId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getCellphoneNumber() {
        return cellphoneNumber;
    }

    public void setCellphoneNumber(String cellphoneNumber) {
        this.cellphoneNumber = cellphoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
