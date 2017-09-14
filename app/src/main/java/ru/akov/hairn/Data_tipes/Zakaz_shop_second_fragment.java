package ru.akov.hairn.Data_tipes;

/**
 * Created by User on 17.03.2017.
 */

public class Zakaz_shop_second_fragment {

    public String clienid;
    public String magazinid;
    public String mail;
    public String name;
    public String phone;
    public String uslugi;
    public String date;
    public String clock;
    public String status;

    public Zakaz_shop_second_fragment() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public Zakaz_shop_second_fragment(String clienid, String magazinid, String mail, String name, String phone, String uslugi, String date, String clock, String status) {
        //  this.product = product;
        this.clienid = clienid;
        this.magazinid = magazinid;
        this.mail = mail;
        this.name = name;
        this.phone = phone;
        this.uslugi = uslugi;
        this.date = date;
        this.clock = clock;
        this.status = status;
    }
    public String getclienid() {
        return clienid;
    }
    public String getmagazinid() {
        return magazinid;
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
    public String getdate() {
        return date;
    }
    public String getclock() {
        return clock;
    }
    public String getstatus() {
        return status;
    }


}
