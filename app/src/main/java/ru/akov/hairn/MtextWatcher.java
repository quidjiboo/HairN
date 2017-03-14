package ru.akov.hairn;

import android.text.Editable;
import android.text.TextWatcher;

/**
 * Created by User on 14.03.2017.
 */

public class MtextWatcher implements TextWatcher {
    MyCallback_textwatcher myCallback;
    public void registerCallBack(MyCallback_textwatcher callback) {
        this.myCallback = callback;
    }
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        String formatted;
        String regex1 = "(\\+\\d)(\\d{3})";
        String regex2 = "(.+ )(\\d{3})$";
        String regex3 = "(.+\\-)(\\d{2})$";

        // буду реализвывать ввод телефона в формате +х (ххх) ххх-хх-хх
        if (s.toString().matches(regex1)) {
            formatted = String.valueOf(s).replaceFirst(regex1, "$1 ($2) ");
            myCallback.izmenit_text(formatted,formatted.length());
          /*  mphone.setText(formatted);
            mphone.setSelection(formatted.length());*/
        } else if (s.toString().matches(regex2)) {
            formatted = String.valueOf(s).replaceFirst(regex2, "$1$2-");
            myCallback.izmenit_text(formatted,formatted.length());
           /* mphone.setText(formatted);
            mphone.setSelection(formatted.length());*/
        } else if (s.toString().matches(regex3) && s.length() < 18) {
            formatted = String.valueOf(s).replaceFirst(regex3, "$1$2-");
            myCallback.izmenit_text(formatted,formatted.length());
          /*  mphone.setText(formatted);
            mphone.setSelection(formatted.length());*/
        }
    }
}
