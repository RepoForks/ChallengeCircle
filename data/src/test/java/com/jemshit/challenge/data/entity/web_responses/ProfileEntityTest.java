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

package com.jemshit.challenge.data.entity.web_responses;

import org.junit.Assert;
import org.junit.Test;

public class ProfileEntityTest {

    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String SURNAME = "surname";
    private static final String PICTURE_URL = "url";
    private static final String BIRTHDAY = "birthday";
    private static final String ADDRESS = "address";
    private static final String NUMBER = "number";

    @Test
    public void constructor_shouldCreateAllParameters() {
        // Assign
        ProfileEntity entity = new ProfileEntity(ID, NAME, SURNAME, PICTURE_URL, NUMBER, ADDRESS, BIRTHDAY);

        // Assert
        Assert.assertEquals(ID, entity.getId());
        Assert.assertEquals(NAME, entity.getName());
        Assert.assertEquals(SURNAME, entity.getSurname());
        Assert.assertEquals(PICTURE_URL, entity.getPictureUrl());
        Assert.assertEquals(BIRTHDAY, entity.getBirthDate());
        Assert.assertEquals(ADDRESS, entity.getAddress());
        Assert.assertEquals(NUMBER, entity.getCellphoneNumber());
    }

    @Test
    public void setter_shouldCreateAllParameters() {
        // Assign
        ProfileEntity entity = new ProfileEntity();
        entity.setId(ID);
        entity.setName(NAME);
        entity.setSurname(SURNAME);
        entity.setPictureUrl(PICTURE_URL);
        entity.setBirthDate(BIRTHDAY);
        entity.setAddress(ADDRESS);
        entity.setCellphoneNumber(NUMBER);

        // Assert
        Assert.assertEquals(ID, entity.getId());
        Assert.assertEquals(NAME, entity.getName());
        Assert.assertEquals(SURNAME, entity.getSurname());
        Assert.assertEquals(PICTURE_URL, entity.getPictureUrl());
        Assert.assertEquals(BIRTHDAY, entity.getBirthDate());
        Assert.assertEquals(ADDRESS, entity.getAddress());
        Assert.assertEquals(NUMBER, entity.getCellphoneNumber());
    }
}