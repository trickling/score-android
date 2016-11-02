package com.example.android.scoresheet.app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import com.example.android.scoresheet.app.data.ScoreSheetContract;
import com.example.android.scoresheet.app.data.ScoreSheetContract.EntrantEntry;
import com.example.android.scoresheet.app.data.ScoreSheetContract.EventEntrantScorecardEntry;
import com.example.android.scoresheet.app.data.ScoreSheetContract.EventEntrantTallyEntry;
import com.example.android.scoresheet.app.data.ScoreSheetContract.EventEntry;
import com.example.android.scoresheet.app.data.ScoreSheetContract.ScorecardEntry;
import com.example.android.scoresheet.app.data.ScoreSheetContract.TallyEntry;

/**
 * Created by Kari Stromsland on 10/25/2016.
 */
public class Utilities {

    public static String[] ELEMENTS = {"Interior", "Exterior", "Containers", "Vehicles", "Elite"};

    private static final String[] EVENT_COLUMNS = {
            ScoreSheetContract.EventEntry.TABLE_NAME + "." + ScoreSheetContract.EventEntry._ID,
            EventEntry.COLUMN_NAME,
            EventEntry.COLUMN_LOCATION,
            EventEntry.COLUMN_DATE,
            EventEntry.COLUMN_HOST,
            EventEntry.COLUMN_STATUS,
            EventEntry.COLUMN_DIVISION,
            EventEntry.COLUMN_INT_SEARCH_AREAS,
            EventEntry.COLUMN_EXT_SEARCH_AREAS,
            EventEntry.COLUMN_CONT_SEARCH_AREAS,
            EventEntry.COLUMN_VEH_SEARCH_AREAS,
            EventEntry.COLUMN_ELITE_SEARCH_AREAS,
            EventEntry.COLUMN_INT_HIDES,
            EventEntry.COLUMN_EXT_HIDES,
            EventEntry.COLUMN_CONT_HIDES,
            EventEntry.COLUMN_VEH_HIDES,
            EventEntry.COLUMN_ELITE_HIDES
    };
    public static final int COL_EVENT_ID = 0;
    public static final int COL_NAME = 1;
    public static final int COL_LOC = 2;
    public static final int COL_DATE = 3;
    public static final int COL_HOST = 4;
    public static final int COL_STATUS = 5;
    public static final int COL_DIV = 6;
    public static final int COL_INT_SA = 7;
    public static final int COL_EXT_SA = 8;
    public static final int COL_CONT_SA = 9;
    public static final int COL_VEH_SA = 10;
    public static final int COL_ELITE_SA = 11;
    public static final int COL_INT_HD = 12;
    public static final int COL_EXT_HD = 13;
    public static final int COL_CONT_HD = 14;
    public static final int COL_VEH_HD = 15;
    public static final int COL_ELITE_HD = 16;

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

    private static final String[] SCORECARD_COLUMNS = {
            ScorecardEntry.TABLE_NAME + "." + ScorecardEntry._ID,
            ScorecardEntry.COLUMN_ELEMENT,
            ScorecardEntry.COLUMN_MAXTIME_M,
            ScorecardEntry.COLUMN_MAXTIME_S,
            ScorecardEntry.COLUMN_FINISH_CALL,
            ScorecardEntry.COLUMN_FALSE_ALERT_FRINGE,
            ScorecardEntry.COLUMN_TIMED_OUT,
            ScorecardEntry.COLUMN_DISMISSED,
            ScorecardEntry.COLUMN_EXCUSED,
            ScorecardEntry.COLUMN_ABSENT,
            ScorecardEntry.COLUMN_ELIMINATED_DURING_SEARCH,
            ScorecardEntry.COLUMN_OTHER_FAULTS_DESCR,
            ScorecardEntry.COLUMN_OTHER_FAULTS_COUNT,
            ScorecardEntry.COLUMN_COMMENTS,
            ScorecardEntry.COLUMN_TOTAL_TIME,
            ScorecardEntry.COLUMN_PRONOUNCED,
            ScorecardEntry.COLUMN_JUDGE_SIGNATURE,
            ScorecardEntry.COLUMN_SEARCH_AREA,
            ScorecardEntry.COLUMN_HIDES_MAX,
            ScorecardEntry.COLUMN_HIDES_FOUND,
            ScorecardEntry.COLUMN_HIDES_MISSED,
            ScorecardEntry.COLUMN_TOTAL_FAULTS,
            ScorecardEntry.COLUMN_MAXPOINT,
            ScorecardEntry.COLUMN_TOTAL_POINTS
    };
    static final int COL_SCORECARD_ID = 0;
    static final int COL_ELEMENT = 1;
    static final int COL_MAXTM = 2;
    static final int COL_MAXTS = 3;
    static final int COL_FINCALL = 4;
    static final int COL_FAF = 5;
    static final int COL_TIMEOUT = 6;
    static final int COL_DISMISSED = 7;
    static final int COL_EXCUSED = 8;
    static final int COL_ABSENT = 9;
    static final int COL_EDS = 10;
    static final int COL_OFD = 11;
    static final int COL_OFC = 12;
    static final int COL_COMMENTS = 13;
    static final int COL_TOTALT = 14;
    static final int COL_PRON = 15;
    static final int COL_JS = 16;
    static final int COL_SA = 17;
    static final int COL_HDMAX = 18;
    static final int COL_HDFOUND = 19;
    static final int COL_HDMISSED = 20;
    static final int COL_TOTALFLTS = 21;
    static final int COL_MAXPT = 22;
    static final int COL_TOTALPTS = 23;

    private static final String[] TALLY_COLUMNS = {
            TallyEntry.TABLE_NAME + "." + TallyEntry._ID,
            TallyEntry.COLUMN_TOTAL_TIME,
            TallyEntry.COLUMN_TOTAL_FAULTS,
            TallyEntry.COLUMN_TOTAL_POINTS,
            TallyEntry.COLUMN_TITLE,
            TallyEntry.COLUMN_QUALIFYING_SCORE,
            TallyEntry.COLUMN_QUALIFYING_SCORES
    };
    public static final int COL_TALLY_ID = 0;
    public static final int COL_TOTAL_TIME = 1;
    public static final int COL_TOTAL_FAULTS = 2;
    public static final int COL_TOTAL_POINTS = 3;
    public static final int COL_TITLE = 4;
    public static final int COL_QSCORE = 5;
    public static final int COL_QSCORES = 6;

    private static final String[] EVENTENTRANTSCORECARD_COLUMNS = {
            EventEntrantScorecardEntry.TABLE_NAME + "." + EventEntrantScorecardEntry._ID,
            EventEntrantScorecardEntry.COLUMN_EVENT_ID, EventEntrantScorecardEntry.COLUMN_ENTRANT_ID, EventEntrantScorecardEntry.COLUMN_SCORECARD_ID
    };
    public static final int COL_EVENTENTRANTSCORECARD_ID = 0;
    public static final int COL_EVSC_ID = 1;
    public static final int COL_ENSC_ID = 2;
    public static final int COL_SC_ID = 3;

    private static final String[] EVENTENTRANTTALLY_COLUMNS = {
            EventEntrantTallyEntry.TABLE_NAME + "." + EventEntrantTallyEntry._ID,
            EventEntrantTallyEntry.COLUMN_EVENT_ID, EventEntrantTallyEntry.COLUMN_ENTRANT_ID, EventEntrantTallyEntry.COLUMN_TALLY_ID
    };
    public static final int COL_EVENTENTRANTTALLY_ID = 0;
    public static final int COL_EVTLY_ID = 1;
    public static final int COL_ENTLY_ID = 2;
    public static final int COL_TLY_ID = 3;





