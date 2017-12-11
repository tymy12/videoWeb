package com.zhuanghou.videos.tool;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by duhui on 2017/12/1.
 */
public class MyTime {


    public  static String getTime(Date date){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return  formatter.format(date);
    }
}
