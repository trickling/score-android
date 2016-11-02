/**
 * Created by Kari Stromsland on 8/25/2016.
 */

 /*
 * Copyright (C) 2014 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.scoresheet.app.data;

import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.android.scoresheet.app.data.ScoreSheetContract.EntrantEntry;
import com.example.android.scoresheet.app.data.ScoreSheetContract.EventEntrantScorecardEntry;
import com.example.android.scoresheet.app.data.ScoreSheetContract.EventEntrantTallyEntry;
import com.example.android.scoresheet.app.data.ScoreSheetContract.EventEntry;
import com.example.android.scoresheet.app.data.ScoreSheetContract.EventUserEntry;
import com.example.android.scoresheet.app.data.ScoreSheetContract.ScorecardEntry;
import com.example.android.scoresheet.app.data.ScoreSheetContract.TallyEntry;
import com.example.android.scoresheet.app.data.ScoreSheetContract.UserEntry;

import java.util.ArrayList;

public class ScoreSheetDbHelper extends SQLiteOpenHelper {

    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 17;

    public static final String DATABASE_NAME = "scoresheet.db";

    public ScoreSheetDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // TODO:  Add uniqueness constraints to join tables at least.

    @Override
    public void onCreate(SQLiteDatabase db) {

        final String SQL_CREATE_EVENT_TABLE = "CREATE TABLE " +
                EventEntry.TABLE_NAME + " (" + EventEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                EventEntry.COLUMN_NAME + " TEXT DEFAULT 'Name', " +
                EventEntry.COLUMN_LOCATION + " TEXT DEFAULT 'Location', " +
                EventEntry.COLUMN_DATE + " TEXT DEFAULT 'Date', " +
                EventEntry.COLUMN_HOST + " TEXT DEFAULT 'Host', " +
                EventEntry.COLUMN_STATUS + " TEXT DEFAULT 'active', " +
                EventEntry.COLUMN_DIVISION + " TEXT DEFAULT 'NW', " +
                EventEntry.COLUMN_INT_SEARCH_AREAS + " TEXT DEFAULT '0', " +
                EventEntry.COLUMN_EXT_SEARCH_AREAS + " TEXT DEFAULT '0', " +
                EventEntry.COLUMN_CONT_SEARCH_AREAS + " TEXT DEFAULT '0', " +
                EventEntry.COLUMN_VEH_SEARCH_AREAS + " TEXT DEFAULT '0', " +
                EventEntry.COLUMN_ELITE_SEARCH_AREAS + " TEXT DEFAULT '0', " +
                EventEntry.COLUMN_INT_HIDES + " TEXT DEFAULT '0', " +
                EventEntry.COLUMN_EXT_HIDES + " TEXT DEFAULT '0', " +
                EventEntry.COLUMN_CONT_HIDES + " TEXT DEFAULT '0', " +
                EventEntry.COLUMN_VEH_HIDES + " TEXT DEFAULT '0', " +
                EventEntry.COLUMN_ELITE_HIDES + " TEXT DEFAULT '0' " +
                ")";
        db.execSQL(SQL_CREATE_EVENT_TABLE);


        final String SQL_CREATE_ENTRANT_TABLE = "CREATE TABLE " +
                EntrantEntry.TABLE_NAME + " (" + EntrantEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                EntrantEntry.COLUMN_FIRST_NAME + " TEXT DEFAULT 'First Name', " +
                EntrantEntry.COLUMN_LAST_NAME + " TEXT DEFAULT 'Last Name', " +
                EntrantEntry.COLUMN_ID_NUMBER + " TEXT DEFAULT '0', " +
                EntrantEntry.COLUMN_DOG_NAME + " TEXT DEFAULT 'Dog Name', " +
                EntrantEntry.COLUMN_DOG_ID_NUMBER + " TEXT DEFAULT '0', " +
                EntrantEntry.COLUMN_BREED + " TEXT DEFAULT 'Breed' " +
                ")";
        db.execSQL(SQL_CREATE_ENTRANT_TABLE);


//        final String SQL_CREATE_EVENTENTRANT_TABLE = "CREATE TABLE " +
//                EventEntrantEntry.TABLE_NAME + " (" + EventEntrantEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
//                EventEntrantEntry.COLUMN_EVENT_ID + " INTEGER NOT NULL, " +
//                EventEntrantEntry.COLUMN_ENTRANT_ID + " INTEGER NOT NULL, " +
//                ")";
//        db.execSQL(SQL_CREATE_EVENTENTRANT_TABLE);


        final String SQL_CREATE_USER_TABLE = "CREATE TABLE " +
                UserEntry.TABLE_NAME + " (" + UserEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                UserEntry.COLUMN_FIRST_NAME + " TEXT DEFAULT 'First Name', " +
                UserEntry.COLUMN_LAST_NAME + " TEXT DEFAULT 'Last Name', " +
                UserEntry.COLUMN_ROLE + " TEXT DEFAULT 'Role', " +
                UserEntry.COLUMN_APPROVED + " TEXT DEFAULT 'Approved', " +
                UserEntry.COLUMN_STATUS + " TEXT DEFAULT 'active', " +
                UserEntry.COLUMN_EMAIL + " TEXT DEFAULT 'email', " +
                UserEntry.COLUMN_PASSWORD + " TEXT DEFAULT 'password' " +
                ")";
        db.execSQL(SQL_CREATE_USER_TABLE);


        final String SQL_CREATE_EVENTUSER_TABLE = "CREATE TABLE " +
                EventUserEntry.TABLE_NAME + " (" + EventUserEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                EventUserEntry.COLUMN_EVENT_ID + " INTEGER NOT NULL, " +
                EventUserEntry.COLUMN_USER_ID + " INTEGER NOT NULL" +
                ")";
        db.execSQL(SQL_CREATE_EVENTUSER_TABLE);


        final String SQL_CREATE_EVENTENTRANTSCORECARD_TABLE = "CREATE TABLE " +
                EventEntrantScorecardEntry.TABLE_NAME + " (" + EventEntrantScorecardEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                EventEntrantScorecardEntry.COLUMN_EVENT_ID + " INTEGER NOT NULL, " +
                EventEntrantScorecardEntry.COLUMN_ENTRANT_ID + " INTEGER NOT NULL, " +
                EventEntrantScorecardEntry.COLUMN_SCORECARD_ID + " INTEGER NOT NULL " +
                ")";
        db.execSQL(SQL_CREATE_EVENTENTRANTSCORECARD_TABLE);


        final String SQL_CREATE_SCORECARD_TABLE = "CREATE TABLE " +
                ScorecardEntry.TABLE_NAME + " (" + ScorecardEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ScorecardEntry.COLUMN_ELEMENT + " TEXT DEFAULT 'Element', " +
                ScorecardEntry.COLUMN_MAXTIME_M + " TEXT DEFAULT '00', " +
                ScorecardEntry.COLUMN_MAXTIME_S + " TEXT DEFAULT '00', " +
                ScorecardEntry.COLUMN_FINISH_CALL + " TEXT DEFAULT 'yes', " +
                ScorecardEntry.COLUMN_FALSE_ALERT_FRINGE + " TEXT DEFAULT '0', " +
                ScorecardEntry.COLUMN_TIMED_OUT + " TEXT DEFAULT 'no', " +
                ScorecardEntry.COLUMN_DISMISSED + " TEXT DEFAULT 'no', " +
                ScorecardEntry.COLUMN_EXCUSED + " TEXT DEFAULT 'no', " +
                ScorecardEntry.COLUMN_ABSENT + " TEXT DEFAULT 'no', " +
                ScorecardEntry.COLUMN_ELIMINATED_DURING_SEARCH + " TEXT DEFAULT 'no', " +
                ScorecardEntry.COLUMN_OTHER_FAULTS_DESCR + " TEXT DEFAULT 'Describe...', " +
                ScorecardEntry.COLUMN_OTHER_FAULTS_COUNT + " TEXT DEFAULT '0', " +
                ScorecardEntry.COLUMN_COMMENTS + " TEXT DEFAULT 'Comments...', " +
                ScorecardEntry.COLUMN_TOTAL_TIME + " TEXT DEFAULT '0', " +
                ScorecardEntry.COLUMN_PRONOUNCED + " TEXT DEFAULT 'no', " +
                ScorecardEntry.COLUMN_JUDGE_SIGNATURE + " TEXT DEFAULT 'no', " +
                ScorecardEntry.COLUMN_SEARCH_AREA + " TEXT DEFAULT '1', " +
                ScorecardEntry.COLUMN_HIDES_MAX + " TEXT DEFAULT '1', " +
                ScorecardEntry.COLUMN_HIDES_FOUND + " TEXT DEFAULT '0', " +
                ScorecardEntry.COLUMN_HIDES_MISSED + " TEXT DEFAULT '0', " +
                ScorecardEntry.COLUMN_TOTAL_FAULTS + " TEXT DEFAULT '0', " +
                ScorecardEntry.COLUMN_MAXPOINT + " TEXT DEFAULT '0', " +
                ScorecardEntry.COLUMN_TOTAL_POINTS + " TEXT DEFAULT '0' " +
                ")";
        db.execSQL(SQL_CREATE_SCORECARD_TABLE);


        final String SQL_CREATE_TALLY_TABLE = "CREATE TABLE " +
                TallyEntry.TABLE_NAME + " (" + TallyEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                TallyEntry.COLUMN_TOTAL_TIME + " TEXT DEFAULT '0', " +
                TallyEntry.COLUMN_TOTAL_FAULTS + " TEXT DEFAULT '0', " +
                TallyEntry.COLUMN_TOTAL_POINTS + " TEXT DEFAULT '0', " +
                TallyEntry.COLUMN_TITLE + " TEXT DEFAULT 'no', " +
                TallyEntry.COLUMN_QUALIFYING_SCORE + " TEXT DEFAULT '0', " +
                TallyEntry.COLUMN_QUALIFYING_SCORES + " TEXT DEFAULT '0' " +
                ")";
        db.execSQL(SQL_CREATE_TALLY_TABLE);

        final String SQL_CREATE_EVENTENTRANTTALLY_TABLE = "CREATE TABLE " +
                EventEntrantTallyEntry.TABLE_NAME + " (" + EventEntrantTallyEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                EventEntrantTallyEntry.COLUMN_EVENT_ID + " INTEGER NOT NULL, " +
                EventEntrantTallyEntry.COLUMN_ENTRANT_ID + " INTEGER NOT NULL, " +
                EventEntrantTallyEntry.COLUMN_TALLY_ID + " INTEGER NOT NULL" +
                ")";
        db.execSQL(SQL_CREATE_EVENTENTRANTTALLY_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        // Note that this only fires if you change the version number for your database.
        // It does NOT depend on the version number for your application.
        // If you want to update the schema without wiping data, commenting out the next 2 lines
        // should be your top priority before modifying this method.
        db.execSQL("DROP TABLE IF EXISTS " + EventEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + EntrantEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + UserEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + EventUserEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + EventEntrantScorecardEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + ScorecardEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TallyEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + EventEntrantTallyEntry.TABLE_NAME);
        onCreate(db);
    }

    public ArrayList<Cursor> getData(String Query){
        //get writable database
        SQLiteDatabase sqlDB = this.getWritableDatabase();
        String[] columns = new String[] { "mesage" };
        //an array list of cursor to save two cursors one has results from the query
        //other cursor stores error message if any errors are triggered
        ArrayList<Cursor> alc = new ArrayList<Cursor>(2);
        MatrixCursor Cursor2= new MatrixCursor(columns);
        alc.add(null);
        alc.add(null);


        try{
            String maxQuery = Query ;
            //execute the query results will be save in Cursor c
            Cursor c = sqlDB.rawQuery(maxQuery, null);


            //add value to cursor2
            Cursor2.addRow(new Object[] { "Success" });

            alc.set(1,Cursor2);
            if (null != c && c.getCount() > 0) {


                alc.set(0,c);
                c.moveToFirst();

                return alc ;
            }
            return alc;
        } catch(SQLiteException sqlEx){
            Log.d("printing exception", sqlEx.getMessage());
            //if any exceptions are triggered save the error message to cursor an return the arraylist
            Cursor2.addRow(new Object[] { ""+sqlEx.getMessage() });
            alc.set(1,Cursor2);
            return alc;
        } catch(Exception ex){

            Log.d("printing exception", ex.getMessage());

            //if any exceptions are triggered save the error message to cursor an return the arraylist
            Cursor2.addRow(new Object[] { ""+ex.getMessage() });
            alc.set(1,Cursor2);
            return alc;
        }
    }
}
