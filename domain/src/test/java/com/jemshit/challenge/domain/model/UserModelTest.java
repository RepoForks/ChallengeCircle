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


import org.junit.Assert;
import org.junit.Test;

public class UserModelTest {

    private static final String ID = "id";
    private static final String FULLNAME = "fullname";
    private static final String PICTURE_URL = "url";
    private static final String ADDRESS = "address";

    @Test
    public void constructor_shouldCreateAllParameters() {
        // Assign
        UserModel model = new UserModel(ID,FULLNAME, PICTURE_URL,ADDRESS);

        // Assert
        Assert.assertEquals(ID, model.getProfileId());
        Assert.assertEquals(FULLNAME, model.getProfileFullname());
        Assert.assertEquals(PICTURE_URL, model.getPictureUrl());
        Assert.assertEquals(ADDRESS, model.getAddress());

    }

    @Test
    public void setter_shouldCreateAllParameters() {
        // Assign
        UserModel model = new UserModel();
        model.setProfileId(ID);
        model.setProfileFullname(FULLNAME);
        model.setPictureUrl(PICTURE_URL);
        model.setAddress(ADDRESS);

        // Assert
        Assert.assertEquals(ID, model.getProfileId());
        Assert.assertEquals(FULLNAME, model.getProfileFullname());
        Assert.assertEquals(PICTURE_URL, model.getPictureUrl());
        Assert.assertEquals(ADDRESS, model.getAddress());

    }
}