    public static String[] scorecardCompletion(Context context, String eventid) {

        int numEntrants;

        String EvEnTlySortOrder = EventEntrantTallyEntry._ID + " ASC";
        String EvEnTlySelection = EventEntrantTallyEntry.COLUMN_EVENT_ID + " = ?";
        String[] EvEnTlySelectionArgs = {eventid};

        Cursor cEvEnTly = context.getContentResolver().query(EventEntrantTallyEntry.CONTENT_URI, EVENTENTRANTTALLY_COLUMNS, EvEnTlySelection, EvEnTlySelectionArgs, EvEnTlySortOrder);

        if (cEvEnTly.moveToFirst()) {
            numEntrants = cEvEnTly.getCount();
        } else {
            numEntrants = 0;
        }

        String[] completed_entrant_scorecards = new String[numEntrants];

        String EvSortOrder = EventEntry._ID + " ASC";
        String EvSelection = EventEntry._ID + " = ?";
        String[] EvSelectionArgs = {eventid};

        Cursor cEv = context.getContentResolver().query(EventEntry.CONTENT_URI, EVENT_COLUMNS, EvSelection, EvSelectionArgs, EvSortOrder);

        int cont_count = 0;
        int int_count = 0;
        int ext_count = 0;
        int veh_count = 0;
        int elite_count = 0;
        int sc_count = 0;

        for (int i = 0; i < completed_entrant_scorecards.length; i++) {
            completed_entrant_scorecards[i] = "inc";
        }
        if (cEvEnTly.moveToFirst()) {
            String entrantid = cEvEnTly.getString(COL_ENTLY_ID);
            String EvEnScdSortOrder = EventEntrantScorecardEntry._ID + " ASC";
            String EvEnScdSelection = EventEntrantScorecardEntry.COLUMN_EVENT_ID + " = ? AND " + EventEntrantScorecardEntry.COLUMN_ENTRANT_ID + " = ?";
            String[] EvEnScdSelectionArgs = {eventid, entrantid};
            Cursor cEvEnScd = context.getContentResolver().query(EventEntrantScorecardEntry.CONTENT_URI, EVENTENTRANTSCORECARD_COLUMNS, EvEnScdSelection, EvEnScdSelectionArgs, EvEnScdSortOrder);
            if (cEv.moveToFirst()) {
                for (int i = 0; i < completed_entrant_scorecards.length; i++) {
                    sc_count = 0;
                    for (int m = 0; m < ELEMENTS.length; m++) {
                        switch (ELEMENTS[m]) {
                            case "Containers":
                                if (cEv.getString(COL_CONT_SA) != "") {
                                    cont_count = Integer.decode(cEv.getString(COL_CONT_SA));
                                } else {
                                    cont_count = 0;
                                }
                                break;
                            case "Exterior":
                                if (cEv.getString(COL_EXT_SA) != "") {
                                    ext_count = Integer.decode(cEv.getString(COL_EXT_SA));
                                } else {
                                    ext_count = 0;
                                }
                                break;
                            case "Interior":
                                if (cEv.getString(COL_INT_SA) != "") {
                                    int_count = Integer.decode(cEv.getString(COL_INT_SA));
                                } else {
                                    int_count = 0;
                                }
                                break;
                            case "Vehicles":
                                if (cEv.getString(COL_VEH_SA) != "") {
                                    veh_count = Integer.decode(cEv.getString(COL_VEH_SA));
                                } else {
                                    veh_count = 0;
                                }
                                break;
                            case "Elite":
                                if (cEv.getString(COL_ELITE_SA) != "") {
                                    elite_count = Integer.decode(cEv.getString(COL_ELITE_SA));
                                } else {
                                    elite_count = 0;
                                }
                                break;
                        }
                        if (cEvEnScd.moveToFirst()) {
                            do {
                                long scorecardid = cEvEnScd.getLong(COL_SC_ID);
                                String ScdSortOrder = ScorecardEntry._ID + " ASC";
                                String ScdSelection = ScorecardEntry._ID + " = ?";
                                String[] ScdSelectionArgs = {Long.valueOf(scorecardid).toString()};
                                Cursor cScd = context.getContentResolver().query(ScorecardEntry.CONTENT_URI, SCORECARD_COLUMNS, ScdSelection, ScdSelectionArgs, ScdSortOrder);
                                if (cScd.moveToFirst()) {
                                    if (cScd.getString(COL_ELEMENT).equals(ELEMENTS[m])) {
                                        if (cScd.getString(COL_JS).equals(true)) {
                                            sc_count += 1;
                                        }
                                    }
                                }
                                cEvEnScd.moveToNext();
                            } while (!cEvEnScd.isAfterLast());
                            if (sc_count == cont_count + ext_count + int_count + veh_count + elite_count) {
                                if (cEvEnScd.moveToPosition(i)) {
                                    completed_entrant_scorecards[i] = cEvEnScd.getString(COL_ENSC_ID);
                                }
                            }
                        }
                    }
                }
            }
        }
        return completed_entrant_scorecards;
    }




    public static String[] tallyCompletion(Context context, String eventid){

        int numEntrants;

        String EvEnTlySortOrder = EventEntrantTallyEntry._ID + " ASC";
        String EvEnTlySelection = EventEntrantTallyEntry.COLUMN_EVENT_ID + " = ?";
        String[] EvEnTlySelectionArgs = {eventid};

        Cursor cEvEnTly = context.getContentResolver().query(EventEntrantTallyEntry.CONTENT_URI, EVENTENTRANTTALLY_COLUMNS, EvEnTlySelection, EvEnTlySelectionArgs, EvEnTlySortOrder);

        if (cEvEnTly.moveToFirst()) {
            numEntrants = cEvEnTly.getCount();
        } else {
            numEntrants = 0;
        }

        String[] completed_entrant_tallies = new String[numEntrants];

        for (int i=0; i<completed_entrant_tallies.length; i++){
            completed_entrant_tallies[i] = "inc";
        }
        int i = 0;
        if(cEvEnTly.moveToFirst()){
            do{

                String TlySortOrder = TallyEntry._ID + " ASC";
                String TlySelection = TallyEntry._ID + " = ?";
                String[] TlySelectionArgs = {cEvEnTly.getString(COL_TLY_ID)};

                Cursor cTly = context.getContentResolver().query(TallyEntry.CONTENT_URI, TALLY_COLUMNS, TlySelection, TlySelectionArgs, TlySortOrder);

                if (cTly.getString(COL_TOTAL_TIME) != "0"){
                    completed_entrant_tallies[i] = cEvEnTly.getString(COL_ENTLY_ID);
                    i+=1;
                }
                cEvEnTly.moveToNext();
            }while (!cEvEnTly.isAfterLast());
        }
        return completed_entrant_tallies;
    }




