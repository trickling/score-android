package com.example.android.scoresheet.app.Users;

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
public class UserListAdapter extends CursorAdapter {
    public static class ViewHolder {
        public final TextView userFirstNameView;
        public final TextView userRoleView;

        public ViewHolder(View view) {
            userFirstNameView = (TextView) view.findViewById(R.id.list_item_first_name_textview);
            userRoleView = (TextView) view.findViewById(R.id.list_item_role_textview);
        }
    }

    public UserListAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {

        View view = LayoutInflater.from(context).inflate(R.layout.list_item_user, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);
        view.setTag(viewHolder);

        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        ViewHolder viewHolder = (ViewHolder) view.getTag();

        String first_name = cursor.getString(UserListFragment.COL_FIRST_NAME);
        String role = cursor.getString(UserListFragment.COL_ROLE);

        viewHolder.userFirstNameView.setText(first_name);
        viewHolder.userRoleView.setText(role);
    }
}
