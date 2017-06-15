package ru.akov.hairn.Data_tipes;

/**
 * Created by Alexandr on 14.06.2017.
 */

public class Typs_of_shop {
    public String value;


    public Typs_of_shop() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public Typs_of_shop(String value) {
        //  this.product = product;
        this.value = value;



    }
    public String getvalue() {
        return value;
    }

}
