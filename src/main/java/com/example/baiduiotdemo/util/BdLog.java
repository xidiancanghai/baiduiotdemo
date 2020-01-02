package com.example.baiduiotdemo.util;


import com.example.baiduiotdemo.BaiduiotdemoApplication;
import org.apache.log4j.Logger;

public class BdLog {

    private final static Logger log = Logger.getLogger(BaiduiotdemoApplication.class);

    public static void info(String str) {
        log.info(str);
    }

    public static void trace(String str) {
        log.trace(str);
    }

    public static void error(String str) {
        log.error(str);
    }

    public static void debug(String str) {
        log.debug(str);
    }
}
