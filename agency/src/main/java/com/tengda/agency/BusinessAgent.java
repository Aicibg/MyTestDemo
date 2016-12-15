package com.tengda.agency;

/**
 * Created by DongHao on 2016/12/14.
 * Description:
 */

public class BusinessAgent implements Sell {
    private Vendor mVendor;

    public BusinessAgent(Vendor vendor) {
        mVendor = vendor;
    }

    @Override
    public void sell() {
        mVendor.sell();
    }

    @Override
    public void add() {
        mVendor.add();
    }
}
