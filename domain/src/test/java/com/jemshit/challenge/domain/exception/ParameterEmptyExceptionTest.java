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

package com.jemshit.challenge.domain.exception;

import org.junit.Assert;
import org.junit.Test;

public class ParameterEmptyExceptionTest {

    @Test
    public void constructor_shouldCreateWithMessage(){
        // Assign
        ParameterEmptyException parameterEmptyException= new ParameterEmptyException("message");

        // Assert
        Assert.assertEquals("message",parameterEmptyException.getMessage());
    }

    @Test
    public void constructor_shouldCreateWithoutMessage(){
        // Assign
        ParameterEmptyException parameterEmptyException= new ParameterEmptyException();

        // Assert
        Assert.assertEquals(null,parameterEmptyException.getMessage());
    }
}