package com.example.android.scoresheet.app.Entrants;

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
import com.example.android.scoresheet.app.data.ScoreSheetContract.EntrantEntry;

/**
 * Created by Kari Stromsland on 9/18/2016.
 */
public class EntrantEditFragment extends Fragment {

    public static final String LOG_TAG = EntrantEditFragment.class.getSimpleName();
    static final String ENTRANTEDIT_URI = "URI";

    private Uri mUri;
    private Uri editUri;

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

    private EditText mFirstNameEditText;
    private EditText mLastNameEditText;
    private EditText mIdEditText;
    private EditText mDogNameEditText;
    private EditText mDogIdEditText;
    private EditText mBreedEditText;


    public EntrantEditFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        Bundle arguments = getArguments();
        if (arguments != null) {
            mUri = arguments.getParcelable(EntrantEditFragment.ENTRANTEDIT_URI);
        }

        View rootView = inflater.inflate(R.layout.fragment_edit_entrant, container, false);

        mFirstNameEditText = (EditText) rootView.findViewById(R.id.entrant_first_name_text_edit);
        mLastNameEditText = (EditText) rootView.findViewById(R.id.entrant_last_name_text_edit);
        mIdEditText = (EditText) rootView.findViewById(R.id.entrant_id_text_edit);
        mDogNameEditText = (EditText) rootView.findViewById(R.id.entrant_dog_name_text_edit);
        mDogIdEditText = (EditText) rootView.findViewById(R.id.entrant_dog_id_text_edit);
        mBreedEditText = (EditText) rootView.findViewById(R.id.entrant_breed_text_edit);


        String sortOrder = EntrantEntry._ID + " ASC";
        String selection = EntrantEntry._ID + " = ?";
        String[] selectionArgs = {Long.valueOf(EntrantEntry.getEntrantIdFromUri(mUri)).toString()};
        String FirstNameText;

        Cursor c = getContext().getContentResolver().query(EntrantEntry.CONTENT_URI, ENTRANT_COLUMNS, selection, selectionArgs, sortOrder);

        if(c.moveToFirst()) {
            mFirstNameEditText.setText(c.getString(COL_FIRST_NAME));
            mLastNameEditText.setText(c.getString(COL_LAST_NAME));
            mIdEditText.setText(c.getString(COL_ID));
            mDogNameEditText.setText(c.getString(COL_DOG_NAME));
            mDogIdEditText.setText(c.getString(COL_DOG_ID));
            mBreedEditText.setText(c.getString(COL_BREED));
        }

        Button save_edit_button = (Button) rootView.findViewById(R.id.entrantEditSave);

        View.OnClickListener edit_saveOnClickListener = new View.OnClickListener() {
            public void onClick(View v) {

                ContentValues mEditContentValues = new ContentValues();

                mEditContentValues.put(EntrantEntry.COLUMN_FIRST_NAME, mFirstNameEditText.getText().toString());
                mEditContentValues.put(EntrantEntry.COLUMN_LAST_NAME, mLastNameEditText.getText().toString());
                mEditContentValues.put(EntrantEntry.COLUMN_ID_NUMBER, mIdEditText.getText().toString());
                mEditContentValues.put(EntrantEntry.COLUMN_DOG_NAME, mDogNameEditText.getText().toString());
                mEditContentValues.put(EntrantEntry.COLUMN_DOG_ID_NUMBER, mDogIdEditText.getText().toString());
                mEditContentValues.put(EntrantEntry.COLUMN_BREED, mBreedEditText.getText().toString());

                String selection = EntrantEntry._ID + " = ?";
                String[] selectionArgs = {Long.valueOf(EntrantEntry.getEntrantIdFromUri(mUri)).toString()};
                getContext().getContentResolver().update(EntrantEntry.CONTENT_URI, mEditContentValues, selection, selectionArgs);

                Intent intent = new Intent(getContext(), EntrantEditActivity.class).setData(mUri);
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