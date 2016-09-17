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
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.android.scoresheet.app.data.ScoreSheetContract.EventEntry;
import com.example.android.scoresheet.app.data.ScoreSheetContract.EntrantEntry;


public class ScoreSheetDbHelper extends SQLiteOpenHelper {

    // If you change the database schema, you must increment the database version.
    private static final int DATABASE_VERSION = 2;

    static final String DATABASE_NAME = "scoresheet.db";

    public ScoreSheetDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        final String SQL_CREATE_EVENT_TABLE = "CREATE TABLE " + EventEntry.TABLE_NAME + " (" + EventEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + EventEntry.COLUMN_SHORT_DESC + " TEXT NOT NULL)";

        db.execSQL(SQL_CREATE_EVENT_TABLE);

        final String SQL_CREATE_ENTRANT_TABLE = "CREATE TABLE " + EntrantEntry.TABLE_NAME + " (" + EntrantEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + EntrantEntry.COLUMN_TEAM_DESC + " TEXT NOT NULL)";

        db.execSQL(SQL_CREATE_ENTRANT_TABLE);
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
        onCreate(db);
    }
}
