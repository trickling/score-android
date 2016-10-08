package com.example.android.scoresheet.app.Events;

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
import com.example.android.scoresheet.app.data.ScoreSheetContract.EventEntry;

/**
 * Created by Kari Stromsland on 9/15/2016.
 */
public class EventEditFragment extends Fragment{

    public static final String LOG_TAG = EventEditFragment.class.getSimpleName();

    private static final String[] EVENT_COLUMNS = {
            EventEntry.TABLE_NAME + "." + EventEntry._ID,
            EventEntry.COLUMN_SHORT_DESC
    };
    static final int COL_EVENT_ID = 0;
    static final int COL_EVENT_DESC = 1;

    static final String EVENTEDIT_URI = "URI";

    private Uri mUri;
    private Uri eventUri;
    private Uri editUri;
    private EditText mDescrEditText;
    private String DescrEditText;

    public EventEditFragment() {
        // Required empty public constructor
    }

    public interface Callback {

        public void onEventEditEntrants(Uri editUri);
        public void onEventEditUsers(Uri editUri);
    }

//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        Bundle arguments = getArguments();
        if (arguments != null) {
            mUri = arguments.getParcelable(EventEditFragment.EVENTEDIT_URI);
            DescrEditText = EventEntry.getEventIdDescriptionFromUri(mUri);
        }

        View rootView = inflater.inflate(R.layout.fragment_edit_event, container, false);

        mDescrEditText = (EditText) rootView.findViewById(R.id.eventEditText);

        mDescrEditText.setText(DescrEditText);

        Button save_edit_button = (Button) rootView.findViewById(R.id.eventEditSave);

        View.OnClickListener edit_saveOnClickListener = new View.OnClickListener() {
            public void onClick(View v) {
                DescrEditText = mDescrEditText.getText().toString();

                if ( !(DescrEditText.equals("")) ) {

                    editUri = EventEntry.buildEventDescUri(DescrEditText);
                    ContentValues mEditContentValues = new ContentValues();
                    mEditContentValues.put(EventEntry.COLUMN_SHORT_DESC, DescrEditText);
                    String selection = EventEntry.COLUMN_SHORT_DESC + " = ?";
                    String[] selectionArgs = {EventEntry.getEventIdDescriptionFromUri(mUri)};
                    getContext().getContentResolver().update(EventEntry.CONTENT_URI, mEditContentValues, selection, selectionArgs);
                }
            }
        };

        save_edit_button.setOnClickListener(edit_saveOnClickListener);



        Button edit_entrants_button = (Button) rootView.findViewById(R.id.event_edit_entrant);

        View.OnClickListener edit_entrantsOnClickListener = new View.OnClickListener() {
            public void onClick(View v) {
                ((Callback) getActivity()).onEventEditEntrants(mUri);
            }
        };
        edit_entrants_button.setOnClickListener(edit_entrantsOnClickListener);



        Button edit_users_button = (Button) rootView.findViewById(R.id.event_edit_user);

        View.OnClickListener edit_usersOnClickListener = new View.OnClickListener() {
            public void onClick(View v) {
                ((Callback) getActivity()).onEventEditUsers(mUri);
            }
        };
        edit_users_button.setOnClickListener(edit_usersOnClickListener);


        return rootView;
    }
}