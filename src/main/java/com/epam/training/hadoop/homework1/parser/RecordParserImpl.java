/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.hadoop.homework1.parser;

import com.epam.training.hadoop.homework1.entity.RecordEntity;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 *
 * @author Oleksandr_Taranovsky
 */
public class RecordParserImpl implements RecordParser {

    private final static String EMPTY_RECORD = "";
    private final static Pattern VALID_RECORD = Pattern.compile("(ip\\d+)\\s(.+)\\s(\\[(.+)\\s(.+)\\])\\s(\\\"(\\w+)\\s(\\/.+)\\s(.+)\\\")\\s(\\d+)\\s(\\d+)\\s(\\\"(.+)\\\")\\s(\\\"(.+)\\\")");
    private static Matcher matcher;
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormat.forPattern("dd/MMM/yyyy:HH:mm:ss").withLocale(Locale.ENGLISH);

    @Override
    public boolean isValidRecord(String record) {
        return isValid(record);
    }

    private boolean isValid(String record) {
        if (record == null || EMPTY_RECORD.equals(record)) {
            return false;
        }
        matcher = VALID_RECORD.matcher(record);
        if (matcher.find()) {
            try {
                DateTime.parse(matcher.group(4), DATE_TIME_FORMATTER);
            } catch (RuntimeException e) {
                return false;
            }
        } else {
            return false;
        }

        return true;
    }

    @Override
    public RecordEntity parseRecord(String record) {
        if (!isValid(record)) {
            return null;
        }
        RecordEntity recordEntity = new RecordEntity();

        recordEntity.setIp(matcher.group(1));
        recordEntity.setSomeUnknownFields1(matcher.group(2));
        recordEntity.setDateTime(DateTime.parse(matcher.group(4), DATE_TIME_FORMATTER).withZoneRetainFields(DateTimeZone.forID(matcher.group(5))));
        recordEntity.setMethodName(matcher.group(7));
        recordEntity.setLocalLink(matcher.group(8));
        recordEntity.setProtocolName(matcher.group(9));
        recordEntity.setStatus(Integer.valueOf(matcher.group(10)));
        recordEntity.setBytesTransfered(Long.valueOf(matcher.group(11)));
        recordEntity.setHostLink(matcher.group(13));
        recordEntity.setMiscInfo(matcher.group(15));

        return recordEntity;
    }
}
