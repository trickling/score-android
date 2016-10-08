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
public class UserEditFragment extends Fragment {

    public static final String LOG_TAG = UserEditFragment.class.getSimpleName();

    private static final String[] _COLUMNS = {
            UserEntry.TABLE_NAME + "." + UserEntry._ID,
            UserEntry.COLUMN_USER_DESC
    };
    static final int COL_USER_ID = 0;
    static final int COL_USER_DESC = 1;

    static final String USEREDIT_URI = "URI";

    private Uri mUri;
    private Uri editUri;
    private EditText mDescrEditText;
    private String DescrEditText;

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

        mDescrEditText = (EditText) rootView.findViewById(R.id.userEditText);

        mDescrEditText.setText(UserEntry.getUserDescFromUri(mUri));

        Button save_edit_button = (Button) rootView.findViewById(R.id.userEditSave);

        View.OnClickListener edit_saveOnClickListener = new View.OnClickListener() {
            public void onClick(View v) {
                DescrEditText = mDescrEditText.getText().toString();

                if ( !(DescrEditText.equals("")) ) {
                    editUri =UserEntry.buildUserDescUri(DescrEditText);
                    ContentValues mEditContentValues = new ContentValues();
                    mEditContentValues.put(UserEntry.COLUMN_USER_DESC, DescrEditText);
                    String selection = UserEntry.COLUMN_USER_DESC + " = ?";
                    String[] selectionArgs = {UserEntry.getUserDescFromUri(mUri)};
                    getContext().getContentResolver().update(UserEntry.CONTENT_URI, mEditContentValues, selection, selectionArgs);
                }
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