    public static void updateScorecards(Context context, String eventid, String searchareas, String element) {

        String scorecardid;
        String newscorecardid;
        String entrantid;
        Integer newSearchAreaCount;

        String EvSortOrder = EventEntry._ID + " ASC";
        String EvSelection = EventEntry._ID + " = ?";
        String[] EvSelectionArgs = {eventid};

        Cursor cEv = context.getContentResolver().query(EventEntry.CONTENT_URI, EVENT_COLUMNS, EvSelection, EvSelectionArgs, EvSortOrder);

        newSearchAreaCount = Integer.decode(searchareas);

        String EvEnTlySortOrder = EventEntrantTallyEntry._ID + " ASC";
        String EvEnTlySelection = EventEntrantTallyEntry.COLUMN_EVENT_ID + " = ?";
        String[] EvEnTlySelectionArgs = {eventid};
        Cursor cEvEnTly = context.getContentResolver().query(EventEntrantTallyEntry.CONTENT_URI, EVENTENTRANTTALLY_COLUMNS, EvEnTlySelection, EvEnTlySelectionArgs, EvEnTlySortOrder);

        if (cEvEnTly.moveToFirst()) {
            do {

                entrantid = cEvEnTly.getString(COL_ENTLY_ID);

                String EvEnScdSortOrder = EventEntrantScorecardEntry._ID + " ASC";
                String EvEnScdSelection = EventEntrantScorecardEntry.COLUMN_EVENT_ID + " = ? AND " + EventEntrantScorecardEntry.COLUMN_ENTRANT_ID + " = ?";
                String[] EvEnScdSelectionArgs = {eventid, entrantid};
                Cursor cEvEnScd = context.getContentResolver().query(EventEntrantScorecardEntry.CONTENT_URI, EVENTENTRANTSCORECARD_COLUMNS, EvEnScdSelection, EvEnScdSelectionArgs, EvEnScdSortOrder);

                if (cEvEnScd.moveToFirst()) {
                    do {
                        scorecardid = cEvEnScd.getString(COL_SC_ID);
                        int count = 0;
                        String ScdSortOrder = ScorecardEntry._ID + " DESC";
                        String ScdSelection = ScorecardEntry._ID + " = ? AND " + ScorecardEntry.COLUMN_ELEMENT + " = ?";
                        String[] ScdSelectionArgs = {scorecardid, element};
                        Cursor cScd = context.getContentResolver().query(ScorecardEntry.CONTENT_URI, SCORECARD_COLUMNS, ScdSelection, ScdSelectionArgs, ScdSortOrder);

                        if (cScd.moveToFirst()) {
                            Integer oldSearchAreaCount = 0;
                            if (cEv.moveToFirst()) {
                                switch (element) {
                                    case "Containers":
                                        oldSearchAreaCount = Integer.decode(cEv.getString(COL_CONT_SA));
                                        break;
                                    case "Interior":
                                        oldSearchAreaCount = Integer.decode(cEv.getString(COL_INT_SA));
                                        break;
                                    case "Exterior":
                                        oldSearchAreaCount = Integer.decode(cEv.getString(COL_EXT_SA));
                                        break;
                                    case "Vehicles":
                                        oldSearchAreaCount = Integer.decode(cEv.getString(COL_VEH_SA));
                                        break;
                                    case "Elite":
                                        oldSearchAreaCount = Integer.decode(cEv.getString(COL_ELITE_SA));
                                        break;
                                }

                                if (oldSearchAreaCount < newSearchAreaCount) {
                                    for (int i = 0; i < (newSearchAreaCount - oldSearchAreaCount); i++) {
                                        // add scorecards until = new SearchAreaCount
                                        ContentValues mSCContentValues = new ContentValues();
                                        mSCContentValues.put(ScorecardEntry.COLUMN_SEARCH_AREA, oldSearchAreaCount + i + 1);
                                        mSCContentValues.put(ScorecardEntry.COLUMN_ELEMENT, element);
                                        Uri scUri = context.getContentResolver().insert(ScorecardEntry.buildScorecardUri(), mSCContentValues);
                                        // update join table
                                        newscorecardid = Long.valueOf(ScorecardEntry.getScorecardIdFromUri(scUri)).toString();
                                        ContentValues mEvEnScdContentValues = new ContentValues();
                                        mEvEnScdContentValues.put(EventEntrantScorecardEntry.COLUMN_ENTRANT_ID, entrantid);
                                        mEvEnScdContentValues.put(EventEntrantScorecardEntry.COLUMN_EVENT_ID, eventid);
                                        mEvEnScdContentValues.put(EventEntrantScorecardEntry.COLUMN_SCORECARD_ID, newscorecardid);

                                        context.getContentResolver().insert(EventEntrantScorecardEntry.buildEventEntrantScorecardUri(), mEvEnScdContentValues);
                                    }
                                } else if (oldSearchAreaCount > newSearchAreaCount) {
                                    for (int i = 0; i < (oldSearchAreaCount - newSearchAreaCount - count); i++) {
                                        // delete scorecards until = new SearchAreaCount
                                        if (Integer.decode(cScd.getString(COL_SA)) == oldSearchAreaCount - i) {
                                            String scSelection = ScorecardEntry._ID + " = ?";
                                            String[] scSelectionArgs = {scorecardid};
                                            context.getContentResolver().delete(ScorecardEntry.CONTENT_URI, scSelection, scSelectionArgs);
                                            // update join table
                                            String dSelection = EventEntrantScorecardEntry.COLUMN_SCORECARD_ID + " = ?";
                                            String[] dSelectionArgs = {scorecardid};
                                            context.getContentResolver().delete(EventEntrantScorecardEntry.CONTENT_URI, dSelection, dSelectionArgs);
                                            count++;
                                        }
                                    }
                                }
                            }
                        }
                        cEvEnScd.moveToNext();
                    }while (!cEvEnScd.isAfterLast());
                }
                cEvEnTly.moveToNext();
            }while(!cEvEnTly.isAfterLast());
        }
    }




    public static String[] placeOrder(Context context, String eventid){

        long place_points = 0;
        long place_faults = 0;
        int place_time = 0;
        int fcount = 0;
        int tcount = 0;
        int numEntrants = 0;
        Boolean found = false;

        String EvEnTlySortOrder = EventEntrantTallyEntry._ID + " ASC";
        String EvEnTlySelection = EventEntrantTallyEntry.COLUMN_EVENT_ID + " = ?";
        String[] EvEnTlySelectionArgs = {eventid};

        Cursor cEvEnTly = context.getContentResolver().query(EventEntrantTallyEntry.CONTENT_URI, EVENTENTRANTTALLY_COLUMNS, EvEnTlySelection, EvEnTlySelectionArgs, EvEnTlySortOrder);

        if (cEvEnTly.moveToFirst()) {
            fcount = cEvEnTly.getCount();
        } else {
            fcount = 0;
        }

        String[] placing = new String[fcount];
        for (int i=0; i<placing.length; i++){
            placing[i] = "0";
        }

        for (int i=0; i<placing.length; i++){
            found = false;
            place_points = 0;
            place_faults = 0;
            place_time = 0;
            long total_points = 0;
            double total_points_dbl = 0.0;
            long total_faults = 0;
            double total_faults_dbl = 0.0;
            int tmp_time = 0;
            if(cEvEnTly.moveToFirst()){
                do{
                    String tlySortOrder = TallyEntry._ID + " ASC";
                    String tlySelection = TallyEntry._ID + " = ?";
                    String[] tlySelectionArgs = {cEvEnTly.getString(COL_TLY_ID)};

                    Cursor cTly = context.getContentResolver().query(TallyEntry.CONTENT_URI, TALLY_COLUMNS, tlySelection, tlySelectionArgs, tlySortOrder);
                    for (int k=0; k<placing.length; k++){
                        if (placing[k] == Long.valueOf(cEvEnTly.getLong(COL_TLY_ID)).toString()){
                            found = true;
                        }
                    }
                    if (found){
                        found = false;
                        cEvEnTly.moveToNext();
                        continue;
                    }
                    if(cTly.moveToFirst()) {
                        tmp_time = str_to_time(cTly.getString(COL_TOTAL_TIME));
                        if (tmp_time != 0) {
                            total_points_dbl = Double.parseDouble(cTly.getString(COL_TOTAL_POINTS));
                            total_points = Math.round(total_points_dbl);
                        } else {
                            total_points = 0;
                        }
                        if (tmp_time != 0) {
                            total_faults_dbl = Double.parseDouble(cTly.getString(COL_TOTAL_FAULTS));
                            total_faults = Math.round(total_faults_dbl);
                        } else {
                            total_faults = 0;
                        }
                        if (place_points < total_points) {
                            place_time = str_to_time(cTly.getString(COL_TOTAL_TIME));
                            place_points = total_points;
                            place_faults = total_faults;
                        }
                        if ((place_points <= total_points) && (place_time >= str_to_time(cTly.getString(COL_TOTAL_TIME))) && (place_faults >= total_faults)) {
                            place_time = str_to_time(cTly.getString(COL_TOTAL_TIME));
                            place_points = total_points;
                            place_faults = total_faults;
                        }
                    }
                    cEvEnTly.moveToNext();
                }while (!cEvEnTly.isAfterLast());
            }
            if(cEvEnTly.moveToFirst()){
                do{
                    String tlySortOrder = TallyEntry._ID + " ASC";
                    String tlySelection = TallyEntry._ID + " = ?";
                    String[] tlySelectionArgs = {cEvEnTly.getString(COL_TLY_ID)};

                    Cursor cTly = context.getContentResolver().query(TallyEntry.CONTENT_URI, TALLY_COLUMNS, tlySelection, tlySelectionArgs, tlySortOrder);

                    for (int k=0; k<placing.length; k++){
                        if (placing[k] == Long.valueOf(cEvEnTly.getLong(COL_TLY_ID)).toString()){
                            found = true;
                        }
                    }
                    if (found){
                        found = false;
                        cEvEnTly.moveToNext();
                        continue;
                    }
                    if(cTly.moveToFirst()) {
                        tmp_time = str_to_time(cTly.getString(COL_TOTAL_TIME));
                        if (tmp_time != 0) {
                            total_points_dbl = Double.parseDouble(cTly.getString(COL_TOTAL_POINTS));
                            total_points = Math.round(total_points_dbl);
                        } else {
                            total_points = 0;
                        }
                        if (tmp_time != 0) {
                            total_faults_dbl = Double.parseDouble(cTly.getString(COL_TOTAL_FAULTS));
                            total_faults = Math.round(total_faults_dbl);
                        } else {
                            total_faults = 0;
                        }
                        if ((total_points == place_points) && (str_to_time(cTly.getString(COL_TOTAL_TIME)) == place_time) && (total_faults == place_faults)) {
                            placing[tcount] = Long.valueOf(cEvEnTly.getLong(COL_TLY_ID)).toString();
                            tcount += 1;
                            break;
                        }
                    }
                    cEvEnTly.moveToNext();
                }while (!cEvEnTly.isAfterLast());
            }
        }
        return placing;
    }




