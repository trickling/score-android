package com.example.android.scoresheet.app.Scorecards;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.scoresheet.app.R;

/**
 * Created by Kari Stromsland on 10/21/2016.
 */
public class ScorecardListAdapter extends CursorAdapter{
    public static class ViewHolder {
        public final TextView scorecardElementView;
        public final TextView scorecardSearchAreaView;

        public ViewHolder(View view) {
            scorecardElementView = (TextView) view.findViewById(R.id.scorecard_list_element_text_view);
            scorecardSearchAreaView = (TextView) view.findViewById(R.id.scorecard_list_search_area_text_view);
        }
    }

    public ScorecardListAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {

        View view = LayoutInflater.from(context).inflate(R.layout.list_item_scorecard, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);
        view.setTag(viewHolder);

        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        ViewHolder viewHolder = (ViewHolder) view.getTag();

        String element = cursor.getString(ScorecardListFragment.COL_ELEMENT);
        String search_area = cursor.getString(ScorecardListFragment.COL_SA);

        viewHolder.scorecardElementView.setText(element);
        viewHolder.scorecardSearchAreaView.setText(search_area);
    }
}
