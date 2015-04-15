/*
 * Copyright (c) 2014 Twitter Inc.
 */
package com.twitter.university.android.yamba.service;

import android.content.Context;
import android.content.Intent;

public class YambaServiceHelper {

    private final Context ctxt;

    public YambaServiceHelper(Context ctxt) {
        this.ctxt = ctxt.getApplicationContext();
    }

    public void post(String tweet) {
        Intent i = new Intent(YambaContract.Service.ACTION_EXECUTE);
        i.setPackage(YambaContract.Service.PACKAGE);
        i.putExtra(YambaContract.Service.PARAM_OP, YambaContract.Service.OP_POST);
        i.putExtra(YambaContract.Service.PARAM_TWEET, tweet);
        ctxt.startService(i);
    }
}
