package com.example.android.scoresheet.app.Events;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckedTextView;
import android.support.v4.widget.CursorAdapter;

import com.example.android.scoresheet.app.R;

/**
 * Created by Kari Stromsland on 10/3/2016.
 */
public class EventEditUsersListAdapter extends CursorAdapter{
    // Adapter that exposes data from a Cursor to a ListView widget.


    // A ViewHolder describes an item view and metadata about its place within RecyclerView
    public static class ViewHolder {
        public final CheckedTextView userFirstNameView;

        public ViewHolder(View view) {
            userFirstNameView = (CheckedTextView) view.findViewById(R.id.list_item_event_edit_users_textview);
        }
    }

    // Constructor
    public EventEditUsersListAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {

        View view = LayoutInflater.from(context).inflate(R.layout.list_item_event_edit_users, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);
        view.setTag(viewHolder);

        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        ViewHolder viewHolder = (ViewHolder) view.getTag();

        String first_name = cursor.getString(EventEditUsersFragment.COL_FIRST_NAME);

        viewHolder.userFirstNameView.setText(first_name);

        if (EventEditUsersFragment.checked_status(context, cursor)){
            viewHolder.userFirstNameView.setChecked(true);
            viewHolder.userFirstNameView.setCheckMarkDrawable(android.R.drawable.checkbox_on_background);
        }else{
            viewHolder.userFirstNameView.setChecked(false);
            viewHolder.userFirstNameView.setCheckMarkDrawable(android.R.drawable.checkbox_off_background);
        }
    }
}