    public static String getElmSearchAreas(Context context, String scorecardid){

        String search_areas = "";
        String eventid;
        String element;

        String EvEnScdSortOrder = EventEntrantScorecardEntry._ID + " ASC";
        String EvEnScdSelection = EventEntrantScorecardEntry.COLUMN_SCORECARD_ID + " = ?";
        String[] EvEnScdSelectionArgs = {scorecardid};
        Cursor cEvEnScd = context.getContentResolver().query(EventEntrantScorecardEntry.CONTENT_URI, EVENTENTRANTSCORECARD_COLUMNS, EvEnScdSelection, EvEnScdSelectionArgs, EvEnScdSortOrder);

        if(cEvEnScd.moveToFirst()){
            eventid = Long.valueOf(cEvEnScd.getLong(COL_EVSC_ID)).toString();
        }else{
            eventid = "";
        }

        String EvSortOrder = EventEntry._ID + " ASC";
        String EvSelection = EventEntry._ID + " = ?";
        String[] EvSelectionArgs = {eventid};

        Cursor cEv = context.getContentResolver().query(EventEntry.CONTENT_URI, EVENT_COLUMNS, EvSelection, EvSelectionArgs, EvSortOrder);

        String ScdSortOrder = ScorecardEntry._ID + " ASC";
        String ScdSelection = ScorecardEntry._ID + " = ?";
        String[] ScdSelectionArgs = {scorecardid};

        Cursor cScd = context.getContentResolver().query(ScorecardEntry.CONTENT_URI, SCORECARD_COLUMNS, ScdSelection, ScdSelectionArgs, ScdSortOrder);

        if(cScd.moveToFirst()){
            element = cScd.getString(COL_ELEMENT);
        }else{
            element = "";
        }

        if(cEv.moveToFirst()){
            switch (element){
                case "Containers":
                    search_areas = cEv.getString(COL_CONT_SA);
                    break;
                case "Interior":
                    search_areas = cEv.getString(COL_INT_SA);
                    break;
                case "Exterior":
                    search_areas = cEv.getString(COL_EXT_SA);
                    break;
                case "Vehicles":
                    search_areas = cEv.getString(COL_VEH_SA);
                    break;
                case "Elite":
                    search_areas = cEv.getString(COL_ELITE_SA);
                    break;
            }
        }
        return search_areas;
    }




    public static String getElmHides(Context context, String scorecardid){

        String hides = "";
        String eventid;
        String element;

        String EvEnScdSortOrder = EventEntrantScorecardEntry._ID + " ASC";
        String EvEnScdSelection = EventEntrantScorecardEntry.COLUMN_SCORECARD_ID + " = ?";
        String[] EvEnScdSelectionArgs = {scorecardid};
        Cursor cEvEnScd = context.getContentResolver().query(EventEntrantScorecardEntry.CONTENT_URI, EVENTENTRANTSCORECARD_COLUMNS, EvEnScdSelection, EvEnScdSelectionArgs, EvEnScdSortOrder);

        if(cEvEnScd.moveToFirst()){
            eventid = Long.valueOf(cEvEnScd.getLong(COL_EVSC_ID)).toString();
        }else{
            eventid = "";
        }

        String EvSortOrder = EventEntry._ID + " ASC";
        String EvSelection = EventEntry._ID + " = ?";
        String[] EvSelectionArgs = {eventid};

        Cursor cEv = context.getContentResolver().query(EventEntry.CONTENT_URI, EVENT_COLUMNS, EvSelection, EvSelectionArgs, EvSortOrder);

        String ScdSortOrder = ScorecardEntry._ID + " ASC";
        String ScdSelection = ScorecardEntry._ID + " = ?";
        String[] ScdSelectionArgs = {scorecardid};

        Cursor cScd = context.getContentResolver().query(ScorecardEntry.CONTENT_URI, SCORECARD_COLUMNS, ScdSelection, ScdSelectionArgs, ScdSortOrder);

        if(cScd.moveToFirst()){
            element = cScd.getString(COL_ELEMENT);
        }else{
            element = "";
        }

        if(cEv.moveToFirst()){
            switch (element){
                case "Containers":
                    hides = cEv.getString(COL_CONT_HD);
                    break;
                case "Interior":
                    hides = cEv.getString(COL_INT_HD);
                    break;
                case "Exterior":
                    hides = cEv.getString(COL_EXT_HD);
                    break;
                case "Vehicles":
                    hides = cEv.getString(COL_VEH_HD);
                    break;
                case "Elite":
                    hides = cEv.getString(COL_ELITE_HD);
                    break;
            }
        }
        return hides;
    }




    public static String getCheckHideCount(Context context, String scorecardid){

        String message = "";
        String eventid = "";
        String entrantid = "";

        String hideCountCheckStr = getElmHides(context,scorecardid);
        int hideCountCheck = Integer.decode(hideCountCheckStr);
        int elm_hides = hideCountCheck;

        String EvEnScdSortOrder = EventEntrantScorecardEntry._ID + " ASC";
        String EvEnScdSelection = EventEntrantScorecardEntry.COLUMN_SCORECARD_ID + " = ?";
        String[] EvEnScdSelectionArgs = {scorecardid};
        Cursor cEvEnScd = context.getContentResolver().query(EventEntrantScorecardEntry.CONTENT_URI, EVENTENTRANTSCORECARD_COLUMNS, EvEnScdSelection, EvEnScdSelectionArgs, EvEnScdSortOrder);

        if(cEvEnScd.moveToFirst()) {
            eventid = cEvEnScd.getString(COL_EVSC_ID);
            entrantid = cEvEnScd.getString(COL_ENSC_ID);
        }else {
            eventid = "";
        }
        String EvSortOrder = EventEntry._ID + " ASC";
        String EvSelection = EventEntry._ID + " = ?";
        String[] EvSelectionArgs = {eventid};

        Cursor cEv = context.getContentResolver().query(EventEntry.CONTENT_URI, EVENT_COLUMNS, EvSelection, EvSelectionArgs, EvSortOrder);

        String ScdSortOrder = ScorecardEntry._ID + " ASC";
        String ScdSelection = ScorecardEntry._ID + " = ?";
        String[] ScdSelectionArgs = {scorecardid};
        Cursor cScd = context.getContentResolver().query(ScorecardEntry.CONTENT_URI, SCORECARD_COLUMNS, ScdSelection, ScdSelectionArgs, ScdSortOrder);

        String EvEnSortOrder = EventEntrantScorecardEntry._ID + " ASC";
        String EvEnSelection = EventEntrantScorecardEntry.COLUMN_ENTRANT_ID + " = ? AND " + EventEntrantScorecardEntry.COLUMN_EVENT_ID + " = ?";
        String[] EvEnSelectionArgs = {entrantid, eventid};
        Cursor cEvEn = context.getContentResolver().query(EventEntrantScorecardEntry.CONTENT_URI, EVENTENTRANTSCORECARD_COLUMNS, EvEnSelection, EvEnSelectionArgs, EvEnSortOrder);

        if(cEvEn.moveToFirst()) {
            do {
                String scorecardids = cEvEn.getString(COL_SC_ID);
                String[] ScdsSelectionArgs = {scorecardids};
                Cursor cScds = context.getContentResolver().query(ScorecardEntry.CONTENT_URI, SCORECARD_COLUMNS, ScdSelection, ScdsSelectionArgs, ScdSortOrder);
                if(cScds.moveToFirst()) {
                    String hidesMaxStr = cScds.getString(COL_HDMAX);
                    int hidesMax = Integer.decode(hidesMaxStr);
                    hideCountCheck = hideCountCheck - hidesMax;
                }
                cEvEn.moveToNext();
            } while (cEvEn.isAfterLast());
        }
        cEvEn.moveToNext();

        message = "";
        if(cEv.moveToFirst() && cScd.moveToFirst()) {
            if (((hideCountCheck > elm_hides) || (hideCountCheck < 0)) && (!cEv.getString(COL_DIV).equals("NW1"))) {
                message = "Incorrect Hide Count...";
            }
            if (cEv.getString(COL_DIV).equals("NW1")) {
                if (!cScd.getString(COL_HDMAX).equals("1")) {
                    message = "Incorrect Hide Count...";
                }
            }
            if (cEv.getString(COL_DIV).equals("NW2")) {
                String hidesmaxStr = cScd.getString(COL_HDMAX);
                int hidesmax = Integer.decode(hidesmaxStr);
                String search_areas_str = getElmSearchAreas(context, scorecardid);
                int search_areas = Integer.decode(search_areas_str);
                if (cScd.getString(COL_HDMAX).equals("0") || hidesmax >= elm_hides && (search_areas > 1)) {
                    message = "Incorrect Hide Count...";
                }
                if ((cScd.getString(COL_HDMAX).equals("1")) && (elm_hides == 1) && (search_areas == 1)) {
                    message = "";
                }
            }
        }
        return message;
    }




