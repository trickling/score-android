package com.example.android.scoresheet.app.Events;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.scoresheet.app.R;

/**
 * Created by Kari Stromsland on 9/7/2016.
 */
public class EventListAdapter extends CursorAdapter {

    public static class ViewHolder {
        public final TextView descriptionView;

        public ViewHolder(View view) {
            descriptionView = (TextView) view.findViewById(R.id.list_item_event_textview);
        }
    }

    public EventListAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {

        View view = LayoutInflater.from(context).inflate(R.layout.list_item_event, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);
        view.setTag(viewHolder);

        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        ViewHolder viewHolder = (ViewHolder) view.getTag();

        String description = cursor.getString(EventListFragment.COL_EVENT_DESC);

        viewHolder.descriptionView.setText(description);
    }

}
