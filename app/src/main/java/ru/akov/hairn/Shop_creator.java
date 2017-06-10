package ru.akov.hairn;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import ru.akov.hairn.Data_tipes.Clock;
import ru.akov.hairn.Data_tipes.MyDennedeli;
import ru.akov.hairn.Data_tipes.Shop_data;
import ru.akov.hairn.Data_tipes.Shop_in_locat_url_names_loc;
import ru.akov.hairn.Data_tipes.Shop_locat_list_data;
import ru.akov.hairn.Data_tipes.Shops;
import ru.akov.hairn.Data_tipes.Usluga;


/**
 * Created by User on 13.01.2017.
 */

public   class Shop_creator {
    public  static void add_defaults(final DatabaseReference mDatabase){
        //дефолтовыймагазин
        final String TAG = "ДЕФОЛТНЫе настройки базы";

        mDatabase.addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        mDatabase.child("shops_types").child("barbershop").setValue(Boolean.TRUE);
                        mDatabase.child("shops_types").child("car_workshop").setValue(Boolean.TRUE);
                        mDatabase.child("location").child("Novovoronezh").setValue(Boolean.TRUE);
                        mDatabase.child("location").child("Moscow").setValue(Boolean.TRUE);
                        mDatabase.child("services").child("barbershop").child("Man's haircut").setValue(Boolean.TRUE);
                        mDatabase.child("services").child("barbershop").child("Female haircut").setValue(Boolean.TRUE);
             //           mDatabase.child("services").child("Hair coloring").setValue();
                        }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.w(TAG, "getUser:onCancelled", databaseError.toException());
                    }
                });
    }
    public  static void add_Nady_barbeshop_text_first(final DatabaseReference mDatabase, final FirebaseUser user){
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageReference = storage.getReference().child("test2.txt");

        String data = "ТЕСТОВЫЙ БАРБЕР ШОП ОЛОЛОЛОЛОЛООЛ ЫАДВЫОФА ЖДЛОФ ЛИЬТИФЛОРЫ ЛРТЕТ";


        File file = null;
        FileOutputStream stream = null;
        try {

            file = File.createTempFile("test2", "txt");
            stream = new FileOutputStream(file);
            stream.write(data.getBytes());
        } catch( IOException e ) {

        }
        finally {
            try {
                stream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }



        UploadTask uploadTask = storageReference.putFile(Uri.fromFile(file));
        // Register observers to listen for when the download is done or if it fails
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle unsuccessful uploads
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                // taskSnapshot.getMetadata() contains file metadata such as size, content-type, and download URL.
                @SuppressWarnings("VisibleForTests") Uri downloadUrl =  taskSnapshot.getDownloadUrl();
                Log.v("AKOV", "ДЕЛАЕМ МАГАЗИН НАДЕ!!!");
                add_Nady_barbeshop(mDatabase,user,downloadUrl.toString());
            }
        });

    }
    public  static void add_Nady_barbeshop(final DatabaseReference mDatabase, final FirebaseUser user,String uri_text ){
        //дефолтовыймагазин
        final String uri_text1=uri_text;
        final String TAG = "ТЕСТОВЫЙ БАРБЕР ШОП";
        final String userId = user.getUid();
        mDatabase.child("shops").addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        if(!dataSnapshot.exists()) {
                            String key = mDatabase.child("barbershops").push().getKey();


                            Shop_data msg = new Shop_data("Nady_hair_shop", "barbershop", "https://firebasestorage.googleapis.com/v0/b/test-base-soc-net.appspot.com/o/defaultshop.png?alt=media&token=92cc5bdb-bb0d-4a03-a292-da6ef5eb622d",userId,"Novovoronezh",37.7853889 ,-122.4056973,uri_text1,"3","89191894255");
                            //    String key=mDatabase.child("shops").push().getKey();


                            mDatabase.child("barbershops").child(key).setValue(msg);


                     //       mDatabase.child("locations_names").child("Novovoronezh").child("barbershops_names").child("Nady_hair_shop").setValue(key);
                            Shop_in_locat_url_names_loc msguri = new Shop_in_locat_url_names_loc("Nady_hair_shop","https://firebasestorage.googleapis.com/v0/b/test-base-soc-net.appspot.com/o/defaultshop.png?alt=media&token=92cc5bdb-bb0d-4a03-a292-da6ef5eb622d",37.7853889,-122.4056973,"3");
                            mDatabase.child("locations_names").child("Novovoronezh").child("barbershops_names").child(key).setValue(msguri);
                            Shop_locat_list_data msgpos= new Shop_locat_list_data("37.7853889","-122.4056973");
                            mDatabase.child("locations").child("Novovoronezh").child("barbershops_locations").child(key).setValue(msgpos);
                            Double  femalehaircut =500.0;
                            mDatabase.child("services_names").child("Female haircut").child("Novovoronezh").child(key).setValue(femalehaircut);
                            Double  Haircoloring =300.0;
                            mDatabase.child("services_names").child("Hair coloring").child("Novovoronezh").child(key).setValue(Haircoloring);

                            String usluga = "Female haircut";
                            mDatabase.child("barbershops").child(key).child("services").child(usluga).setValue(true);
                            usluga = "Hair coloring";
                            mDatabase.child("barbershops").child(key).child("services").child(usluga).setValue(true);
                            Double  price =500.0;
                            mDatabase.child("barbershops").child(key).child("services_price").child("Hair coloring").setValue(price);
                           // mDatabase.child("services_price").child("Hair coloring").child(key).child("price").setValue(price);

                             price =300.0;
                            mDatabase.child("barbershops").child(key).child("services_price").child("Female haircut").setValue(price);

                         //   mDatabase.child("services_price").child("Female haircut").child(key).child("price").setValue(price);

                         /*   Product product = new Product("default","0.0","https://firebasestorage.googleapis.com/v0/b/test-base-soc-net.appspot.com/o/shopping-paper-bag-outline_318-39786.png?alt=media&token=93a2373e-1336-4fbe-9268-924db09e4fb9");
                            String key = mDatabase.child("shops").child(key1).child("products").push().getKey();
                            mDatabase.child("shops").child(key1).child("products").child(key).setValue(product);

                            Product_varibles_default variebl = new Product_varibles_default("default");
                            mDatabase.child("shops").child(key1).child("products").child(key).child("variebles").setValue(variebl);*/

                            Log.v("AKOV", "NO SHOPS");

                        }
                        // ...
         /*               Usluga usluga = new Usluga("стрижка");
                        mDatabase.child("shop").child("test_barber").child("uslugi").push().setValue(usluga);
                        usluga = new Usluga("окраска");
                        mDatabase.child("shop").child("test_barber").child("uslugi").push().setValue(usluga);
                        usluga = new Usluga("укладка");
                        mDatabase.child("shop").child("test_barber").child("uslugi").push().setValue(usluga);
                        usluga = new Usluga("освадебная причёска");
                        mDatabase.child("shop").child("test_barber").child("uslugi").push().setValue(usluga);
                        usluga = new Usluga("брови");
                        mDatabase.child("shop").child("test_barber").child("uslugi").push().setValue(usluga);*/
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.w(TAG, "getUser:onCancelled", databaseError.toException());
                    }
                });
    }
    public  static void add_default_barbeshop(final DatabaseReference mDatabase, final FirebaseUser user ){
        //дефолтовыймагазин
        final String TAG = "ТЕСТОВЫЙ БАРБЕР ШОП";
        final String userId = user.getUid();
        mDatabase.child("shop").addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        if(!dataSnapshot.exists()) {

                            Shops msg = new Shops("Nady_hair_shop", "barbershop", "https://firebasestorage.googleapis.com/v0/b/test-base-soc-net.appspot.com/o/defaultshop.png?alt=media&token=92cc5bdb-bb0d-4a03-a292-da6ef5eb622d",userId);
                            //    String key=mDatabase.child("shops").push().getKey();


                            mDatabase.child("shop").setValue(msg);





                         /*   Product product = new Product("default","0.0","https://firebasestorage.googleapis.com/v0/b/test-base-soc-net.appspot.com/o/shopping-paper-bag-outline_318-39786.png?alt=media&token=93a2373e-1336-4fbe-9268-924db09e4fb9");
                            String key = mDatabase.child("shops").child(key1).child("products").push().getKey();
                            mDatabase.child("shops").child(key1).child("products").child(key).setValue(product);

                            Product_varibles_default variebl = new Product_varibles_default("default");
                            mDatabase.child("shops").child(key1).child("products").child(key).child("variebles").setValue(variebl);*/

                            Log.v("AKOV", "NO SHOPS");

                        }
                        // ...
                        Usluga usluga = new Usluga("стрижка");
                        mDatabase.child("shop").child("test_barber").child("uslugi").push().setValue(usluga);
                        usluga = new Usluga("окраска");
                        mDatabase.child("shop").child("test_barber").child("uslugi").push().setValue(usluga);
                        usluga = new Usluga("укладка");
                        mDatabase.child("shop").child("test_barber").child("uslugi").push().setValue(usluga);
                        usluga = new Usluga("освадебная причёска");
                        mDatabase.child("shop").child("test_barber").child("uslugi").push().setValue(usluga);
                        usluga = new Usluga("брови");
                        mDatabase.child("shop").child("test_barber").child("uslugi").push().setValue(usluga);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.w(TAG, "getUser:onCancelled", databaseError.toException());
                    }
                });
    }
    public  static void add_test_workdays(final DatabaseReference mDatabase, final FirebaseUser user ){
        //дефолтовыймагазин
        final String TAG = "ДЕФОЛТНЫЙ БАРБЕР ШОП";
        final String userId = user.getUid();
        mDatabase.child("shop").child("test_barber").child("workdays").addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        if(!dataSnapshot.exists()) {
                            ArrayList<MyDennedeli> mydays = new ArrayList();
                            mydays=get_days_ofmouth1();
                            int z;
                            z=mydays.size();
                            for (int i = 0; i < z; i++){
                                if(mydays.get(i).getDayinweek()==1||mydays.get(i).getDayinweek()==7){
                                    Clock mcm = new Clock("8:00","VOHODNOI");
                                    mDatabase.child("shop").child("test_barber").child("workdays").child(mydays.get(i).getDay()).push().setValue(mcm);
                                }
                                else {
                                    Clock mcm = new Clock("8:00", "free");
                                    mDatabase.child("shop").child("test_barber").child("workdays").child(mydays.get(i).getDay()).push().setValue(mcm);
                                    Clock mcm1 = new Clock("8:30", "free");
                                    mDatabase.child("shop").child("test_barber").child("workdays").child(mydays.get(i).getDay()).push().setValue(mcm1);
                                }
                            }

                         /*   Clock mcm = new Clock("8:00","free");
                            mDatabase.child("shop").child("test_barber").child("workdays").child("20170121").push().setValue(mcm);
                            mcm = new Clock("8:30","free");
                            mDatabase.child("shop").child("test_barber").child("workdays").child("20170121").push().setValue(mcm);
                            mcm = new Clock("9:00","free");
                            mDatabase.child("shop").child("test_barber").child("workdays").child("20170122").push().setValue(mcm);
                            mcm = new Clock("9:30","free");
                            mDatabase.child("shop").child("test_barber").child("workdays").child("20170122").push().setValue(mcm);*/
                           /* mDatabase.child("shop").child("test_barber").child("workdays").child("20170116").child("8:00").setValue("free");
                            mDatabase.child("shop").child("test_barber").child("workdays").child("20170116").child("8:30").setValue("free");
                            mDatabase.child("shop").child("test_barber").child("workdays").child("20170116").child("9:00").setValue("free");
                            mDatabase.child("shop").child("test_barber").child("workdays").child("20170117").child("9:00").setValue("free");
                            mDatabase.child("shop").child("test_barber").child("workdays").child("20170117").child("9:30").setValue("free");
                            mDatabase.child("shop").child("test_barber").child("workdays").child("20170117").child("10:00").setValue("free");*/

                                 }

                        // ...

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.w(TAG, "getUser:onCancelled", databaseError.toException());
                    }
                });
    }
    public  static  ArrayList<MyDennedeli> get_days_ofmouth1(){

        ArrayList<MyDennedeli> days = new ArrayList();
        Calendar mycal = Calendar.getInstance();
        int today_month;
        int today_year;
        int day_in_month;
        today_year = mycal.get(Calendar.YEAR);
        today_month = mycal.get(Calendar.MONTH);


        day_in_month=mycal.getActualMaximum(Calendar.DAY_OF_MONTH);

        for (int i = 1; i <= day_in_month; i++){
            Calendar testcal = Calendar.getInstance();

            String month = String.format("%02d", today_month+1);
            String day = String.format("%02d",i);

            testcal.set(Calendar.DAY_OF_MONTH,i);
            int dayofweek = testcal.get(Calendar.DAY_OF_WEEK) ;
            Log.w("AKOV",Integer.toString(today_year)+month+day+ "день недели " + dayofweek);
            MyDennedeli dm = new MyDennedeli(Integer.toString(today_year)+month+day, dayofweek);
           // days.add(Integer.toString(today_year)+month+day);
            days.add(dm);
        }

// сдедующий месяц!!!
        Calendar mycal1 = Calendar.getInstance();
        mycal1.set(Calendar.MONTH, today_month+1);
        Log.w("AKOV","месяц" + mycal1.get(Calendar.MONTH) + " сегодня дней в месяце" + mycal.getActualMaximum(Calendar.DAY_OF_MONTH));
        int next_month = mycal1.get(Calendar.MONTH)+1;
        int day_in_next_month=mycal1.getActualMaximum(Calendar.DAY_OF_MONTH);
        for (int i = 1; i <= day_in_next_month; i++){
            Calendar testcal1 = Calendar.getInstance();
            testcal1.set(Calendar.MONTH, today_month+1);
            String next_month1 = String.format("%02d", next_month);
            String day = String.format("%02d",i);

            testcal1.set(Calendar.DAY_OF_MONTH,i);
            int dayofweek = testcal1.get(Calendar.DAY_OF_WEEK) ;

            Log.w("AKOV",Integer.toString(today_year)+next_month1+day + "день недели В следующем месяце " + dayofweek);

            MyDennedeli dm = new MyDennedeli(Integer.toString(today_year)+next_month1+day, dayofweek);
            //  days.add(Integer.toString(today_year)+next_month1+day);
            days.add(dm);
        }
        mycal.clear();
        mycal1.clear();

     /* Calendar mycal1 = Calendar.getInstance();
      Log.w("AKOV","месяц    =" + mycal.get(Calendar.MONTH)  + mycal.get(Calendar.DAY_OF_MONTH));
        Log.w("AKOV","месяц11   =" + mycal1.get(Calendar.MONTH)+1  + mycal1.get(Calendar.DAY_OF_MONTH));*/
        return days;
    }

}
