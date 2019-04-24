package com.example.myapplication;

import android.provider.ContactsContract;

import org.litepal.LitePal;
import org.litepal.crud.LitePalSupport;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DBhelper {

    public DBhelper(){
        LitePal.getDatabase();
    }

    //增加标记
    public Map addMap(String code, int site, ArrayList values){

        Map add = new Map();
        add.setCode(code);
        add.setSite(site);
        add.setValues(values);

        return add;
    }

    //获取标记信息
    public List<Map> getMap(){
        List<Map> Map = LitePal.order("code").order("site").find(Map.class);
        return Map;
    }

}

