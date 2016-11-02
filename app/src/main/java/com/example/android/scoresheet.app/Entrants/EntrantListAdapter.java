package com.example.android.scoresheet.app.Entrants;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.scoresheet.app.R;

/**
 * Created by Kari Stromsland on 9/18/2016.
 */
public class EntrantListAdapter extends CursorAdapter {

    public static class ViewHolder {
        public final TextView entrantfirstnameView;
        public final TextView entrantlastnameView;

        public ViewHolder(View view) {
            entrantfirstnameView = (TextView) view.findViewById(R.id.list_item_first_name_textview);
            entrantlastnameView = (TextView) view.findViewById(R.id.list_item_last_name_textview);
        }
    }

    public EntrantListAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {

        View view = LayoutInflater.from(context).inflate(R.layout.list_item_entrant, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);
        view.setTag(viewHolder);

        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        ViewHolder viewHolder = (ViewHolder) view.getTag();

        String firstname = cursor.getString(EntrantListFragment.COL_FIRST_NAME);
        String lastname = cursor.getString(EntrantListFragment.COL_LAST_NAME);

        viewHolder.entrantfirstnameView.setText(firstname);
        viewHolder.entrantlastnameView.setText(lastname);
    }
}
