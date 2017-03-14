package ru.akov.hairn.Data_tipes;

/**
 * Created by User on 14.03.2017.
 */

public class Usluga {

    public String value;


    public Usluga() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public Usluga(String value) {
        //  this.product = product;
        this.value = value;



    }
    public String getvalue() {
        return value;
    }


}
