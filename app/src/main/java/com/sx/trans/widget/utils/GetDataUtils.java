package com.sx.trans.widget.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2017/6/27.
 */

public class GetDataUtils {

    public static String getTime() {//  获取年月
        Date date = new Date();
        DateFormat sdf = new SimpleDateFormat("yyyy-MM");

        String time = sdf.format(date);

        return time;
    }

    public String time() {
        Date date = new Date();
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String time = sdf.format(date);

        return time;
    }

    public static String getNian() {
        Date date = new Date();
        DateFormat sdf = new SimpleDateFormat("yyyy");

        String time = sdf.format(date);

        return time;
    }

    public static String secToTime(int time) {  //将秒转换成：： 形式
        String timeStr = null;
        int hour = 0;
        int minute = 0;
        int second = 0;
        if (time <= 0)
            return "00:00:00";
        else {
            minute = time / 60;
            if (minute < 60) {
                second = time % 60;
                timeStr = unitFormat(hour) + ":" + unitFormat(minute) + ":" + unitFormat(second) + ":";
            } else {
                hour = minute / 60;
                if (hour > 99)
                    return "99:59:59";
                minute = minute % 60;
                second = time - hour * 3600 - minute * 60;
                timeStr = unitFormat(hour) + ":" + unitFormat(minute) + ":" + unitFormat(second);
            }
        }
        return timeStr;
    }

    private int setTiemZhuanMiao(String str) {//  将时分秒转换成秒

        int hourValue = Integer.parseInt(str.split("时")[0]);
        int minValue = Integer.parseInt(str.split("时")[1].split("分")[0]);
        int secValue = Integer.parseInt(str.split("时")[1].split("分")[1].split("秒")[0]);
        return hourValue * 3600 + minValue * 60 + secValue;
    }

    public static String setTiemType(String str) {//  将时分秒转换成秒

        return str.replace("时", ":").replace("分", ":").replace("秒", "");
    }

    public static String unitFormat(int i) {
        String retStr = null;
        if (i >= 0 && i < 10)
            retStr = "0" + Integer.toString(i);
        else
            retStr = "" + i;
        return retStr;
    }
}
