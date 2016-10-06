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
            // In this case the id needs to be fully qualified with a table name, since
            // the content provider joins the location & weather tables in the background
            // (both have an _id column)
            // On the one hand, that's annoying.  On the other, you can search the weather table
            // using the location set by the user, which is only in the Location table.
            // So the convenience is worth it.
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

//    private View.OnFocusChangeListener descrOnFocusChangeListener = new View.OnFocusChangeListener() {
//        public void onFocusChange(View v, Boolean hasFocus) {
//            if ( mDescrEditText.getText().toString() != "" ) {
//                mUri = ScoreSheetContract.EventEntry.buildEventDesc(mDescrEditText.getText().toString());
//                ContentValues mEditContentValues = new ContentValues();
//                mEditContentValues.put(ScoreSheetContract.EventEntry.COLUMN_SHORT_DESC, mDescrEditText.getText().toString());
//                newUri = getContext().getContentResolver().insert(mUri, mEditContentValues);
//            }
//        }
//    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Add this line in order for this fragment to handle menu events.
//        setHasOptionsMenu(true);
    }

//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        inflater.inflate(R.menu.event_new_fragment, menu);
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
////        if (id == R.id.action_map) {
////            openPreferredLocationInMap();
////            return true;
////        }
//        return super.onOptionsItemSelected(item);
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_new_user, container, false);

//        Bundle arguments = getArguments();
//        if (arguments != null) {
//            mUri = arguments.getParcelable(EventNewFragment.EVENTNEW_URI);
//        }

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
