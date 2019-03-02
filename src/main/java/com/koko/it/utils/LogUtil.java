package com.koko.it.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogUtil {
    private static Logger logger;
    public LogUtil(Class c) {
         logger = LoggerFactory.getLogger(c);
    }

    public void info(String s){
        logger.info(s);
    }

    public void info(String s, Object o){
        logger.info(s, o);
    }

    public void debug(String s){
        logger.debug(s);
    }

    public void error(String s, Throwable t){
        logger.error(s, t);
    }

    public void saveErrorMsg(String s, Throwable t){
        info("------------------------"+s+"出错啦---------------------------");
        error(s, t);
    }

}
