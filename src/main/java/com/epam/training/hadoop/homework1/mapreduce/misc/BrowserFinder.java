/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.hadoop.homework1.mapreduce.misc;

import com.epam.training.hadoop.homework1.mapreduce.counter.Browsers;
import java.util.EnumSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Oleksandr_Taranovsky
 */
public class BrowserFinder {

    private static Pattern pattern = null;

    private BrowserFinder() {
    }

    static {

        StringBuilder stringBuilder = new StringBuilder();
        String browserName = null;

        for (Browsers b : Browsers.values()) {
            stringBuilder.append(b);
            stringBuilder.append("|");
        }
        stringBuilder.delete(stringBuilder.length() - 1, stringBuilder.length());
        pattern = Pattern.compile(stringBuilder.toString());
    }

    public static EnumSet<Browsers> getBrowsers(String info) {
        String upperInfo = info.toUpperCase();

        Matcher matcher = pattern.matcher(upperInfo);

        EnumSet<Browsers> set = EnumSet.noneOf(Browsers.class);

        String group = null;

        while (matcher.find()) {
            group = matcher.group();
            if (!group.isEmpty()) {
                set.add(Browsers.valueOf(group));
            }
        }

        return set;
    }
}
