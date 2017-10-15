package com.jemshit.challenge.data.other;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class DateHelperTest {

    @Rule public ExpectedException expectedException = ExpectedException.none();
    private DateHelper dateHelper;

    @Before
    public void setUp() throws Exception {
        dateHelper = new DateHelper();
    }

    @Test
    public void getAgeFromDate_shouldReturnValidData() {
        // Assign
        String dateBirth = "1993-11-13";
        Long currentTimeEpoch = 1508065943000L; // 2017-15-10
        int age;

        // Act
        age = dateHelper.getAgeFromDate(dateBirth, currentTimeEpoch);

        // Assert
        Assert.assertTrue(age == 23);
    }

    @Test
    public void getAgeFromDate_shouldReturnZeroAge() {
        // Assign
        String dateBirth = "wrong syntax";
        Long currentTimeEpoch = 1508065943000L; // 2017-15-10
        int age;

        // Act
        age = dateHelper.getAgeFromDate(dateBirth, currentTimeEpoch);

        // Assert
        Assert.assertTrue(age == 0);
    }
}