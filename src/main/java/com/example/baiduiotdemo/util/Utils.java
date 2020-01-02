package com.example.baiduiotdemo.util;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Random;

public class Utils {

    private static  final int LEN = 20;

    public static String generateMessageId() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < LEN; i++) {
            sb.append((char)(i%26 + 'a'));
        }
        sb.append('-');
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String dateNowStr = sdf.format(d);
        sb.append(dateNowStr);
        return sb.toString();
    }
}

