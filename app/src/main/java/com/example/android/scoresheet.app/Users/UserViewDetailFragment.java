package com.example.android.scoresheet.app.Users;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.scoresheet.app.R;
import com.example.android.scoresheet.app.data.ScoreSheetContract.UserEntry;

/**
 * Created by Kari Stromsland on 9/18/2016.
 */
public class UserViewDetailFragment extends Fragment {
    public static final String LOG_TAG = UserViewDetailFragment.class.getSimpleName();

    private static final String[] USER_COLUMNS = {
            UserEntry.TABLE_NAME + "." + UserEntry._ID,
            UserEntry.COLUMN_FIRST_NAME,
            UserEntry.COLUMN_LAST_NAME,
            UserEntry.COLUMN_ROLE,
            UserEntry.COLUMN_APPROVED,
            UserEntry.COLUMN_STATUS,
            UserEntry.COLUMN_EMAIL,
            UserEntry.COLUMN_PASSWORD
    };
    static final int COL_USER_ID = 0;
    static final int COL_FIRST_NAME = 1;
    static final int COL_LAST_NAME = 2;
    static final int COL_ROLE = 3;
    static final int COL_APPROVED = 4;
    static final int COL_STATUS = 5;
    static final int COL_EMAIL = 6;
    static final int COL_PASSWD = 7;

    static final String USERVIEW_URI = "URI";

    private Uri mUri;
    private TextView mFirstNameViewText;
    private TextView mLastNameViewText;
    private TextView mRoleViewText;
    private TextView mStatusViewText;
    private TextView mEmailViewText;

    public UserViewDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_detail_view_user, container, false);

        Bundle arguments = getArguments();
        if (arguments != null) {
            mUri = arguments.getParcelable(UserViewDetailFragment.USERVIEW_URI);
        }

        mFirstNameViewText = (TextView) rootView.findViewById(R.id.user_first_name_text_view);
        mLastNameViewText = (TextView) rootView.findViewById(R.id.user_last_name_text_view);
        mRoleViewText = (TextView) rootView.findViewById(R.id.user_role_text_view);
        mStatusViewText = (TextView) rootView.findViewById(R.id.user_status_text_view);
        mEmailViewText = (TextView) rootView.findViewById(R.id.user_email_text_view);


        String sortOrder = UserEntry._ID + " ASC";
        String selection = UserEntry._ID + " = ?";
        String[] selectionArgs = {Long.valueOf(UserEntry.getUserIdFromUri(mUri)).toString()};

        Cursor c = getContext().getContentResolver().query(UserEntry.CONTENT_URI, USER_COLUMNS, selection, selectionArgs, sortOrder);

        if(c.moveToFirst()) {
            mFirstNameViewText.setText(c.getString(COL_FIRST_NAME));
            mLastNameViewText.setText(c.getString(COL_LAST_NAME));
            mRoleViewText.setText(c.getString(COL_ROLE));
            mStatusViewText.setText(c.getString(COL_STATUS));
            mEmailViewText.setText(c.getString(COL_EMAIL));
        }

        return rootView;

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}