package com.jemshit.challenge.data.entity.mapper;

import com.jemshit.challenge.data.entity.web_responses.ProfileEntity;
import com.jemshit.challenge.data.other.DateHelper;
import com.jemshit.challenge.domain.model.ProfileModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ProfileEntityMapper {

    private DateHelper dateHelper;

    @Inject
    public ProfileEntityMapper(DateHelper dateHelper) {
        this.dateHelper = dateHelper;
    }

    public ProfileModel transform(ProfileEntity entity) {
        ProfileModel model = null;
        if (entity != null) {
            model = new ProfileModel();
            model.setProfileId(entity.getId());
            model.setName(entity.getName());
            model.setSurname(entity.getSurname());
            model.setFullname(entity.getName() + " " + entity.getSurname());
            model.setBirthDate(entity.getBirthDate());
            model.setAddress(entity.getAddress());
            model.setCellphoneNumber(entity.getCellphoneNumber());
            model.setPictureUrl(entity.getPictureUrl());
            model.setAge(dateHelper.getAgeFromDate(entity.getBirthDate(), System.currentTimeMillis()));
        }
        return model;
    }

    public List<ProfileModel> transform(Collection<ProfileEntity> entityCollection) {
        final List<ProfileModel> modelList = new ArrayList<>(20);
        for (ProfileEntity entity : entityCollection) {
            final ProfileModel model = transform(entity);
            if (model != null) {
                modelList.add(model);
            }
        }
        return modelList;
    }
}
