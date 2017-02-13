package com.chumbok.news.util;


import com.chumbok.news.Logger;

import java.io.UnsupportedEncodingException;

public class StringUtil {

    private static final Logger logger = Logger.getLogger();

    private StringUtil() {
        throw new IllegalStateException();
    }

    public static String getUnicodeString(String str) {
        String ret = null;
        try {
            ret = new String(str.getBytes("ISO-8859-1"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
        }
        return ret;
    }
}
