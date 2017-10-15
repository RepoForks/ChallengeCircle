package com.jemshit.challenge.data.entity.web_responses;

public class UserEntity {

    private String id;
    private String name;
    private String surname;
    private String pictureUrl;
    private String address;

    public UserEntity() {
    }

    public UserEntity(String id, String name, String surname, String pictureUrl, String address) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.pictureUrl = pictureUrl;
        this.address = address;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
