package com.tengda.agency;

import android.util.Log;

/**
 * Created by DongHao on 2016/12/14.
 * Description:
 */

public class Vendor implements Sell {
    @Override
    public void sell() {
        System.out.println("In sell method");
        Log.e("test:","In sell method");
    }

    @Override
    public void add() {
        System.out.println("In add method");
        Log.e("test:","In add method");
    }
}
