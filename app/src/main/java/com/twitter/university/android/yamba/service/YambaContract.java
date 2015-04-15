package com.twitter.university.android.yamba.service;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;


public class YambaContract {
    private YambaContract() { }

    public static final int VERSION = 2;

    /** Yamba Service */
    public static class Service {
        private Service() {}

        // Call to the Yamba Service
        public static final String ACTION_EXECUTE
            = "com.twitter.university.android.yamba.service.action.EXECUTE";

        // Permission required to call the Yamba Service
        public static final String PERMISSION_EXECUTE
            = "com.twitter.university.android.yamba.service.permission.EXECUTE";

        public static final String PACKAGE
            = "com.twitter.university.android.yamba.service";

        // Execute action method id parameter
        public static final String PARAM_OP
            = "com.twitter.university.android.yamba.service.TWEET_OP";
        // method id parameter value: post a message.  Parameter is the message
        public static final int OP_POST = -1;
        // method id parameter value: poll once.  No parameters.
        public static final int OP_POLL = -2;
        // method id parameter value: start polling.  No parameters.
        public static final int OP_START_POLLING = -3;
        // method id parameter value: stop polling.  No parameters.
        public static final int OP_STOP_POLLING = -4;

        // Parameter to EXECUTE|POST: String - the tweet message to be posted
        public static final String PARAM_TWEET
            = "com.twitter.university.android.yamba.service.TWEET";

        // Yamba Service timeline updated broadcast
        public static final String ACTION_TIMELINE_UPDATED
            = "com.twitter.university.android.yamba.service.action.TIMELINE_UPDATED";

        // Permission required to receive the timeline updated broadcast
        public static final String PERMISSION_RECEIVE_TIMELINE_UPDATE
            = "com.twitter.university.android.yamba.service.permission.RECEIVE_TIMELINE_UPDATED";

        // Timeline updated broadcast parameter: number of new tweetes.
        public static final String PARAM_COUNT
            = "com.twitter.university.android.yamba.action.NEW_TWEET_COUNT";

        // Yamba Service post complete
        public static final String ACTION_POST_COMPLETE
            = "com.twitter.university.android.yamba.service.action.POST_COMPLETE";

        // Permission required to receive the post complete message
        public static final String PERMISSION_RECEIVE_POST_COMPLETE
            = "com.twitter.university.android.yamba.service.permission.RECEIVE_POST_COMPLETE";

        // Parameter to ACTION_POST_COMPLETE: boolean - true iff post succeeded
        public static final String PARAM_POST_SUCCEEDED
            = "com.twitter.university.android.yamba.action.NEW_TWEET_COUNT";
    }

    public static final String AUTHORITY = "com.twitter.university.android.yamba";

    public static final Uri BASE_URI = new Uri.Builder()
        .scheme(ContentResolver.SCHEME_CONTENT)
        .authority(AUTHORITY)
        .build();

    public static final String PERMISSION_READ
        = "com.twitter.university.android.yamba.timeline.permission.READ";
    public static final String PERMISSION_WRITE
        = "com.twitter.university.android.yamba.timeline.permission.WRITE";

    public static class Timeline {
        private Timeline() { }

        public static final String TABLE = "timeline";

        public static final Uri URI = BASE_URI.buildUpon().appendPath(TABLE).build();

        private static final String MINOR_TYPE = "/vnd." + AUTHORITY + "." + TABLE;

        public static final String ITEM_TYPE
            = ContentResolver.CURSOR_ITEM_BASE_TYPE + MINOR_TYPE;
        public static final String DIR_TYPE
            = ContentResolver.CURSOR_DIR_BASE_TYPE + MINOR_TYPE;

        public static class Columns {
            public static final String ID = BaseColumns._ID;
            public static final String HANDLE = "handle";
            public static final String TWEET = "tweet";
            public static final String TIMESTAMP = "timestamp";
        }
    }

    public static class MaxTimeline {
        private MaxTimeline() { }
        public static final String TABLE = "maxTimeline";

        public static final Uri URI = BASE_URI.buildUpon().appendPath(TABLE).build();

        private static final String MINOR_TYPE = "/vnd." + AUTHORITY + "." + TABLE;

        public static final String ITEM_TYPE
            = ContentResolver.CURSOR_ITEM_BASE_TYPE + MINOR_TYPE;

        public static class Columns {
            public static final String TIMESTAMP = "timestamp";
        }
    }
}
