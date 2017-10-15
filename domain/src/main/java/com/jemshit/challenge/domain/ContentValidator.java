package com.jemshit.challenge.domain;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ContentValidator {

    @Inject
    public ContentValidator() {
    }

    public boolean isEmptyString(CharSequence str) {
        return str == null || str.length() == 0;
    }
}
