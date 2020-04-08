package com.ujjwalsingh.mvvm.Data;

import android.icu.util.Calendar;

import com.ujjwalsingh.mvvm.Note.NotesEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class SampleDataProvidor {

    public static final String sampe1= "Its a good day";
    public static final String sampe2= "Conquorer of the world";
    public static final String sampe3= "The world is earth, the universe.";


    public static Date getDate(int amount){
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.add(Calendar.MILLISECOND,amount);
        return gregorianCalendar.getTime();
    }

    public static List<NotesEntity> addListSample(){
        List<NotesEntity> list = new ArrayList<>();
        list.add(new NotesEntity(getDate(0),sampe1));
        list.add(new NotesEntity(getDate(-1),sampe2));
        list.add(new NotesEntity(getDate(-2),sampe3));
        return list;
    }
}
