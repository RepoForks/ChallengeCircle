package com.jemshit.challenge.data.test_helper;

import java.util.concurrent.ThreadLocalRandom;

public class IntegerUtility {

    public static int createRandomInt(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }
}
