package com.koko.it.utils;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 定时任务，在Application添加注解@EnableScheduling即可执行定时任务
 */
@Component
public class SchedulerTaskUtil {
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Scheduled(fixedRate = 3000)
    public void reportCurrentTime(){
        System.out.println("现在时间：" + sdf.format(new Date()));
    }

}
