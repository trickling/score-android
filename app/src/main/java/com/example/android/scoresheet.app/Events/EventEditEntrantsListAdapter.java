package com.example.android.scoresheet.app.Events;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckedTextView;

import com.example.android.scoresheet.app.R;

/**
 * Created by Kari Stromsland on 9/19/2016.
 */
public class EventEditEntrantsListAdapter extends CursorAdapter{
    // Adapter that exposes data from a Cursor to a ListView widget.


    // A ViewHolder describes an item view and metadata about its place within RecyclerView
    public static class ViewHolder {
        public final CheckedTextView entrantInfoView;

        public ViewHolder(View view) {
            entrantInfoView = (CheckedTextView) view.findViewById(R.id.list_item_event_edit_entrants_textview);
        }
    }

    // Constructor
    public EventEditEntrantsListAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {

        View view = LayoutInflater.from(context).inflate(R.layout.list_item_event_edit_entrants, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);
        view.setTag(viewHolder);

        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        ViewHolder viewHolder = (ViewHolder) view.getTag();

        String first_name = cursor.getString(EventEditEntrantsFragment.COL_FIRST_NAME);
        String last_name = cursor.getString(EventEditEntrantsFragment.COL_LAST_NAME);

        viewHolder.entrantInfoView.setText(first_name + " " + last_name);

        if (EventEditEntrantsFragment.checked_status(context, cursor)){
            viewHolder.entrantInfoView.setChecked(true);
            viewHolder.entrantInfoView.setCheckMarkDrawable(android.R.drawable.checkbox_on_background);
        }else{
            viewHolder.entrantInfoView.setChecked(false);
            viewHolder.entrantInfoView.setCheckMarkDrawable(android.R.drawable.checkbox_off_background);
        }
    }
}
