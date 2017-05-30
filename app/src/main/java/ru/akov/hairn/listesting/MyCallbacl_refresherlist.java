package ru.akov.hairn.listesting;


import ru.akov.hairn.listesting.DATA.GPScoords_price;

/**
 * Created by User on 21.04.2016.
 */
public interface MyCallbacl_refresherlist {

       void refresh(GPScoords_price obj);

       void addtolist(GPScoords_price obj);
       void removefromlist(String obj);


}