/*
 * Copyright (c) 2017 Jemshit Iskanderov.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.jemshit.challenge.domain.model;

import junit.framework.Assert;

import org.junit.Test;

public class ProfileModelTest {

    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String SURNAME = "surname";
    private static final String FULLNAME = "fullname";
    private static final String PICTURE_URL = "url";
    private static final String NUMBER = "number";
    private static final String ADDRESS = "address";
    private static final String BIRTHDATE = "date";
    private static final int AGE = 1;

    @Test
    public void constructor_shouldCreateAllParameters() {
        // Assign
        ProfileModel profileModel = new ProfileModel(ID, NAME, SURNAME, FULLNAME, PICTURE_URL,
                NUMBER, ADDRESS, BIRTHDATE, AGE);

        // Assert
        Assert.assertEquals(ID, profileModel.getProfileId());
        Assert.assertEquals(NAME, profileModel.getName());
        Assert.assertEquals(SURNAME, profileModel.getSurname());
        Assert.assertEquals(FULLNAME, profileModel.getFullname());
        Assert.assertEquals(PICTURE_URL, profileModel.getPictureUrl());
        Assert.assertEquals(NUMBER, profileModel.getCellphoneNumber());
        Assert.assertEquals(ADDRESS, profileModel.getAddress());
        Assert.assertEquals(BIRTHDATE, profileModel.getBirthDate());
        Assert.assertEquals(AGE, profileModel.getAge());

    }

    @Test
    public void setter_shouldCreateAllParameters() {
        // Assign
        ProfileModel profileModel = new ProfileModel();
        profileModel.setProfileId(ID);
        profileModel.setName(NAME);
        profileModel.setSurname(SURNAME);
        profileModel.setFullname(FULLNAME);
        profileModel.setPictureUrl(PICTURE_URL);
        profileModel.setCellphoneNumber(NUMBER);
        profileModel.setAddress(ADDRESS);
        profileModel.setBirthDate(BIRTHDATE);
        profileModel.setAge(AGE);

        // Assert
        Assert.assertEquals(ID, profileModel.getProfileId());
        Assert.assertEquals(NAME, profileModel.getName());
        Assert.assertEquals(SURNAME, profileModel.getSurname());
        Assert.assertEquals(FULLNAME, profileModel.getFullname());
        Assert.assertEquals(PICTURE_URL, profileModel.getPictureUrl());
        Assert.assertEquals(NUMBER, profileModel.getCellphoneNumber());
        Assert.assertEquals(ADDRESS, profileModel.getAddress());
        Assert.assertEquals(BIRTHDATE, profileModel.getBirthDate());
        Assert.assertEquals(AGE, profileModel.getAge());

    }
}