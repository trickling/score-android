package com.example.android.scoresheet.app.Events;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.CursorAdapter;
import android.widget.TextView;

import com.example.android.scoresheet.app.R;

/**
 * Created by Kari Stromsland on 10/4/2016.
 */
public class EventViewDetailAdapter extends CursorAdapter{
    public static class ViewHolder {
        public final TextView userListView;

        public ViewHolder(View view) {
            userListView = (TextView) view.findViewById(R.id.list_item_event_user_textview);
        }
    }

    public EventViewDetailAdapter (Context context, Cursor c, int flags) {
        super(context, c, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {

        View view = LayoutInflater.from(context).inflate(R.layout.list_item_event_user, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);
        view.setTag(viewHolder);

        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        ViewHolder viewHolder = (ViewHolder) view.getTag();
        String description = cursor.getString(EventViewDetailFragment.COL_USER_DESC);
        viewHolder.userListView.setText(description);
    }
}
