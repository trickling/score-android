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

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

public class ScoreSheetProvider extends ContentProvider {
    // The URI Matcher used by this content provider.
    private static final UriMatcher sUriMatcher = buildUriMatcher();
    private ScoreSheetDbHelper mOpenHelper;

    static final int EVENT = 100;
    static final int EVENT_WITH_DESCR = 101;
    static final int ENTRANT = 200;
    static final int ENTRANT_WITH_DESCR = 201;

    private static final SQLiteQueryBuilder sEventByDescriptionQueryBuilder;

    static{
        sEventByDescriptionQueryBuilder = new SQLiteQueryBuilder();

        //This is an inner join which looks like
        //weather INNER JOIN location ON weather.location_id = location._id
        sEventByDescriptionQueryBuilder.setTables(
                ScoreSheetContract.EventEntry.TABLE_NAME);
    }

    private static final String sShortDescription = ScoreSheetContract.EventEntry.COLUMN_SHORT_DESC + " = ? ";

    private Cursor getEventByDescription(Uri uri, String[] projection, String sortOrder) {
        String description = ScoreSheetContract.EventEntry.getEventDescriptionFromUri(uri);

        return sEventByDescriptionQueryBuilder.query(mOpenHelper.getReadableDatabase(),
                projection,
                sShortDescription,
                new String[] {description},
                null,
                null,
                sortOrder
        );
    }

    private static final SQLiteQueryBuilder sEntrantByDescriptionQueryBuilder;

    static{
        sEntrantByDescriptionQueryBuilder = new SQLiteQueryBuilder();

        //This is an inner join which looks like
        //weather INNER JOIN location ON weather.location_id = location._id
        sEntrantByDescriptionQueryBuilder.setTables(
                ScoreSheetContract.EntrantEntry.TABLE_NAME);
    }

    private static final String sTeamDescription = ScoreSheetContract.EntrantEntry.COLUMN_TEAM_DESC + " = ? ";

    private Cursor getEntrantByDescription(Uri uri, String[] projection, String sortOrder) {
        String description = ScoreSheetContract.EntrantEntry.getEntrantDescriptionFromUri(uri);

        return sEntrantByDescriptionQueryBuilder.query(mOpenHelper.getReadableDatabase(),
                projection,
                sTeamDescription,
                new String[] {description},
                null,
                null,
                sortOrder
        );
    }

    static UriMatcher buildUriMatcher() {
        // 1) The code passed into the constructor represents the code to return for the root
        // URI.  It's common to use NO_MATCH as the code for this case. Add the constructor below.
        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        final String authority = ScoreSheetContract.CONTENT_AUTHORITY;

        // 2) Use the addURI function to match each of the types.  Use the constants from
        // WeatherContract to help define the types to the UriMatcher.
        matcher.addURI(authority, ScoreSheetContract.PATH_EVENT, EVENT);
        matcher.addURI(authority, ScoreSheetContract.PATH_EVENT + "/*", EVENT_WITH_DESCR);
        matcher.addURI(authority, ScoreSheetContract.PATH_ENTRANT, ENTRANT);
        matcher.addURI(authority, ScoreSheetContract.PATH_ENTRANT + "/*", ENTRANT_WITH_DESCR);
        return matcher;
    }

    @Override
    public boolean onCreate() {
        mOpenHelper = new ScoreSheetDbHelper(getContext());
        return true;
    }

