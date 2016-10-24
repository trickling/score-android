package com.example.android.scoresheet.app.Users;

import android.content.ContentValues;
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
public class UserNewFragment extends Fragment{
    public static final String LOG_TAG = UserNewFragment.class.getSimpleName();

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

    static final String USERNEW_URI = "URI";

    private Uri mUri;
    private Uri newUri;
    private EditText mFirstNameNewText;
    private EditText mLastNameNewText;
    private EditText mRoleNewText;
    private EditText mStatusNewText;
    private EditText mEmailNewText;
    private EditText mPasswordNewText;

    public UserNewFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_new_user, container, false);

        mFirstNameNewText = (EditText) rootView.findViewById(R.id.user_first_name_text_edit);
        mLastNameNewText = (EditText) rootView.findViewById(R.id.user_last_name_text_edit);
        mRoleNewText = (EditText) rootView.findViewById(R.id.user_role_text_edit);
        mStatusNewText = (EditText) rootView.findViewById(R.id.user_status_text_edit);
        mEmailNewText = (EditText) rootView.findViewById(R.id.user_email_text_edit);
        mPasswordNewText = (EditText) rootView.findViewById(R.id.user_password_text_edit);

        Button save_new_button = (Button) rootView.findViewById(R.id.userNewSave);

        View.OnClickListener new_saveOnClickListener = new View.OnClickListener() {
            public void onClick(View v) {

                ContentValues mNewContentValues = new ContentValues();

                mNewContentValues.put(UserEntry.COLUMN_FIRST_NAME, mFirstNameNewText.getText().toString());
                mNewContentValues.put(UserEntry.COLUMN_LAST_NAME, mLastNameNewText.getText().toString());
                mNewContentValues.put(UserEntry.COLUMN_ROLE, mRoleNewText.getText().toString());
                mNewContentValues.put(UserEntry.COLUMN_STATUS, mStatusNewText.getText().toString());
                mNewContentValues.put(UserEntry.COLUMN_EMAIL, mEmailNewText.getText().toString());
                mNewContentValues.put(UserEntry.COLUMN_PASSWORD, mPasswordNewText.getText().toString());

                newUri = getContext().getContentResolver().insert(UserEntry.CONTENT_URI, mNewContentValues);

            }
        };

        save_new_button.setOnClickListener(new_saveOnClickListener);

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