package com.limefriends.molde.comm.utils;

public class StringUtil {

    public static String moveLine(String msg) {
        String result = "";
        String[] lines = msg.split("\\\\n");
        for (String line : lines) {
            result += line;
            result += "\n";
        }
        return result;
    }

}
