package com.koko.it.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogUtil {
    private static Logger logger;
    public LogUtil(Class c) {
         logger = LoggerFactory.getLogger(c);
    }

    public static void info(Object o){
        logger.info(LogUtil.class.getSimpleName(), o);
    }

    public static void info(String s, Object o){
        logger.info(s, o);
    }

    public static void error(String s, Throwable t){
        logger.error(s, t);
    }

    public static void saveErrorMsg(String s, Throwable t){
        info("------------------------"+s+"出错啦---------------------------");
        error(s, t);
    }

}
