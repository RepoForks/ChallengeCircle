package com.jemshit.challenge.data.entity.mapper;

import com.jemshit.challenge.data.entity.web_responses.UserEntity;
import com.jemshit.challenge.domain.model.UserModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class UserEntityMapper {

    @Inject
    public UserEntityMapper() {
        //
    }

    public UserModel transform(UserEntity entity) {
        UserModel model = null;
        if (entity != null) {
            model = new UserModel();
            model.setProfileId(entity.getId());
            model.setProfileFullname(entity.getName() + " " + entity.getSurname());
            model.setAddress(entity.getAddress());
            model.setPictureUrl(entity.getPictureUrl());
        }
        return model;
    }

    public List<UserModel> transform(Collection<UserEntity> entityCollection) {
        final List<UserModel> modelList = new ArrayList<>(20);
        for (UserEntity entity : entityCollection) {
            final UserModel model = transform(entity);
            if (model != null) {
                modelList.add(model);
            }
        }
        return modelList;
    }
}