    public static String getMaxPoint(Context context, String scorecardid){

        String eventid = "";
        String entrantid = "";
        String hidesMaxStr = "";
        String pointStr = "";

        int hidesMax = 0;
        double point = 0.0;
        String elementHidesStr = "";
        int elementHides = 0;

        String EvEnScdSortOrder = EventEntrantScorecardEntry._ID + " ASC";
        String EvEnScdSelection = EventEntrantScorecardEntry.COLUMN_SCORECARD_ID + " = ?";
        String[] EvEnScdSelectionArgs = {scorecardid};
        Cursor cEvEnScd = context.getContentResolver().query(EventEntrantScorecardEntry.CONTENT_URI, EVENTENTRANTSCORECARD_COLUMNS, EvEnScdSelection, EvEnScdSelectionArgs, EvEnScdSortOrder);

        if(cEvEnScd.moveToFirst()) {
            eventid = cEvEnScd.getString(COL_EVSC_ID);
        }else {
            eventid = "";
        }

        String EvSortOrder = EventEntry._ID + " ASC";
        String EvSelection = EventEntry._ID + " = ?";
        String[] EvSelectionArgs = {eventid};

        Cursor cEv = context.getContentResolver().query(EventEntry.CONTENT_URI, EVENT_COLUMNS, EvSelection, EvSelectionArgs, EvSortOrder);

        String ScdSortOrder = ScorecardEntry._ID + " ASC";
        String ScdSelection = ScorecardEntry._ID + " = ?";
        String[] ScdSelectionArgs = {scorecardid};
        Cursor cScd = context.getContentResolver().query(ScorecardEntry.CONTENT_URI, SCORECARD_COLUMNS, ScdSelection, ScdSelectionArgs, ScdSortOrder);

        if(cScd.moveToFirst()) {
            hidesMaxStr = cScd.getString(COL_HDMAX);
            hidesMax = Integer.decode(hidesMaxStr);
        }

        if(cEv.moveToFirst() && cScd.moveToFirst()) {
//            if (cScd.getString(COL_MAXPT) != "0") {
            if (!cEv.getString(COL_DIV).equals("NW1")) {
                switch (cScd.getString(COL_ELEMENT)) {
                    case "Containers":
                        elementHidesStr = cEv.getString(COL_CONT_HD);
                        elementHides = Integer.decode(elementHidesStr);
                        if (hidesMax > 0) {
                            if (!cEv.getString(COL_DIV).equals("Element Specialty")) {
                                point = (25.00 / elementHides) * hidesMax;
                            } else if (cEv.getString(COL_DIV).equals("Element Specialty")) {
                                point = (100.00 / elementHides) * hidesMax;
                            } else {
                                point = 0;
                            }
                        }
                        break;
                    case "Interior":
                        elementHidesStr = cEv.getString(COL_INT_HD);
                        elementHides = Integer.decode(elementHidesStr);
                        if (hidesMax > 0) {
                            if (!cEv.getString(COL_DIV).equals("Element Specialty")) {
                                point = (25.00 / elementHides) * hidesMax;
                            } else if (cEv.getString(COL_DIV).equals("Element Specialty")) {
                                point = (100.00 / elementHides) * hidesMax;
                            } else {
                                point = 0;
                            }
                        }
                        break;
                    case "Exterior":
                        elementHidesStr = cEv.getString(COL_EXT_HD);
                        elementHides = Integer.decode(elementHidesStr);
                        if (hidesMax > 0) {
                            if (!cEv.getString(COL_DIV).equals("Element Specialty")) {
                                point = (25.00 / elementHides) * hidesMax;
                            } else if (cEv.getString(COL_DIV).equals("Element Specialty")) {
                                point = (100.00 / elementHides) * hidesMax;
                            } else {
                                point = 0;
                            }
                        }
                        break;
                    case "Vehicles":
                        elementHidesStr = cEv.getString(COL_VEH_HD);
                        elementHides = Integer.decode(elementHidesStr);
                        if (hidesMax > 0) {
                            if (!cEv.getString(COL_DIV).equals("Element Specialty")) {
                                point = (25.00 / elementHides) * hidesMax;
                            } else if (cEv.getString(COL_DIV).equals("Element Specialty")) {
                                point = (100.00 / elementHides) * hidesMax;
                            } else {
                                point = 0;
                            }
                        }
                        break;
                    case "Elite":
                        elementHidesStr = cEv.getString(COL_ELITE_HD);
                        elementHides = Integer.decode(elementHidesStr);
                        if (hidesMax > 0) {
                            point = (100.00 / elementHides) * hidesMax;
                        } else {
                            point = 0;
                        }
                        break;

                }
            } else {
                point = 25.0;
            }
//            }
            pointStr = String.format("%1.1f", point);
        }
        return pointStr;
    }




    public static String getFaultTotal(Context context, String scorecardid){

        int totalfaults = 0;
        String totalFaultsStr = "";
        String totalfaultsStrg = "";
        int falseAlertFringe = 0;
        String falseAlertFringeStr = "";

        String eventid = "";

        String EvEnScdSortOrder = EventEntrantScorecardEntry._ID + " ASC";
        String EvEnScdSelection = EventEntrantScorecardEntry.COLUMN_SCORECARD_ID + " = ?";
        String[] EvEnScdSelectionArgs = {scorecardid};
        Cursor cEvEnScd = context.getContentResolver().query(EventEntrantScorecardEntry.CONTENT_URI, EVENTENTRANTSCORECARD_COLUMNS, EvEnScdSelection, EvEnScdSelectionArgs, EvEnScdSortOrder);

        if(cEvEnScd.moveToFirst()) {
            eventid = cEvEnScd.getString(COL_EVSC_ID);
        }else {
            eventid = "";
        }

        String EvSortOrder = EventEntry._ID + " ASC";
        String EvSelection = EventEntry._ID + " = ?";
        String[] EvSelectionArgs = {eventid};

        Cursor cEv = context.getContentResolver().query(EventEntry.CONTENT_URI, EVENT_COLUMNS, EvSelection, EvSelectionArgs, EvSortOrder);

        String ScdSortOrder = ScorecardEntry._ID + " ASC";
        String ScdSelection = ScorecardEntry._ID + " = ?";
        String[] ScdSelectionArgs = {scorecardid};
        Cursor cScd = context.getContentResolver().query(ScorecardEntry.CONTENT_URI, SCORECARD_COLUMNS, ScdSelection, ScdSelectionArgs, ScdSortOrder);

        if(cScd.moveToFirst() && cEv.moveToFirst()) {

            if (cScd.getString(COL_OFC).equals("")) {
                totalfaults = 0;
            } else {
                totalfaultsStrg = cScd.getString(COL_OFC);
                totalfaults = Integer.decode(totalfaultsStrg);
            }
            if (cScd.getString(COL_FAF).equals("")) {
                falseAlertFringe = 0;
            } else {
                falseAlertFringeStr = cScd.getString(COL_FAF);
                falseAlertFringe = Integer.decode(falseAlertFringeStr);
            }
            if (falseAlertFringe > 0) {
                if (!cEv.getString(COL_DIV).equals("Elite")) {
                    totalfaults += 2;
                }
            }
            if (cScd.getString(COL_EDS).equals("yes") || cScd.getString(COL_EXCUSED).equals("yes")) {
                if (!cEv.getString(COL_DIV).equals("Elite")) {
                    totalfaults += 3;
                } else {
                    totalfaults += 1;
                }
            }
            if ((cScd.getString(COL_ABSENT).equals("yes")) && (!cEv.getString(COL_DIV).equals("Elite"))) {
                totalfaults += 4;
            }
            totalFaultsStr = Integer.toString(totalfaults);
        }
        return totalFaultsStr;
    }




