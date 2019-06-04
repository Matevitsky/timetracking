package com.matevitsky.util;

public class TimeParser {

    private TimeParser() {
    }

    /**
     * Parse incoming time in minute to String format hh:mm
     *
     * @param time - time in minute
     * @return return minute time in String format hh:mm
     */
    public static final String parseIntegerToTimeString(Integer time) {

        Integer minutes = time;
        Integer hours = 0;
        String result = "";

        while (minutes >= 60) {
            minutes -= 60;
            hours++;
        }
        if ((hours + "").length() == 1) {
            result += "0" + hours + ":";
        } else {
            result += hours + ":";
        }
        if ((minutes + "").length() == 1) {
            result += "0" + minutes;
        } else {
            result += minutes;
        }

        return result;
    }

    /**
     * Parse incoming time in String to minute
     *
     * @param time - time in String format hh:mm
     * @return return time in minute
     */

    public static final Integer parseStringTimeToInteger(String time) {
        if (time != null) {

            String[] split = time.split(":");
            return Integer.parseInt(split[0]) * 60 + Integer.parseInt(split[1]);

        }
        return 0;
    }
}
