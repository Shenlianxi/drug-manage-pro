package com.ytdsuda.management.utils;

import java.util.Random;

public class KeyUtil {
    /*
    * 生成唯一的主键
    * 格式:时间+随机数
    * */
    public static synchronized String genUniqueKey() {
        Random random = new Random();
//        随机生成6位数
        Integer number = random.nextInt(900) + 100;
        return  System.currentTimeMillis() + String.valueOf(number);
    }
}