    public static String getTime(Context context, String scorecardid){

        String eventid = "";
        int falseAlertFringe = 0;
        String falseAlertFringeStr = "";
        String elapsed_time = "";

        String EvEnScdSortOrder = EventEntrantScorecardEntry._ID + " ASC";
        String EvEnScdSelection = EventEntrantScorecardEntry.COLUMN_SCORECARD_ID + " = ?";
        String[] EvEnScdSelectionArgs = {scorecardid};
        Cursor cEvEnScd = context.getContentResolver().query(EventEntrantScorecardEntry.CONTENT_URI, EVENTENTRANTSCORECARD_COLUMNS, EvEnScdSelection, EvEnScdSelectionArgs, EvEnScdSortOrder);

        if(cEvEnScd.moveToFirst()) {
            eventid = cEvEnScd.getString(COL_EVSC_ID);
        }else {
            eventid = "";
        }

        String EvSortOrder = EventEntry._ID + " ASC";
        String EvSelection = EventEntry._ID + " = ?";
        String[] EvSelectionArgs = {eventid};

        Cursor cEv = context.getContentResolver().query(EventEntry.CONTENT_URI, EVENT_COLUMNS, EvSelection, EvSelectionArgs, EvSortOrder);

        String ScdSortOrder = ScorecardEntry._ID + " ASC";
        String ScdSelection = ScorecardEntry._ID + " = ?";
        String[] ScdSelectionArgs = {scorecardid};
        Cursor cScd = context.getContentResolver().query(ScorecardEntry.CONTENT_URI, SCORECARD_COLUMNS, ScdSelection, ScdSelectionArgs, ScdSortOrder);

        if(cScd.moveToFirst() && cEv.moveToFirst()) {

            falseAlertFringeStr = cScd.getString(COL_FAF);
            falseAlertFringe = Integer.decode(falseAlertFringeStr);
            elapsed_time = cScd.getString(COL_TOTALT);

            if (cScd.getString(COL_TIMEOUT).equals("yes") || cScd.getString(COL_FINCALL).equals("no") && cEv.getString(COL_DIV).equals("Elite")) {
                elapsed_time = cScd.getString(COL_MAXTM) + ":" + cScd.getString(COL_MAXTS) + ":00";
            } else if (!cEv.getString(COL_DIV).equals("Elite")) {
                if (cScd.getString(COL_TIMEOUT).equals("yes") || cScd.getString(COL_FINCALL).equals("no") || cScd.getString(COL_ABSENT).equals("yes") || cScd.getString(COL_EDS).equals("yes") || cScd.getString(COL_EXCUSED).equals("yes") || falseAlertFringe > 0) {
                    elapsed_time = cScd.getString(COL_MAXTM) + ":" + cScd.getString(COL_MAXTS) + ":00";
                }
            }
        }
        return elapsed_time;
    }




    public static String getPoints(Context context, String scorecardid){

        double falseAlertFringe = 0.0;
        String falseAlertFringeStr = "";
        double totalFaults = 0.0;
        String totalFaultsStr = "";
        double maxPoint = 0.0;
        String maxPointStr = "";
        double hidesMax = 0.0;
        String hidesMaxStr = "";
        double hidesFound = 0.0;
        String hidesFoundStr = "";
        double eliteHides = 0.0;
        String eliteHidesStr = "";

        String eventid = "";

        double points = 0.0;
        String pointStr = "";

        String EvEnScdSortOrder = EventEntrantScorecardEntry._ID + " ASC";
        String EvEnScdSelection = EventEntrantScorecardEntry.COLUMN_SCORECARD_ID + " = ?";
        String[] EvEnScdSelectionArgs = {scorecardid};
        Cursor cEvEnScd = context.getContentResolver().query(EventEntrantScorecardEntry.CONTENT_URI, EVENTENTRANTSCORECARD_COLUMNS, EvEnScdSelection, EvEnScdSelectionArgs, EvEnScdSortOrder);

        if(cEvEnScd.moveToFirst()) {
            eventid = cEvEnScd.getString(COL_EVSC_ID);
        }else {
            eventid = "";
        }

        String EvSortOrder = EventEntry._ID + " ASC";
        String EvSelection = EventEntry._ID + " = ?";
        String[] EvSelectionArgs = {eventid};

        Cursor cEv = context.getContentResolver().query(EventEntry.CONTENT_URI, EVENT_COLUMNS, EvSelection, EvSelectionArgs, EvSortOrder);

        String ScdSortOrder = ScorecardEntry._ID + " ASC";
        String ScdSelection = ScorecardEntry._ID + " = ?";
        String[] ScdSelectionArgs = {scorecardid};
        Cursor cScd = context.getContentResolver().query(ScorecardEntry.CONTENT_URI, SCORECARD_COLUMNS, ScdSelection, ScdSelectionArgs, ScdSortOrder);

        if(cScd.moveToFirst() && cEv.moveToFirst()) {

            if (cScd.getString(COL_FAF).equals("")) {
                falseAlertFringe = 0.0;
            } else {
                falseAlertFringeStr = cScd.getString(COL_FAF);
                falseAlertFringe = Double.parseDouble(falseAlertFringeStr);
            }
            if (cScd.getString(COL_TOTALFLTS).equals("")) {
                totalFaults = 0.0;
            } else {
                totalFaultsStr = cScd.getString(COL_TOTALFLTS);
                totalFaults = Double.parseDouble(totalFaultsStr);
            }
            if (cScd.getString(COL_MAXPT).equals("")) {
                maxPoint = 0.0;
            } else {
                maxPointStr = cScd.getString(COL_MAXPT);
                maxPoint = Double.parseDouble(maxPointStr);
            }
            if (cScd.getString(COL_HDMAX).equals("")) {
                hidesMax = 0.0;
            } else {
                hidesMaxStr = cScd.getString(COL_HDMAX);
                hidesMax = Double.parseDouble(hidesMaxStr);
            }
            if (cScd.getString(COL_HDFOUND).equals("")) {
                hidesFound = 0.0;
            } else {
                hidesFoundStr = cScd.getString(COL_HDFOUND);
                hidesFound = Double.parseDouble(hidesFoundStr);
            }
            if (cEv.getString(COL_ELITE_HD).equals("")) {
                eliteHides = 0.0;
            } else {
                eliteHidesStr = cEv.getString(COL_ELITE_HD);
                eliteHides = Double.parseDouble(eliteHidesStr);
            }

            points = 0.0;
            if (totalFaults <= 3) {
                if (hidesFound == hidesMax && cEv.getString(COL_DIV).equals("NW1")) {
                    points = maxPoint;
                } else if ((cEv.getString(COL_DIV).equals("NW2")) || (cEv.getString(COL_DIV).equals("NW3")) || (cEv.getString(COL_DIV).equals("Element Specialty"))) {
                    points = hidesFound * maxPoint / hidesMax;
                } else if (cEv.getString(COL_DIV).equals("Elite")) {
                    if (falseAlertFringe == 0) {
                        points = (hidesFound * maxPoint / hidesMax) - totalFaults;
                    } else if (falseAlertFringe <= 3 && falseAlertFringe > 0) {
                        points = (hidesFound * maxPoint) - totalFaults + (falseAlertFringe * 100.0 / eliteHides / 2);
                    }
                    if (cScd.getString(COL_FINCALL).equals("no")) {
                        if (hidesFound > 0 || falseAlertFringe > 0) {
                            points = points - 100.0 / eliteHides / 2;
                        }
                    }
                } else {
                    points = 0.0;
                }
            } else {
                points = 0.0;
            }
            if ((cScd.getString(COL_ABSENT).equals("yes")) || (cScd.getString(COL_EDS).equals("yes")) || (cScd.getString(COL_EXCUSED).equals("yes"))) {
                points = 0.0;
            }
        }
        pointStr = String.format("%1.1f", points);
        return pointStr;
    }




