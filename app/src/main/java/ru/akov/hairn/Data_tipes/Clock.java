package ru.akov.hairn.Data_tipes;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by Alexandr on 09.07.2016.
 */
@IgnoreExtraProperties
public class Clock {


    public String clock;
    public String avaluble;

    public Clock() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public Clock(String clock, String avaluble) {
      //  this.product = product;
        this.clock = clock;

        this.avaluble = avaluble;

    }
    public String getclock() {
        return clock;
    }

    public String getavaluble() {
        return avaluble;
    }





}