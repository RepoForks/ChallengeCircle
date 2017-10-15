package com.jemshit.challenge.presentation.validator;

import android.text.TextUtils;
import android.widget.TextView;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ViewValidator {

    @Inject
    public ViewValidator() {
    }

    public boolean isNonEmpty(TextView view, String errorText) {
        boolean isValid = view.getText().toString() != null && !TextUtils.isEmpty(view.getText());
        if (!isValid) {
            view.setError(errorText);
            view.requestFocus();
        }
        return isValid;
    }
}
