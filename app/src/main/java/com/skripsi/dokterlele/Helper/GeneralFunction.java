package com.skripsi.dokterlele.Helper;

import android.content.Context;
import android.support.multidex.MultiDex;

import com.orm.SugarApp;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class GeneralFunction extends SugarApp {

    public static String getHari() {
        String dateformat = "yyyy-MM-dd hh:mm:ss";
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat(dateformat);
        String formattedDate = df.format(c.getTime());
        return formattedDate;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
