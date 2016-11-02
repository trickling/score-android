package com.example.android.scoresheet.app.Users;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.android.scoresheet.app.R;
import com.example.android.scoresheet.app.data.ScoreSheetContract.UserEntry;

/**
 * Created by Kari Stromsland on 9/18/2016.
 */
public class UserEditFragment extends Fragment {

    public static final String LOG_TAG = UserEditFragment.class.getSimpleName();

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

    static final String USEREDIT_URI = "URI";

    private Uri mUri;
    private Uri editUri;
    private EditText mFirstNameEditText;
    private EditText mLastNameEditText;
    private EditText mRoleEditText;
    private EditText mApprovedEditText;
    private EditText mStatusEditText;
    private EditText mEmailEditText;
    private EditText mPasswordEditText;

    public UserEditFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        Bundle arguments = getArguments();
        if (arguments != null) {
            mUri = arguments.getParcelable(UserEditFragment.USEREDIT_URI);
        }

        View rootView = inflater.inflate(R.layout.fragment_edit_user, container, false);

        mFirstNameEditText = (EditText) rootView.findViewById(R.id.user_first_name_text_edit);
        mLastNameEditText = (EditText) rootView.findViewById(R.id.user_last_name_text_edit);
        mRoleEditText = (EditText) rootView.findViewById(R.id.user_role_text_edit);
        mStatusEditText = (EditText) rootView.findViewById(R.id.user_status_text_edit);
        mEmailEditText = (EditText) rootView.findViewById(R.id.user_email_text_edit);
        mPasswordEditText = (EditText) rootView.findViewById(R.id.user_password_text_edit);

        String sortOrder = UserEntry._ID + " ASC";
        String selection = UserEntry._ID + " = ?";
        String[] selectionArgs = {Long.valueOf(UserEntry.getUserIdFromUri(mUri)).toString()};
        String FirstName;

        Cursor c = getContext().getContentResolver().query(UserEntry.CONTENT_URI, USER_COLUMNS, selection, selectionArgs, sortOrder);

        if(c.moveToFirst()) {
            mFirstNameEditText.setText(c.getString(COL_FIRST_NAME));
            mLastNameEditText.setText(c.getString(COL_LAST_NAME));
            mRoleEditText.setText(c.getString(COL_ROLE));
            mStatusEditText.setText(c.getString(COL_STATUS));
            mEmailEditText.setText(c.getString(COL_EMAIL));
            mPasswordEditText.setText(c.getString(COL_PASSWD));

        }

        Button save_edit_button = (Button) rootView.findViewById(R.id.userEditSave);

        View.OnClickListener edit_saveOnClickListener = new View.OnClickListener() {
            public void onClick(View v) {

                ContentValues mEditContentValues = new ContentValues();

                mEditContentValues.put(UserEntry.COLUMN_FIRST_NAME, mFirstNameEditText.getText().toString());
                mEditContentValues.put(UserEntry.COLUMN_LAST_NAME, mLastNameEditText.getText().toString());
                mEditContentValues.put(UserEntry.COLUMN_ROLE, mRoleEditText.getText().toString());
                mEditContentValues.put(UserEntry.COLUMN_STATUS, mStatusEditText.getText().toString());
                mEditContentValues.put(UserEntry.COLUMN_EMAIL, mEmailEditText.getText().toString());
                mEditContentValues.put(UserEntry.COLUMN_PASSWORD, mPasswordEditText.getText().toString());

                String selection = UserEntry._ID + " = ?";
                String[] selectionArgs = {Long.valueOf(UserEntry.getUserIdFromUri(mUri)).toString()};
                getContext().getContentResolver().update(UserEntry.CONTENT_URI, mEditContentValues, selection, selectionArgs);

                Intent intent = new Intent(getContext(), UserViewDetailActivity.class).setData(mUri);
                startActivity(intent);
            }
        };

        save_edit_button.setOnClickListener(edit_saveOnClickListener);

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