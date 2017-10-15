package com.jemshit.challenge.data.entity.web_responses;

public class ProfileEntity {

    private String id;
    private String name;
    private String surname;
    private String pictureUrl;
    private String cellphoneNumber;
    private String address;
    private String birthDate;

    public ProfileEntity() {
    }

    public ProfileEntity(String id, String name, String surname, String pictureUrl, String cellphoneNumber, String address, String birthDate) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.pictureUrl = pictureUrl;
        this.cellphoneNumber = cellphoneNumber;
        this.address = address;
        this.birthDate = birthDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
}
