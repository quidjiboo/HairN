package ru.akov.hairn.Data_tipes;

/**
 * Created by User on 14.03.2017.
 */

public class Confirm {

    public String user;


    public Confirm() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public Confirm(String value) {
        //  this.product = product;
        this.user = value;



    }
    public String getuser() {
        return user;
    }


}
