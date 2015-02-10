package com.epam.training.hadoop.homework1.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Oleksandr_Taranovsky on 2/10/2015.
 */
public class RecordValidatorImpl implements RecordValidator {

    private final static String EMPTY_RECORD = "";
    private final static Pattern VALID_RECORD = Pattern.compile("");
    private static Matcher matcher;

    @Override
    public boolean isValidRecord(String record) {
        if (record == null || EMPTY_RECORD.equals(record)) {
            return false;
        }
        matcher = VALID_RECORD.matcher(record);
        return matcher.find();
        
    }
}
