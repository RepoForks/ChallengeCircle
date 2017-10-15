package com.jemshit.challenge.data.entity.web_responses;

import java.util.List;

public class GetProfileListResponseEntity {

    private String token;
    private List<UserEntity> data;

    public GetProfileListResponseEntity() {
    }

    public GetProfileListResponseEntity(List<UserEntity> data) {
        this.data = data;
    }

    public List<UserEntity> getData() {
        return data;
    }

    public void setData(List<UserEntity> data) {
        this.data = data;
    }
}
