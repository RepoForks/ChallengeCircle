package com.jemshit.challenge.data.test_helper;

import com.jemshit.challenge.data.entity.web_responses.ProfileEntity;
import com.jemshit.challenge.data.entity.web_responses.UserEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DataGenerator {

    public static UserEntity createUserEntity(boolean valid) {
        int randomNumberTail = IntegerUtility.createRandomInt(0, 1000);
        return valid ?
                new UserEntity("id-" + randomNumberTail, "name-" + randomNumberTail, "surname-" + randomNumberTail,
                        "pictureUrl-" + randomNumberTail, "address-" + randomNumberTail)
                : null;
    }

    public static List<UserEntity> createUserEntityList(boolean valid) {
        if (valid) {
            List<UserEntity> entityList = new ArrayList<>(2);
            entityList.add(createUserEntity(valid));
            entityList.add(createUserEntity(valid));
            return entityList;
        } else
            return Collections.emptyList();
    }

    public static ProfileEntity createProfileEntity(boolean valid) {
        int randomNumberTail = IntegerUtility.createRandomInt(0, 1000);
        return valid ?
                new ProfileEntity("id-" + randomNumberTail, "name-" + randomNumberTail, "surname-" + randomNumberTail,
                        "pictureUrl-" + randomNumberTail, "phoneNumber-" + randomNumberTail, "address-" + randomNumberTail,
                        "birthDate-" + randomNumberTail)
                : null;
    }

    public static List<ProfileEntity> createProfileEntityList(boolean valid) {
        if (valid) {
            List<ProfileEntity> entityList = new ArrayList<>(2);
            entityList.add(createProfileEntity(valid));
            entityList.add(createProfileEntity(valid));
            return entityList;
        } else
            return Collections.emptyList();
    }


}
