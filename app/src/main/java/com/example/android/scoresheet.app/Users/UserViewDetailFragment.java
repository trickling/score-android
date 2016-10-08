package com.example.android.scoresheet.app.Users;

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
            UserEntry.COLUMN_USER_DESC
    };
    static final int COL_USER_ID = 0;
    static final int COL_USER_DESC = 1;

    static final String USERVIEW_URI = "URI";

    private Uri mUri;
    private TextView mDescrViewText;

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

        mDescrViewText = (TextView) rootView.findViewById(R.id.user_text_view);

        mDescrViewText.setText(UserEntry.getUserDescFromUri(mUri));

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