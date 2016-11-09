package com.android.core.manage;

import android.widget.Toast;

import com.android.core.MainApplication;
/**
 * Created by bug on 2015/09/23.
 */
public class Tip {

    private Tip() { }

    public static void show(String text){
        Toast.makeText(MainApplication.getContext(),text,Toast.LENGTH_LONG).show();
    }

    public static void showShort(String text){
        Toast.makeText(MainApplication.getContext(),text,Toast.LENGTH_SHORT).show();
    }

}
