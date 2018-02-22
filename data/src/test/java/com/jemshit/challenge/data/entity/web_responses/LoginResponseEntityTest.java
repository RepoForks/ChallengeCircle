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

public class LoginResponseEntityTest {

    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String TOKEN = "token";

    @Test
    public void constructor_shouldCreateAllParameters() {
        // Assign
        LoginResponseEntity entity = new LoginResponseEntity(USERNAME,PASSWORD,TOKEN);

        // Assert
        Assert.assertEquals(USERNAME, entity.getUsername());
        Assert.assertEquals(PASSWORD, entity.getPassword());
        Assert.assertEquals(TOKEN, entity.getToken());
    }

    @Test
    public void setter_shouldCreateAllParameters() {
        // Assign
        LoginResponseEntity entity = new LoginResponseEntity();
        entity.setUsername(USERNAME);
        entity.setPassword(PASSWORD);
        entity.setToken(TOKEN);

        // Assert
        Assert.assertEquals(USERNAME, entity.getUsername());
        Assert.assertEquals(PASSWORD, entity.getPassword());
        Assert.assertEquals(TOKEN, entity.getToken());
    }
}