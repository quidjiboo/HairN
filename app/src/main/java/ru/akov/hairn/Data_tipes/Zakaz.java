package ru.akov.hairn.Data_tipes;

/**
 * Created by User on 17.03.2017.
 */

public class Zakaz {
    public String mail;
    public String name;
    public String phone;
    public String uslugi;

    public Zakaz() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public Zakaz(String mail, String name, String phone, String uslugi) {
        //  this.product = product;
        this.mail = mail;
        this.name = name;
        this.phone = phone;
        this.uslugi = uslugi;
    }
    public String getmail() {
        return mail;
    }
    public String getname() {
        return name;
    }
    public String getphone() {
        return phone;
    }
    public String getuslugi() {
        return uslugi;
    }




}
