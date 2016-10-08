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
            UserEntry.COLUMN_USER_DESC
    };
    static final int COL_USER_ID = 0;
    static final int COL_USER_DESC = 1;

    static final String USERNEW_URI = "URI";

    private Uri mUri;
    private Uri newUri;
    private EditText mDescrNewText;
    private String DescrNewText;
    private Boolean mNewTextFocus;

    public UserNewFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_new_user, container, false);

        mDescrNewText = (EditText) rootView.findViewById(R.id.userNewText);

        Button save_new_button = (Button) rootView.findViewById(R.id.userNewSave);

        View.OnClickListener new_saveOnClickListener = new View.OnClickListener() {
            public void onClick(View v) {
                DescrNewText = mDescrNewText.getText().toString();
                if ( !(DescrNewText.equals("")) ) {
                    mUri = UserEntry.buildUserDescUri(DescrNewText);
                    ContentValues mNewContentValues = new ContentValues();
                    mNewContentValues.put(UserEntry.COLUMN_USER_DESC,DescrNewText);
                    newUri = getContext().getContentResolver().insert(UserEntry.CONTENT_URI, mNewContentValues);
                }
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