    public static void getTally(Context context, String tallyid){

        String eventid = "";
        String entrantid = "";

        String EvEnTlySortOrder = EventEntrantTallyEntry._ID + " ASC";
        String EvEnTlySelection = EventEntrantTallyEntry.COLUMN_TALLY_ID + " = ?";
        String[] EvEnTlySelectionArgs = {tallyid};

        Cursor cEvEnTly = context.getContentResolver().query(EventEntrantTallyEntry.CONTENT_URI, EVENTENTRANTTALLY_COLUMNS, EvEnTlySelection, EvEnTlySelectionArgs, EvEnTlySortOrder);

        if (cEvEnTly.moveToFirst()) {
            eventid = cEvEnTly.getString(COL_EVTLY_ID);
            entrantid = cEvEnTly.getString(COL_ENTLY_ID);
        } else {
            eventid = "";
            entrantid = "";
        }

        String EvSortOrder = EventEntry._ID + " ASC";
        String EvSelection = EventEntry._ID + " = ?";
        String[] EvSelectionArgs = {eventid};

        Cursor cEv = context.getContentResolver().query(EventEntry.CONTENT_URI, EVENT_COLUMNS, EvSelection, EvSelectionArgs, EvSortOrder);

        Integer q_scores = new Integer(0);
        Integer q_score = new Integer(0);
        Integer sc_time = new Integer(0);
        Integer time_tally = new Integer(0);
        Integer minInt = new Integer(0);
        Integer secInt = new Integer(0);
        Integer secIntMod = new Integer(0);
        Integer msec = new Integer(0);
        Integer msecIntRnd = new Integer(0);

        Long point_tally_round = new Long(0);
        Long msecDblRnd = new Long(0);

        Double point_tally = new Double(0.0);
        Double tly_total_points = new Double(0.0);
        Double sc_total_points = new Double(0.0);
        Double sc_faults = new Double(0.0);
        Double fault_tally = new Double(0.0);
        Double time_tally_dbl = new Double(0.0);
        Double sec = new Double(0.0);
        Double secModDbl = new Double(0.0);
        Double msecDbl = new Double(0.0);
        Double min = new Double(0.0);

        String m_str = "";
        String s_str = "";
        String ms_str = "";
        String time_tally_str = "";
        String point_tally_str = "";
        String fault_tally_str = "";
        String q_score_str = "";
        String q_scores_str = "";
        String titled = "";
        String specialty = "";

        if(cEv.moveToFirst()) {
            if (cEv.getString(COL_DIV) == "Element Specialty") {
                if (!cEv.getString(COL_CONT_SA).equals("0") || !cEv.getString(COL_CONT_SA).equals("")) {
                    specialty = "Containers";
                } else if (!cEv.getString(COL_EXT_SA).equals("0") || !cEv.getString(COL_EXT_SA).equals("")) {
                    specialty = "Exterior";
                } else if (!cEv.getString(COL_INT_SA).equals("0") || !cEv.getString(COL_INT_SA).equals("")) {
                    specialty = "Interior";
                } else {
                    specialty = "Vehicles";
                }

                String EvEnSortOrder = EventEntrantTallyEntry._ID + " ASC";
                String EvEnSelection = EventEntrantTallyEntry.COLUMN_ENTRANT_ID + " = ?";
                String[] EvEnSelectionArgs = {entrantid};

                Cursor cEvEn = context.getContentResolver().query(EventEntrantTallyEntry.CONTENT_URI, EVENTENTRANTTALLY_COLUMNS, EvEnTlySelection, EvEnTlySelectionArgs, EvEnTlySortOrder);
                if (cEvEn.moveToFirst()) {
                    do {
                        String EnEvSortOrder = EventEntry._ID + " ASC";
                        String EnEvSelection = EventEntry._ID + " = ?";
                        String[] EnEvSelectionArgs = {cEvEn.getString(COL_EVENT_ID)};

                        Cursor cEnEv = context.getContentResolver().query(EventEntry.CONTENT_URI, EVENT_COLUMNS, EnEvSelection, EnEvSelectionArgs, EnEvSortOrder);

                        if (cEnEv.moveToFirst()) {
                            if (cEnEv.getString(COL_DIV).equals("Element Specialty")) {
                                if (((specialty.equals("Containers")) && (!cEnEv.getString(COL_CONT_SA).equals("0"))) || ((specialty.equals("Containers")) && (!cEnEv.getString(COL_CONT_SA).equals(""))) || ((specialty.equals("Exterior")) && (!cEnEv.getString(COL_EXT_SA).equals("0"))) || ((specialty.equals("Exterior")) && (!cEnEv.getString(COL_EXT_SA).equals(""))) || ((specialty.equals("Interior")) && (!cEnEv.getString(COL_INT_SA).equals("0"))) || ((specialty.equals("Interior")) && (!cEnEv.getString(COL_INT_SA).equals(""))) || ((specialty.equals("Vehicles"))) && ((!cEnEv.getString(COL_VEH_SA).equals("0")) || specialty.equals("Vehicles")) && (!cEnEv.getString(COL_VEH_SA).equals(""))) {
                                    do {
                                        String EvEnTlyEnSortOrder = EventEntrantTallyEntry._ID + " ASC";
                                        String EvEnTlyEnSelection = EventEntrantTallyEntry.COLUMN_ENTRANT_ID + " = ? AND " + EventEntrantTallyEntry.COLUMN_EVENT_ID + " = ?";
                                        String[] EvEnTlyEnSelectionArgs = {cEvEn.getString(COL_ENTLY_ID), cEnEv.getString(COL_EVENT_ID)};

                                        Cursor cEvEnTlyEn = context.getContentResolver().query(EventEntrantTallyEntry.CONTENT_URI, EVENTENTRANTTALLY_COLUMNS, EvEnTlySelection, EvEnTlySelectionArgs, EvEnTlySortOrder);

                                        String TlySortOrder = TallyEntry._ID + " ASC";
                                        String TlySelection = TallyEntry._ID + " = ?";
                                        String[] TlySelectionArgs = {cEvEnTlyEn.getString(COL_TALLY_ID)};

                                        Cursor cTly = context.getContentResolver().query(TallyEntry.CONTENT_URI, TALLY_COLUMNS, TlySelection, TlySelectionArgs, TlySortOrder);

                                        if (cTly.moveToFirst()) {
                                            do {
                                                if (cTly.getString(COL_QSCORE).equals("")) {
                                                    q_scores = 0;
                                                } else if (cTly.getString(COL_QSCORE).equals("1")) {
                                                    q_scores += 1;
                                                }
                                                cTly.moveToNext();
                                            } while (!cTly.isAfterLast());
                                        }
                                        cEnEv.moveToNext();
                                    } while (!cEnEv.isAfterLast());
                                }
                            }
                        }
                        cEvEn.moveToNext();
                    } while (!cEvEn.isAfterLast());
                }
            }
            if (q_scores >= 2) {
                q_scores = 0;
            }
            tly_total_points = 0.0;
            if (cEv.getString(COL_DIV).equals("Elite")) {
                String EvEnSortOrder = EventEntrantTallyEntry._ID + " ASC";
                String EvEnSelection = EventEntrantTallyEntry.COLUMN_ENTRANT_ID + " = ?";
                String[] EvEnSelectionArgs = {entrantid};

                Cursor cEvEn = context.getContentResolver().query(EventEntrantTallyEntry.CONTENT_URI, EVENTENTRANTTALLY_COLUMNS, EvEnTlySelection, EvEnTlySelectionArgs, EvEnTlySortOrder);
                if (cEvEn.moveToFirst()) {
                    do {
                        String EnEvSortOrder = EventEntry._ID + " ASC";
                        String EnEvSelection = EventEntry._ID + " = ?";
                        String[] EnEvSelectionArgs = {cEvEn.getString(COL_ENTLY_ID)};

                        Cursor cEnEv = context.getContentResolver().query(EventEntry.CONTENT_URI, EVENT_COLUMNS, EnEvSelection, EnEvSelectionArgs, EnEvSortOrder);

                        if (cEnEv.moveToFirst()) {
                            if (cEnEv.getString(COL_DIV).equals("Element Specialty")) {
                                if (((specialty.equals("Containers")) && (!cEnEv.getString(COL_CONT_SA).equals("0"))) || ((specialty.equals("Containers")) && (!cEnEv.getString(COL_CONT_SA).equals(""))) || ((specialty .equals("Exterior")) && (!cEnEv.getString(COL_EXT_SA).equals("0"))) || ((specialty.equals("Exterior")) && (!cEnEv.getString(COL_EXT_SA).equals(""))) || ((specialty.equals("Interior")) && (!cEnEv.getString(COL_INT_SA).equals("0"))) || ((specialty.equals("Interior")) && (!cEnEv.getString(COL_INT_SA).equals(""))) || ((specialty.equals("Vehicles"))) && ((!cEnEv.getString(COL_VEH_SA).equals("0")) || specialty.equals("Vehicles") && (!cEnEv.getString(COL_VEH_SA).equals("")))) {
                                    do {
                                        String EvEnTlyEnSortOrder = EventEntrantTallyEntry._ID + " ASC";
                                        String EvEnTlyEnSelection = EventEntrantTallyEntry.COLUMN_ENTRANT_ID + " = ? AND " + EventEntrantTallyEntry.COLUMN_EVENT_ID + " = ?";
                                        String[] EvEnTlyEnSelectionArgs = {cEvEn.getString(COL_ENTLY_ID), cEnEv.getString(COL_EVENT_ID)};

                                        Cursor cEvEnTlyEn = context.getContentResolver().query(EventEntrantTallyEntry.CONTENT_URI, EVENTENTRANTTALLY_COLUMNS, EvEnTlySelection, EvEnTlySelectionArgs, EvEnTlySortOrder);

                                        String TlySortOrder = TallyEntry._ID + " ASC";
                                        String TlySelection = TallyEntry._ID + " = ?";
                                        String[] TlySelectionArgs = {cEvEnTlyEn.getString(COL_TLY_ID)};

                                        Cursor cTly = context.getContentResolver().query(TallyEntry.CONTENT_URI, TALLY_COLUMNS, TlySelection, TlySelectionArgs, TlySortOrder);

                                        if (cTly.moveToFirst()) {
                                            do {
                                                if (cTly.getString(COL_TOTAL_POINTS).equals("")) {
                                                    tly_total_points = 0.0;
                                                } else if (cTly.getString(COL_QSCORE).equals("1")) {
                                                    tly_total_points = Double.parseDouble(cTly.getString(COL_TOTAL_POINTS));
                                                }
                                                point_tally += tly_total_points;
                                                cTly.moveToNext();
                                            } while (!cTly.isAfterLast());
                                        }
                                        cEnEv.moveToNext();
                                    } while (!cEnEv.isAfterLast());
                                }
                            }
                        }cEvEn.moveToNext();
                    } while (!cEvEn.isAfterLast());
                }
            }
            sc_total_points = 0.0;
            sc_time = 0;
            sc_faults = 0.0;
            point_tally_round = 0L;
            time_tally = 0;
            fault_tally = 0.0;
            q_score = 0;
            titled = "";

            String EvEnScdSortOrder = EventEntrantScorecardEntry._ID + " ASC";
            String EvEnScdSelection = EventEntrantScorecardEntry.COLUMN_EVENT_ID + " = ? AND " + EventEntrantScorecardEntry.COLUMN_ENTRANT_ID + " = ?";
            String[] EvEnScdSelectionArgs = {eventid, entrantid};
            Cursor cEvEnScd = context.getContentResolver().query(EventEntrantScorecardEntry.CONTENT_URI, EVENTENTRANTSCORECARD_COLUMNS, EvEnScdSelection, EvEnScdSelectionArgs, EvEnScdSortOrder);

            if (cEvEnScd.moveToFirst()) {
                do {
                    String ScdSortOrder = ScorecardEntry._ID + " ASC";
                    String ScdSelection = ScorecardEntry._ID + " = ?";
                    String[] ScdSelectionArgs = {cEvEnScd.getString(COL_SC_ID)};
                    Cursor cScd = context.getContentResolver().query(ScorecardEntry.CONTENT_URI, SCORECARD_COLUMNS, ScdSelection, ScdSelectionArgs, ScdSortOrder);

                    if (cScd.moveToFirst()) {
                        do {
                            if (cScd.getString(COL_TOTALPTS).equals("")) {
                                sc_total_points = 0.0;
                            } else {
                                sc_total_points = Double.parseDouble(cScd.getString(COL_TOTALPTS));
                            }
                            point_tally += sc_total_points;

                            if (cScd.getString(COL_TOTALT).equals("")) {
                                sc_time = 0;
                            } else {
                                sc_time = str_to_time(cScd.getString(COL_TOTALT));
                            }
                            time_tally += sc_time;

                            if (cScd.getString(COL_TOTALFLTS).equals("")) {
                                sc_faults = 0.0;
                            } else {
                                sc_faults = Double.parseDouble(cScd.getString(COL_TOTALFLTS));
                            }
                            fault_tally += sc_faults;

                            // round point tally

                            point_tally_round = Math.round(point_tally);

                            if (point_tally_round == 100 && fault_tally <= 3 && !cEv.getString(COL_DIV).equals("Element Specialty") && !cEv.getString(COL_DIV).equals("Elite")) {
                                titled = "Titled";
                            } else if (cEv.getString(COL_DIV).equals("Element Specialty")) {
                                // round point tally
                                if (point_tally_round >= 75 && fault_tally <= 3) {
                                    q_score = 1;
                                    q_scores += 1;
                                    if (q_scores == 2) {
                                        titled = "Titled";
                                    }
                                }
                                // round point tally
                                if (point_tally_round == 100 && fault_tally <= 3) {
                                    titled = "Titled";
                                }
                                // round point tally
                            } else if (cEv.getString(COL_DIV).equals("Elite") && point_tally_round >= 150 && fault_tally <= 3) {
                                titled = "Titled";
                            } else {
                                titled = "Not this time";
                            }
                            cScd.moveToNext();
                        } while (!cScd.isAfterLast());
                    }
                    cEvEnScd.moveToNext();
                } while (!cEvEnScd.isAfterLast());
            }
        }
        msec = time_tally%1000;
        msecDbl = msec.doubleValue();
        time_tally_dbl = time_tally.doubleValue();

        if (msec < 1 && time_tally%10000000 == 0){
            msec = 0;
        } else {
            // calculate seconds
            sec = (time_tally_dbl-msecDbl)/1000;
            secInt = sec.intValue();
            secIntMod = secInt%60;
            secModDbl = secIntMod.doubleValue();
            if (sec < 1) {
                sec = 0.0;
            } else {
                // calculate minutes
                min = (sec-secModDbl)/60;
                if (min < 1) {
                    min = 0.0;
                }
            }
        }
        // subtract elapsed minutes
        msecDbl = msecDbl/10;
        msecDblRnd = Math.round(msecDbl);
        msecIntRnd = msecDblRnd.intValue();
        sec = sec-(min*60);
        secInt = sec.intValue();
        minInt = min.intValue();

        m_str = minInt.toString();
        if (minInt < 10){
            m_str = "0" + m_str;
        }
        s_str = Integer.toString(secInt);
        if (secInt < 10){
            s_str = "0" + s_str;
        }
        ms_str = Integer.toString(msecIntRnd);
        if (msecIntRnd < 10){
            ms_str = "0" + ms_str;
        }
        time_tally_str = m_str + ":" + s_str + ":" + ms_str;
        point_tally_str = Double.toString(point_tally);
        fault_tally_str = Double.toString(fault_tally);
        q_score_str = Integer.toString(q_score);
        q_scores_str = Integer.toString(q_scores);

        ContentValues mTallyContentValues = new ContentValues();

        mTallyContentValues.put(TallyEntry.COLUMN_TOTAL_TIME, time_tally_str);
        mTallyContentValues.put(TallyEntry.COLUMN_TOTAL_FAULTS, fault_tally_str);
        mTallyContentValues.put(TallyEntry.COLUMN_TOTAL_POINTS, point_tally_str);
        mTallyContentValues.put(TallyEntry.COLUMN_TITLE, titled);
        mTallyContentValues.put(TallyEntry.COLUMN_QUALIFYING_SCORE, q_score_str);
        mTallyContentValues.put(TallyEntry.COLUMN_QUALIFYING_SCORES, q_scores_str);

        String selection = TallyEntry._ID + " = ?";
        String[] selectionArgs = {tallyid};
        context.getContentResolver().update(TallyEntry.CONTENT_URI, mTallyContentValues, selection, selectionArgs);

        return;
    }




