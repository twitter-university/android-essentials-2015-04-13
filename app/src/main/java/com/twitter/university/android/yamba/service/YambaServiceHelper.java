/*
 * Copyright (c) 2014 Twitter Inc.
 */
package com.twitter.university.android.yamba.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.widget.Toast;

import com.twitter.university.android.yamba.R;


public class YambaServiceHelper {

    private final Context ctxt;
    private final IntentFilter filter;
    private final BroadcastReceiver postCompleteReceiver;

    public YambaServiceHelper(Context ctxt) {
        this.ctxt = ctxt.getApplicationContext();

        filter = new IntentFilter();
        filter.addAction(YambaContract.Service.ACTION_POST_COMPLETE);

        postCompleteReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                int msg = (intent.getBooleanExtra(YambaContract.Service.PARAM_POST_SUCCEEDED, false))
                        ? R.string.tweet_succeeded
                        : R.string.tweet_failed;

                Toast.makeText(YambaServiceHelper.this.ctxt, msg, Toast.LENGTH_LONG).show();

                YambaServiceHelper.this.ctxt.unregisterReceiver(this);
            }
        };
    }

    public void post(String tweet) {
        Intent i = new Intent(YambaContract.Service.ACTION_EXECUTE);
        i.setPackage(YambaContract.Service.PACKAGE);
        i.putExtra(YambaContract.Service.PARAM_OP, YambaContract.Service.OP_POST);
        i.putExtra(YambaContract.Service.PARAM_TWEET, tweet);

        ctxt.registerReceiver(postCompleteReceiver, filter);

        ctxt.startService(i);
    }
}
