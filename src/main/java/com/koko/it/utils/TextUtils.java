package com.koko.it.utils;

public class TextUtils {

    public static boolean isEmpty(CharSequence charSequence){
        return charSequence == null || charSequence.length() == 0;
    }

}