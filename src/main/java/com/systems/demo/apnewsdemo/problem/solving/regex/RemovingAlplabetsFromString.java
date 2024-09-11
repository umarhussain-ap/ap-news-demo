package com.systems.demo.apnewsdemo.problem.solving.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lombok.extern.slf4j.Slf4j;

public class RemovingAlplabetsFromString {

        public static String removeAlphabets(String string) {
            // Regex pattern to match alphabets
            Pattern pattern = Pattern.compile("[a-zA-Z]");
            Matcher matcher = pattern.matcher(string);
            // return all alphabets with an empty string
            return matcher.replaceAll("");
        }
}

@Slf4j
class Driver {

    public static final String VALUE_BEFORE_REGEX = "value before regex {}";
    public static final String VALUE_AFTER_REGEX = "value after regex {}";

    public static void main(String[] args) {
        String value = "100 minutes";
        log.info(VALUE_BEFORE_REGEX, value);
        log.info(VALUE_AFTER_REGEX, RemovingAlplabetsFromString.removeAlphabets(value));

        String value2 = "100minutes";
        log.info(VALUE_BEFORE_REGEX, value);
        log.info(VALUE_AFTER_REGEX, RemovingAlplabetsFromString.removeAlphabets(value2));

        String value3 = "100 minutes.";
        log.info(VALUE_BEFORE_REGEX, value);
        log.info(VALUE_AFTER_REGEX, RemovingAlplabetsFromString.removeAlphabets(value3));

        String value4 = "100";
        log.info(VALUE_BEFORE_REGEX, value4);
        log.info(VALUE_AFTER_REGEX, RemovingAlplabetsFromString.removeAlphabets(value4));

    }
}
