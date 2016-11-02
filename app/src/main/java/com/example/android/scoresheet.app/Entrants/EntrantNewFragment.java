package com.example.android.scoresheet.app.Entrants;

import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.android.scoresheet.app.R;
import com.example.android.scoresheet.app.data.ScoreSheetContract.EntrantEntry;

/**
 * Created by Kari Stromsland on 9/18/2016.
 */
public class EntrantNewFragment extends Fragment {

    public static final String LOG_TAG = EntrantNewFragment.class.getSimpleName();
    static final String ENTRANTNEW_URI = "URI";
    private Uri mUri;
    private Uri newUri;

    private static final String[] ENTRANT_COLUMNS = {
            EntrantEntry.TABLE_NAME + "." + EntrantEntry._ID,
            EntrantEntry.COLUMN_FIRST_NAME,
            EntrantEntry.COLUMN_LAST_NAME,
            EntrantEntry.COLUMN_ID_NUMBER,
            EntrantEntry.COLUMN_DOG_NAME,
            EntrantEntry.COLUMN_DOG_ID_NUMBER,
            EntrantEntry.COLUMN_BREED
    };
    static final int COL_ENTRANT_ID = 0;
    static final int COL_FIRST_NAME = 1;
    static final int COL_LAST_NAME = 2;
    static final int COL_ID = 3;
    static final int COL_DOG_NAME = 4;
    static final int COL_DOG_ID = 5;
    static final int COL_BREED = 6;

    private EditText mFirstNameNewText;
    private EditText mLastNameNewText;
    private EditText mIdNewText;
    private EditText mDogNameNewText;
    private EditText mDogIdNewText;
    private EditText mBreedNewText;


    public EntrantNewFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        Bundle arguments = getArguments();
        if (arguments != null) {
            mUri = arguments.getParcelable(EntrantNewFragment.ENTRANTNEW_URI);
        }

        View rootView = inflater.inflate(R.layout.fragment_new_entrant, container, false);

        mFirstNameNewText = (EditText) rootView.findViewById(R.id.entrant_first_name_text_edit);
        mLastNameNewText = (EditText) rootView.findViewById(R.id.entrant_last_name_text_edit);
        mIdNewText = (EditText) rootView.findViewById(R.id.entrant_id_text_edit);
        mDogNameNewText = (EditText) rootView.findViewById(R.id.entrant_dog_name_text_edit);
        mDogIdNewText = (EditText) rootView.findViewById(R.id.entrant_dog_id_text_edit);
        mBreedNewText = (EditText) rootView.findViewById(R.id.entrant_breed_text_edit);

        Button save_new_button = (Button) rootView.findViewById(R.id.entrantNewSave);

        View.OnClickListener new_saveOnClickListener = new View.OnClickListener() {
            public void onClick(View v) {

                ContentValues mNewContentValues = new ContentValues();

                mNewContentValues.put(EntrantEntry.COLUMN_FIRST_NAME, mFirstNameNewText.getText().toString());
                mNewContentValues.put(EntrantEntry.COLUMN_LAST_NAME, mLastNameNewText.getText().toString());
                mNewContentValues.put(EntrantEntry.COLUMN_ID_NUMBER, mIdNewText.getText().toString());
                mNewContentValues.put(EntrantEntry.COLUMN_DOG_NAME, mDogNameNewText.getText().toString());
                mNewContentValues.put(EntrantEntry.COLUMN_DOG_ID_NUMBER, mDogIdNewText.getText().toString());
                mNewContentValues.put(EntrantEntry.COLUMN_BREED, mBreedNewText.getText().toString());

                newUri = getContext().getContentResolver().insert(EntrantEntry.CONTENT_URI, mNewContentValues);

                Intent intent = new Intent(getContext(), EntrantViewDetailActivity.class).setData(newUri);
                startActivity(intent);
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