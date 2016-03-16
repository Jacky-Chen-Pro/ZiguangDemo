package com.boyu100.boyoforandroidpad.utils;

import android.widget.Toast;

import com.boyu100.boyoforandroidpad.base.AppApplication;

/**
 * Created by Jacky on 2016/1/15.
 */
public class ToastUtils {
    public static void showShorToast(String msg) {
        Toast.makeText(AppApplication.context(),msg,Toast.LENGTH_SHORT).show();
    }

    public static void showLongToast(String msg) {
        Toast.makeText(AppApplication.context(),msg,Toast.LENGTH_LONG).show();
    }
}
