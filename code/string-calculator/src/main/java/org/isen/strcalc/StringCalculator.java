package org.isen.strcalc;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {

    public int add(String string) {

        Matcher m = Pattern.compile("^//(.)\n(.*)").matcher(string);
        String delimiters = getDelimiter(m);
        String numberString = getNumberString(string, m.reset());
        return sumNumbers(getNumbers(numberString, delimiters));

    }

    private String getNumberString(String string, Matcher m) {
        if (m.find()) {
            return m.group(2);
        } else {
            return string;
        }
    }

    private String getDelimiter(Matcher m) {
        if (m.find()) {
            return Pattern.quote(m.group(1));
        } else {
            return ",|\n";
        }
    }

    private int sumNumbers(String[] split) {
        int result = 0;
        for (String str : split) {
            result += Integer.parseInt(str);
        }
        return result;
    }

    private String[] getNumbers(String string, String delimiter) {
        if (string.isEmpty()) {
            return new String[0];
        } else {
            return string.split(delimiter);
        }
    }

}
