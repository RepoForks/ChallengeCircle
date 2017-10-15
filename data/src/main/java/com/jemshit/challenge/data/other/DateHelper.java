package com.jemshit.challenge.data.other;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class DateHelper {

    private SimpleDateFormat dateFormat;

    @Inject
    public DateHelper() {
        dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
    }

    public int getAgeFromDate(String dateString, long currentTimeEpoch) {
        Date date;
        try {
            date = dateFormat.parse(dateString);
            long birthEpoch = date.getTime();
            long ageEpoch = currentTimeEpoch - birthEpoch;
            return (int) (ageEpoch / 1000 / 60 / 60 / 24 / 365);
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
