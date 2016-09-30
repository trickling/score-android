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
    static final int EVENT_ID = 101;
    static final int EVENT_ID_DESCR = 102;
    static final int EVENT_DESCR = 103;
    static final int ENTRANT = 200;
    static final int ENTRANT_WITH_DESCR = 201;
    static final int ENTRANT_WITH_EVENT_ID = 202;
    static final int ENTRANT_WITH_EVENT_ID_CHECKED = 203;
    static final int EVENTENTRANT = 300;
    static final int EVENTENTRANT_WITH_ENTRANT_ID = 301;
    static final int EVENTENTRANT_WITH_EVENT_ID = 302;
    static final int EVENTENTRANT_EVENTIDENTRANTID = 303;


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


    private static final SQLiteQueryBuilder sEventByIDforDescriptionQueryBuilder;

    static{
        sEventByIDforDescriptionQueryBuilder = new SQLiteQueryBuilder();

        //This is an inner join which looks like
        //weather INNER JOIN location ON weather.location_id = location._id
        sEventByIDforDescriptionQueryBuilder.setTables(
                ScoreSheetContract.EventEntry.TABLE_NAME);
    }

    private static final String sID = ScoreSheetContract.EventEntry._ID + " = ? ";

    private Cursor getEventByIDforDescription(Uri uri, String[] projection, String sortOrder) {
        long id = ScoreSheetContract.EventEntry.getEventIdFromUri(uri);

        return sEventByIDforDescriptionQueryBuilder.query(mOpenHelper.getReadableDatabase(),
                projection,
                sID,
                new String[] {Long.valueOf(id).toString()},
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
        String description = ScoreSheetContract.EntrantEntry.getEntrantDescFromUri(uri);

        return sEntrantByDescriptionQueryBuilder.query(mOpenHelper.getReadableDatabase(),
                projection,
                sTeamDescription,
                new String[] {description},
                null,
                null,
                sortOrder
        );
    }


    private static final SQLiteQueryBuilder sEntrantsByEventIDQueryBuilder;

    static{
        sEntrantsByEventIDQueryBuilder = new SQLiteQueryBuilder();
        String sEventid = new String();
        //This is an inner join which looks like
        //weather INNER JOIN location ON weather.location_id = location._id
        sEntrantsByEventIDQueryBuilder.setTables(
                ScoreSheetContract.EntrantEntry.TABLE_NAME + ", " + ScoreSheetContract.EventEntrantEntry.TABLE_NAME + " WHERE " + ScoreSheetContract.EntrantEntry.TABLE_NAME + "." + "_id" + " = " + ScoreSheetContract.EventEntrantEntry.TABLE_NAME + "." + ScoreSheetContract.EventEntrantEntry.COLUMN_ENTRANT_ID + " AND " + ScoreSheetContract.EventEntrantEntry.TABLE_NAME + "." + ScoreSheetContract.EventEntrantEntry.COLUMN_EVENT_ID + " = ?");
    }

//    private static final String sEventIdEntrantId = ScoreSheetContract.EventEntrantEntry.TABLE_NAME + "." + ScoreSheetContract.EventEntrantEntry.COLUMN_EVENT_ID + " = ?";

    private static final String sEventIdEntrantIds = null;

    private Cursor getEntrantsByEventEntrant(Uri uri, String[] projection, String sortOrder) {
        String eventid = Long.valueOf(ScoreSheetContract.EventEntry.getEventIdFromUri(uri)).toString();

        return sEntrantsByEventIDQueryBuilder.query(mOpenHelper.getReadableDatabase(),
                projection,
                sEventIdEntrantIds,
                new String[] {eventid},
                null,
                null,
                sortOrder
        );
    }





    private static final SQLiteQueryBuilder sEventEntrantByEventQueryBuilder;

    static{
        sEventEntrantByEventQueryBuilder = new SQLiteQueryBuilder();

        //This is an inner join which looks like
        //weather INNER JOIN location ON weather.location_id = location._id
        sEventEntrantByEventQueryBuilder.setTables(
                ScoreSheetContract.EventEntrantEntry.TABLE_NAME + " INNER JOIN " +
                        ScoreSheetContract.EventEntry.TABLE_NAME + "ON" +
                        ScoreSheetContract.EventEntrantEntry.TABLE_NAME + "." +
                        ScoreSheetContract.EventEntrantEntry.COLUMN_EVENT_ID + " = " +
                        ScoreSheetContract.EventEntry.TABLE_NAME + "." +
                        ScoreSheetContract.EventEntry._ID);
    }

    private static final String sEvent_ID = ScoreSheetContract.EventEntry.TABLE_NAME + "." + ScoreSheetContract.EventEntry._ID + " =? ";

    private Cursor getEventIdEntrant(Uri uri, String[] projection, String sortOrder) {
        String eventid = ScoreSheetContract.EventEntrantEntry.getEventIdFromUri(uri);

        return sEventEntrantByEventQueryBuilder.query(mOpenHelper.getReadableDatabase(),
                projection,
                sEvent_ID,
                new String[] {eventid},
                null,
                null,
                sortOrder
        );
    }




    private static final SQLiteQueryBuilder sEventEntrantByEntrantQueryBuilder;

    static{
        sEventEntrantByEntrantQueryBuilder = new SQLiteQueryBuilder();
        sEventEntrantByEntrantQueryBuilder.setTables(
                ScoreSheetContract.EventEntrantEntry.TABLE_NAME + " INNER JOIN " +
                        ScoreSheetContract.EntrantEntry.TABLE_NAME + "ON" +
                        ScoreSheetContract.EventEntrantEntry.TABLE_NAME + "." +
                        ScoreSheetContract.EventEntrantEntry.COLUMN_EVENT_ID + " = " +
                        ScoreSheetContract.EntrantEntry.TABLE_NAME + "." +
                        ScoreSheetContract.EntrantEntry._ID);
    }

    private static final String sEntrant_ID = ScoreSheetContract.EntrantEntry.TABLE_NAME + "." + ScoreSheetContract.EntrantEntry._ID + " =? ";

    private Cursor getEventEntrantId(Uri uri, String[] projection, String sortOrder) {
        String entrantid = ScoreSheetContract.EventEntrantEntry.getEventIdFromUri(uri);

        return sEventEntrantByEntrantQueryBuilder.query(mOpenHelper.getReadableDatabase(),
                projection,
                sEntrant_ID,
                new String[] {entrantid},
                null,
                null,
                sortOrder
        );
    }


    private static final SQLiteQueryBuilder sIdEventEntrantQueryBuilder;

    static{
        sIdEventEntrantQueryBuilder = new SQLiteQueryBuilder();
            sIdEventEntrantQueryBuilder.setTables(
//                    ScoreSheetContract.EventEntrantEntry.TABLE_NAME + ", " + ScoreSheetContract.EntrantEntry.TABLE_NAME + ", " + ScoreSheetContract.EventEntry.TABLE_NAME + " WHERE " + ScoreSheetContract.EventEntrantEntry.TABLE_NAME + "." + ScoreSheetContract.EventEntrantEntry.COLUMN_ENTRANT_ID + " = " + ScoreSheetContract.EntrantEntry.TABLE_NAME + "." + "_id" + " AND " + ScoreSheetContract.EventEntrantEntry.TABLE_NAME + "." + ScoreSheetContract.EventEntrantEntry.COLUMN_EVENT_ID + " = " + ScoreSheetContract.EventEntry.TABLE_NAME + "." + "_id");
        ScoreSheetContract.EventEntrantEntry.TABLE_NAME );
    }

    private static final String sIdEventEntrant = ScoreSheetContract.EventEntrantEntry.TABLE_NAME + "." + ScoreSheetContract.EventEntrantEntry.COLUMN_EVENT_ID + " = ? " + " AND " +
            ScoreSheetContract.EventEntrantEntry.TABLE_NAME + "." + ScoreSheetContract.EventEntrantEntry.COLUMN_ENTRANT_ID + " = ? ";

    private Cursor getIdEventEntrant(Uri uri, String[] projection, String sortOrder) {
        String eventid = ScoreSheetContract.EventEntrantEntry.getEventIdFromUri(uri);
        String entrantid = ScoreSheetContract.EventEntrantEntry.getEntrantIdWithEventIdFromUri(uri);
        String[] selectionArgs = {eventid, entrantid};
        return sIdEventEntrantQueryBuilder.query(mOpenHelper.getReadableDatabase(),
                projection,
                sIdEventEntrant,
                selectionArgs,
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
        matcher.addURI(authority, ScoreSheetContract.PATH_EVENT + "/#", EVENT_ID);
        matcher.addURI(authority, ScoreSheetContract.PATH_EVENT + "/short_desc", EVENT_DESCR);
        matcher.addURI(authority, ScoreSheetContract.PATH_ENTRANT, ENTRANT);
        matcher.addURI(authority, ScoreSheetContract.PATH_ENTRANT + "/short_desc", ENTRANT_WITH_DESCR);
        matcher.addURI(authority, ScoreSheetContract.PATH_ENTRANT + "/#", ENTRANT_WITH_EVENT_ID);
        matcher.addURI(authority, ScoreSheetContract.PATH_ENTRANT + "/#/checked", ENTRANT_WITH_EVENT_ID_CHECKED);
        matcher.addURI(authority, ScoreSheetContract.PATH_EVENTENTRANT, EVENTENTRANT);
        matcher.addURI(authority, ScoreSheetContract.PATH_EVENTENTRANT + "/#", EVENTENTRANT_WITH_EVENT_ID);
        matcher.addURI(authority, ScoreSheetContract.PATH_EVENTENTRANT + "/#", EVENTENTRANT_WITH_ENTRANT_ID);
        matcher.addURI(authority, ScoreSheetContract.PATH_EVENTENTRANT + "/#/#", EVENTENTRANT_EVENTIDENTRANTID);
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
            case EVENT_ID:
                return ScoreSheetContract.EventEntry.CONTENT_TYPE;
            case EVENT_DESCR:
                return ScoreSheetContract.EventEntry.CONTENT_TYPE;
            case ENTRANT:
                return ScoreSheetContract.EntrantEntry.CONTENT_TYPE;
            case ENTRANT_WITH_DESCR:
                return ScoreSheetContract.EntrantEntry.CONTENT_TYPE;
            case ENTRANT_WITH_EVENT_ID:
                return ScoreSheetContract.EntrantEntry.CONTENT_TYPE;
            case ENTRANT_WITH_EVENT_ID_CHECKED:
                return ScoreSheetContract.EntrantEntry.CONTENT_TYPE;
            case EVENTENTRANT:
                return ScoreSheetContract.EventEntrantEntry.CONTENT_TYPE;
            case EVENTENTRANT_WITH_EVENT_ID:
                return ScoreSheetContract.EventEntrantEntry.CONTENT_TYPE;
            case EVENTENTRANT_WITH_ENTRANT_ID:
                return ScoreSheetContract.EventEntrantEntry.CONTENT_TYPE;
            case EVENTENTRANT_EVENTIDENTRANTID:
                return ScoreSheetContract.EventEntrantEntry.CONTENT_TYPE;
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
            case EVENT_ID: {
                retCursor = getEventByIDforDescription(uri, projection, sortOrder);
                break;
            }
            case EVENT_DESCR: {
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
            case ENTRANT_WITH_EVENT_ID: {
                retCursor = getEntrantsByEventEntrant(uri, projection, sortOrder);
                break;
            }
            case ENTRANT_WITH_EVENT_ID_CHECKED:{
                retCursor = getEntrantsByEventEntrant(uri, projection, sortOrder);
                break;
            }
            case EVENTENTRANT: {

                retCursor = mOpenHelper.getReadableDatabase().query(
                        ScoreSheetContract.EventEntrantEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );

                break;
            }
//            case EVENTENTRANT_WITH_EVENT_ID: {
//                retCursor = getEventIdEntrant(uri, projection, sortOrder);
//                break;
//            }
//            case EVENTENTRANT_WITH_ENTRANT_ID: {
//                retCursor = getEventEntrantId(uri, projection, sortOrder);
//                break;
//            }
            case EVENTENTRANT_EVENTIDENTRANTID: {
                retCursor = getIdEventEntrant(uri, projection, sortOrder);
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
                    returnUri = ScoreSheetContract.EventEntry.buildEventIdUri(_id);
                else
                    throw new android.database.SQLException("Failed to insert row into " + uri);
                break;
            }
            case EVENT_DESCR: {
                long _id = db.insert(ScoreSheetContract.EventEntry.TABLE_NAME, null, values);
                if ( _id > 0 )
                    returnUri = ScoreSheetContract.EventEntry.buildEventIdUri(_id);
                else
                    throw new android.database.SQLException("Failed to insert row into " + uri);
                break;
            }
            case ENTRANT: {
                long _id = db.insert(ScoreSheetContract.EntrantEntry.TABLE_NAME, null, values);
                if ( _id > 0 )
                    returnUri = ScoreSheetContract.EntrantEntry.buildEntrantIdUri(_id);
                else
                    throw new android.database.SQLException("Failed to insert row into " + uri);
                break;
            }
            case ENTRANT_WITH_DESCR: {
                long _id = db.insert(ScoreSheetContract.EntrantEntry.TABLE_NAME, null, values);
                if ( _id > 0 )
                    returnUri = ScoreSheetContract.EntrantEntry.buildEntrantIdUri(_id);
                else
                    throw new android.database.SQLException("Failed to insert row into " + uri);
                break;
            }

            case EVENTENTRANT: {
                long _id = db.insert(ScoreSheetContract.EventEntrantEntry.TABLE_NAME, null, values);
                if ( _id > 0 )
                    returnUri = ScoreSheetContract.EventEntrantEntry.buildIdEventEntrantUri(_id);
                else
                    throw new android.database.SQLException("Failed to insert row into " + uri);
                break;
            }
            case EVENTENTRANT_WITH_EVENT_ID: {
                long _id = db.insert(ScoreSheetContract.EventEntrantEntry.TABLE_NAME, null, values);
                if ( _id > 0 )
                    returnUri = ScoreSheetContract.EventEntrantEntry.buildIdEventEntrantUri(_id);
                else
                    throw new android.database.SQLException("Failed to insert row into " + uri);
                break;
            }
            case EVENTENTRANT_WITH_ENTRANT_ID: {
                long _id = db.insert(ScoreSheetContract.EventEntrantEntry.TABLE_NAME, null, values);
                if ( _id > 0 )
                    returnUri = ScoreSheetContract.EventEntrantEntry.buildIdEventEntrantUri(_id);
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
            case EVENT_DESCR: {
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
            case EVENTENTRANT: {
                rowsDeleted = db.delete(ScoreSheetContract.EventEntrantEntry.TABLE_NAME, selection, selectionArgs);
                break;
            }
            case EVENTENTRANT_WITH_EVENT_ID: {
                rowsDeleted = db.delete(ScoreSheetContract.EventEntrantEntry.TABLE_NAME, selection, selectionArgs);
                break;
            }
            case EVENTENTRANT_WITH_ENTRANT_ID: {
                rowsDeleted = db.delete(ScoreSheetContract.EventEntrantEntry.TABLE_NAME, selection, selectionArgs);
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
            case EVENT_DESCR: {
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
            case EVENTENTRANT: {
                rowsUpdated = db.update(ScoreSheetContract.EventEntrantEntry.TABLE_NAME, values, selection, selectionArgs);
                break;
            }
            case EVENTENTRANT_WITH_EVENT_ID: {
                rowsUpdated = db.update(ScoreSheetContract.EventEntrantEntry.TABLE_NAME, values, selection, selectionArgs);
                break;
            }
            case EVENTENTRANT_WITH_ENTRANT_ID: {
                rowsUpdated = db.update(ScoreSheetContract.EventEntrantEntry.TABLE_NAME, values, selection, selectionArgs);
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
            case EVENTENTRANT:
                db.beginTransaction();
                returnCount = 0;
                try {
                    for (ContentValues value : values) {
                        long _id = db.insert(ScoreSheetContract.EventEntrantEntry.TABLE_NAME, null, value);
                        if (_id != -1) {
                            returnCount++;
                        }
                    }
                    db.setTransactionSuccessful();
                } finally {
                    db.endTransaction();
                }
                getContext().getContentResolver().notifyChange(uri, null);
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