    public static int str_to_time (String time){

        Integer time_int = new Integer(0);
        Integer minInt = new Integer(0);
        Integer secInt = new Integer(0);
        Integer msecInt = new Integer(0);

        String minStr = "";
        String secStr = "";
        String msecStr = "";

        String[] tdata = new String[2];
        String zeros = "00";
        String tdatam;
        String tdatas;
        String tdatams;

        Boolean matched;

        if ((time.equals("")) || (time.equals("0"))){
            time_int = 0;
        }else{
            tdata = time.split(":", 3);
            tdatam = tdata[0];
            if (tdatam.equals(zeros) || tdatam.equals("")){
                minInt = 0;
            }else if(tdatam.matches("^0[1-9]")){
                minStr = tdatam.substring(1);
                minInt = Integer.decode(minStr);
            }else{
                minInt = Integer.decode(tdatam);
            }
            tdatas = tdata[1];
            if (tdatas.equals(zeros)){
                secInt = 0;
            }else if(tdatas.matches("^0[1-9]")){
                secStr = tdatas.substring(1);
                secInt = Integer.decode(secStr);
            }else{
                secInt = Integer.decode(tdatas);
            }
            tdatams = tdata[2];
            if (tdatams.equals(zeros)){
                msecInt = Integer.decode("0");
            }else if(tdatams.matches("^0[1-9]")){
                msecStr = tdatams.substring(1);
                msecInt = Integer.decode(msecStr);
            }else{
                msecInt = Integer.decode(tdatams);
            }
            time_int = minInt*60*1000 + secInt*1000 + msecInt*10;
        }
        return time_int;
    }




    public static int timeToMs(String tallyid){
        int time;

        time = 0;

        return time;
    }
}