/*
 * Copyright (c) 2014 Twitter Inc.
 */
package com.twitter.university.android.yamba;

import android.app.ListFragment;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.twitter.university.android.yamba.service.YambaContract;


public class TimelineFragment extends ListFragment
    implements LoaderManager.LoaderCallbacks<Cursor>
{
    private static final int TIMELINE_LOADER = 42;

    private static final String[] FROM = {
        YambaContract.Timeline.Columns.HANDLE,
        YambaContract.Timeline.Columns.TWEET,
        YambaContract.Timeline.Columns.TIMESTAMP};

    private static final int[] TO = {
        R.id.timeline_row_handle,
        R.id.timeline_row_tweet,
        R.id.timeline_row_time};

    static class TimestampBinder implements SimpleCursorAdapter.ViewBinder {

        @Override
        public boolean setViewValue(View view, Cursor cursor, int columnIndex) {
            if (R.id.timeline_row_time != view.getId())  { return false; }

            long t = cursor.getLong(columnIndex);
            CharSequence ts = DateUtils.getRelativeTimeSpanString(t);
            ((TextView) view).setText(ts);

            return true;
        }
    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(
            getActivity(),
            YambaContract.Timeline.URI,
            null,
            null,
            null,
            YambaContract.Timeline.Columns.TIMESTAMP + " DESC");
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cur) {
        ((SimpleCursorAdapter) getListAdapter()).swapCursor(cur);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        ((SimpleCursorAdapter) getListAdapter()).swapCursor(null);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle state) {
        View v = super.onCreateView(inflater, container, state);

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                getActivity(),
                R.layout.timeline_row,
                null,
                FROM,
                TO,
                0);

        adapter.setViewBinder(new TimestampBinder());
        setListAdapter(adapter);


        getLoaderManager().initLoader(TIMELINE_LOADER, null, this);

        return v;
    }
}
