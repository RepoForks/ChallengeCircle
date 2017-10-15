package com.jemshit.challenge.domain;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

public class ContentValidatorTest {

    private ContentValidator contentValidator;

    @Before
    public void setUp() throws Exception {
        contentValidator = new ContentValidator();
    }

    @Test
    public void isEmptyString_shouldReturnTrue() {
        // Assign
        String inputEmpty = "";
        boolean isEmpty;

        // Act
        isEmpty = contentValidator.isEmptyString(inputEmpty);

        // Assert
        Assert.assertTrue(isEmpty);
    }

    @Test
    public void isEmptyString_shouldReturnFalse() {
        // Assign
        String input = "notEmpty";
        boolean isEmpty;

        // Act
        isEmpty = contentValidator.isEmptyString(input);

        // Assert
        Assert.assertFalse(isEmpty);
    }

    @Test
    public void isEmptyString_shouldReturnTrueOnNullInput() {
        // Assign
        boolean isEmpty;

        // Act
        isEmpty = contentValidator.isEmptyString(null);

        // Assert
        Assert.assertTrue(isEmpty);
    }
}