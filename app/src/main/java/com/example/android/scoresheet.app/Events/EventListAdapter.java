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
        public final TextView eventNameView;
        public final TextView eventDateView;

        public ViewHolder(View view) {
            eventNameView = (TextView) view.findViewById(R.id.list_item_event_name_textview);
            eventDateView = (TextView) view.findViewById(R.id.list_item_event_date_textview);
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

        String name = cursor.getString(EventListFragment.COL_NAME);
        String date = cursor.getString(EventListFragment.COL_DATE);

        viewHolder.eventNameView.setText(name);
        viewHolder.eventDateView.setText(date);
    }

}
