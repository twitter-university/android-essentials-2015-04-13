/*
 * Copyright (c) 2014 Twitter Inc.
 */
/*
 * Copyright (c) 2014 Twitter Inc.
 */
package com.twitter.university.android.yamba;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;


public class YambaService extends IntentService {
    private static final String TAG = "SVC";

    private static final String PARAM_OP = "YambaService.OP";
    private static final String PARAM_TWEET = "YambaService.TWEET";

    private static final int OP_POST = -1;
    private static final int OP_POST_COMPLETE = -2;

    private static class UIHandler extends Handler {
        private final Context ctxt;

        public UIHandler(Context ctxt) {
            super(Looper.getMainLooper());
            this.ctxt = ctxt.getApplicationContext();
        }

        @Override
        public void handleMessage(Message msg) {
            int op = msg.what;
            switch (op) {
                case OP_POST_COMPLETE:
                    Toast.makeText(ctxt, msg.arg1, Toast.LENGTH_LONG).show();
                    break;

                default:
                    super.handleMessage(msg);
            }
        }
    }

    public static void post(Context ctxt, String tweet) {
        Intent i = new Intent(ctxt, YambaService.class);
        i.putExtra(PARAM_OP, OP_POST);
        i.putExtra(PARAM_TWEET, tweet);
        ctxt.startService(i);
    }


    private volatile Handler hdlr;

    public YambaService() { super(TAG); }

    @Override
    public void onCreate() {
        super.onCreate();
        hdlr = new UIHandler(this);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        int op = intent.getIntExtra(PARAM_OP, 0);
        switch (op) {
            case OP_POST:
                postTweet(intent.getStringExtra(PARAM_TWEET));
                break;
            default:
                Log.w(TAG, "unexpected op: " + op);
        }
    }

    private void postTweet(String tweet) {
        // Simulate failing network call
        try { Thread.sleep(3 * 30 * 1000); }
        catch (InterruptedException ignore) { }

        hdlr.obtainMessage(OP_POST_COMPLETE, R.string.tweet_succeeded, 0).sendToTarget();
    }
}