    @Override
    public String getType(Uri uri) {

        // Use the Uri Matcher to determine what kind of URI this is.
        final int match = sUriMatcher.match(uri);

        switch (match) {
            case EVENT:
                return ScoreSheetContract.EventEntry.CONTENT_TYPE;
            case EVENT_WITH_DESCR:
                return ScoreSheetContract.EventEntry.CONTENT_TYPE;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
                        String sortOrder) {
        Cursor retCursor;
        switch (sUriMatcher.match(uri)) {
            case EVENT: {

                retCursor = mOpenHelper.getReadableDatabase().query(
                        ScoreSheetContract.EventEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );

                break;
            }
            case EVENT_WITH_DESCR: {
                retCursor = getEventByDescription(uri, projection, sortOrder);
                break;
            }
            case ENTRANT: {

                retCursor = mOpenHelper.getReadableDatabase().query(
                        ScoreSheetContract.EntrantEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );

                break;
            }
            case ENTRANT_WITH_DESCR: {
                retCursor = getEntrantByDescription(uri, projection, sortOrder);
                break;
            }

            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        retCursor.setNotificationUri(getContext().getContentResolver(), uri);
        return retCursor;
    }


    @Override
    public Uri insert(Uri uri, ContentValues values) {
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);
        Uri returnUri;

        switch (match) {
            case EVENT: {
                long _id = db.insert(ScoreSheetContract.EventEntry.TABLE_NAME, null, values);
                if ( _id > 0 )
                    returnUri = ScoreSheetContract.EventEntry.buildEventUri(_id);
                else
                    throw new android.database.SQLException("Failed to insert row into " + uri);
                break;
            }
            case EVENT_WITH_DESCR: {
                long _id = db.insert(ScoreSheetContract.EventEntry.TABLE_NAME, null, values);
                if ( _id > 0 )
                    returnUri = ScoreSheetContract.EventEntry.buildEventUri(_id);
                else
                    throw new android.database.SQLException("Failed to insert row into " + uri);
                break;
            }
            case ENTRANT: {
                long _id = db.insert(ScoreSheetContract.EntrantEntry.TABLE_NAME, null, values);
                if ( _id > 0 )
                    returnUri = ScoreSheetContract.EntrantEntry.buildEntrantUri(_id);
                else
                    throw new android.database.SQLException("Failed to insert row into " + uri);
                break;
            }
            case ENTRANT_WITH_DESCR: {
                long _id = db.insert(ScoreSheetContract.EntrantEntry.TABLE_NAME, null, values);
                if ( _id > 0 )
                    returnUri = ScoreSheetContract.EntrantEntry.buildEntrantUri(_id);
                else
                    throw new android.database.SQLException("Failed to insert row into " + uri);
                break;
            }
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        getContext().getContentResolver().notifyChange(uri, null);
//        db.close();
        return returnUri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Start by getting a writable database
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        // Use the uriMatcher to match the WEATHER and LOCATION URI's we are going to
        // handle.  If it doesn't match these, throw an UnsupportedOperationException.
        final int match = sUriMatcher.match(uri);

        // A null value deletes all rows.  In my implementation of this, I only notified
        // the uri listeners (using the content resolver) if the rowsDeleted != 0 or the selection
        // is null.
        int rowsDeleted;
//        if(null == selection) selection = "1";
        switch (match) {
            case EVENT: {
                rowsDeleted = db.delete(ScoreSheetContract.EventEntry.TABLE_NAME, selection, selectionArgs);
                break;
            }
            case EVENT_WITH_DESCR: {
                rowsDeleted = db.delete(ScoreSheetContract.EventEntry.TABLE_NAME, selection, selectionArgs);
                break;
            }
            case ENTRANT: {
                rowsDeleted = db.delete(ScoreSheetContract.EntrantEntry.TABLE_NAME, selection, selectionArgs);
                break;
            }
            case ENTRANT_WITH_DESCR: {
                rowsDeleted = db.delete(ScoreSheetContract.EntrantEntry.TABLE_NAME, selection, selectionArgs);
                break;
            }
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        // Oh, and you should notify the listeners here.
        if (rowsDeleted != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        // Student: return the actual rows deleted
//        db.close();
        return rowsDeleted;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        // Student: This is a lot like the delete function.  We return the number of rows impacted
        // by the update.
        // Student: Start by getting a writable database
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();

        // Student: Use the uriMatcher to match the WEATHER and LOCATION URI's we are going to
        // handle.  If it doesn't match these, throw an UnsupportedOperationException.
        final int match = sUriMatcher.match(uri);

        // Student: A null value deletes all rows.  In my implementation of this, I only notified
        // the uri listeners (using the content resolver) if the rowsDeleted != 0 or the selection
        // is null.
        int rowsUpdated;
        if(null == selection) selection = "1";
        switch (match) {
            case EVENT: {
                rowsUpdated = db.update(ScoreSheetContract.EventEntry.TABLE_NAME, values, selection, selectionArgs);
                break;
            }
            case EVENT_WITH_DESCR: {
                rowsUpdated = db.update(ScoreSheetContract.EventEntry.TABLE_NAME, values, selection, selectionArgs);
//                db.execSQL("UPDATE event SET " + selection + "=" + values + " WHERE " + );

                break;
            }
            case ENTRANT: {
                rowsUpdated = db.update(ScoreSheetContract.EntrantEntry.TABLE_NAME, values, selection, selectionArgs);
                break;
            }
            case ENTRANT_WITH_DESCR: {
                rowsUpdated = db.update(ScoreSheetContract.EntrantEntry.TABLE_NAME, values, selection, selectionArgs);
                break;
            }
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        // Oh, and you should notify the listeners here.
        getContext().getContentResolver().notifyChange(uri, null);
        // return the actual rows updated
//        db.close();
        return rowsUpdated;
    }

    @Override
    public int bulkInsert(Uri uri, ContentValues[] values) {
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);
        int returnCount;
        switch (match) {
            case EVENT:
                db.beginTransaction();
                returnCount = 0;
                try {
                    for (ContentValues value : values) {
//                        normalizeDate(value);
                        long _id = db.insert(ScoreSheetContract.EventEntry.TABLE_NAME, null, value);
                        if (_id != -1) {
                            returnCount++;
                        }
                    }
                    db.setTransactionSuccessful();
                } finally {
                    db.endTransaction();
                }
                getContext().getContentResolver().notifyChange(uri, null);
                return returnCount;
            case ENTRANT:
                db.beginTransaction();
                returnCount = 0;
                try {
                    for (ContentValues value : values) {
                        long _id = db.insert(ScoreSheetContract.EntrantEntry.TABLE_NAME, null, value);
                        if (_id != -1) {
                            returnCount++;
                        }
                    }
                    db.setTransactionSuccessful();
                } finally {
                    db.endTransaction();
                }
                getContext().getContentResolver().notifyChange(uri, null);
                return returnCount;
            default:
                return super.bulkInsert(uri, values);
        }
    }


    // You do not need to call this method. This is a method specifically to assist the testing
    // framework in running smoothly. You can read more at:
    // http://developer.android.com/reference/android/content/ContentProvider.html#shutdown()
//    @Override
//    @TargetApi(11)
//    public void shutdown() {
//        mOpenHelper.close();
//        super.shutdown();
//    }
}
