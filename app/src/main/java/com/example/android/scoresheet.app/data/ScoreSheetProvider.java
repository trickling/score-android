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
    static final int EVENT_ID_NAME = 102;
    static final int EVENT_NAME = 103;

    static final int ENTRANT = 200;
    static final int ENTRANT_WITH_FIRST_NAME = 201;
    static final int ENTRANT_WITH_EVENT_ID = 202;
    static final int ENTRANT_WITH_EVENT_ID_CHECKED = 203;

//    static final int EVENTENTRANT = 900;
//    static final int EVENTENTRANT_WITH_ENTRANT_ID = 901;
//    static final int EVENTENTRANT_WITH_EVENT_ID = 902;

    static final int USER = 400;
    static final int USER_WITH_FIRST_NAME = 401;
    static final int USER_WITH_EVENT_ID = 402;
    static final int USER_WITH_EVENT_ID_CHECKED = 403;

    static final int EVENTUSER = 500;
    static final int EVENTUSER_WITH_USER_ID = 501;
    static final int EVENTUSER_WITH_EVENT_ID = 502;
    static final int EVENTUSER_EVENTIDUSERID = 503;

    static final int EVENTENTRANTSCORECARD = 300;
    static final int EVENTENTRANTSCORECARD_WITH_ENTRANT_ID = 301;
    static final int EVENTENTRANTSCORECARD_WITH_EVENT_ID = 302;
    static final int EVENTENTRANTSCORECARD_WITH_SCORECARD_ID = 303;
    static final int EVENTENTRANTSCORECARD_EVENTIDENTRANTID = 304;
    static final int EVENTENTRANTSCORECARD_EVENTIDENTRANTIDSCORECARDID = 305;

    static final int SCORECARD = 600;
    static final int SCORECARD_WITH_ELEMENT = 601;
    static final int SCORECARD_WITH_EVENTID = 602;
    static final int SCORECARD_WITH_EVENTID_ENTRANTID = 603;

    static final int TALLY = 700;
    static final int TALLY_WITH_TITLE = 701;
    static final int TALLY_WITH_EVENT_ID = 702;

    static final int EVENTENTRANTTALLY = 800;
    static final int EVENTENTRANTTALLY_WITH_ENTRANT_ID = 801;
    static final int EVENTENTRANTTALLY_WITH_EVENT_ID = 802;
    static final int EVENTENTRANTTALLY_WITH_TALLY_ID = 803;
    static final int EVENTENTRANTTALLY_EVENTIDENTRANTID = 804;
    static final int EVENTENTRANTTALLY_EVENTIDENTRANTIDTALLYID = 805;


    // EVENT QUERY HELPERS

    private static final SQLiteQueryBuilder sEventByNameQueryBuilder;

    static{
        sEventByNameQueryBuilder = new SQLiteQueryBuilder();

        sEventByNameQueryBuilder.setTables(
                ScoreSheetContract.EventEntry.TABLE_NAME);
    }

    private static final String sName = ScoreSheetContract.EventEntry.COLUMN_NAME + " = ? ";

    private Cursor getEventByName(Uri uri, String[] projection, String sortOrder) {
        String description = ScoreSheetContract.EventEntry.getEventNameFromUri(uri);

        return sEventByNameQueryBuilder.query(mOpenHelper.getReadableDatabase(),
                projection,
                sName,
                new String[] {description},
                null,
                null,
                sortOrder
        );
    }


    private static final SQLiteQueryBuilder sEventByIDforNameQueryBuilder;

    static{
        sEventByIDforNameQueryBuilder = new SQLiteQueryBuilder();

        sEventByIDforNameQueryBuilder.setTables(
                ScoreSheetContract.EventEntry.TABLE_NAME);
    }

    private static final String sID = ScoreSheetContract.EventEntry._ID + " = ? ";

    private Cursor getEventByIDforName(Uri uri, String[] projection, String sortOrder) {
        long id = ScoreSheetContract.EventEntry.getEventIdFromUri(uri);

        return sEventByIDforNameQueryBuilder.query(mOpenHelper.getReadableDatabase(),
                projection,
                sID,
                new String[] {Long.valueOf(id).toString()},
                null,
                null,
                sortOrder
        );
    }



//     ENTRANT QUERY HELPERS

    private static final SQLiteQueryBuilder sEntrantByFirstNameQueryBuilder;

    static{
        sEntrantByFirstNameQueryBuilder = new SQLiteQueryBuilder();

        sEntrantByFirstNameQueryBuilder.setTables(
                ScoreSheetContract.EntrantEntry.TABLE_NAME);
    }


    private static final String sTeamFirstName = ScoreSheetContract.EntrantEntry.COLUMN_FIRST_NAME + " = ? ";

    private Cursor getEntrantByFirstName(Uri uri, String[] projection, String sortOrder) {
        String description = ScoreSheetContract.EntrantEntry.getEntrantFirstNameFromUri(uri);

        return sEntrantByFirstNameQueryBuilder.query(mOpenHelper.getReadableDatabase(),
                projection,
                sTeamFirstName,
                new String[] {description},
                null,
                null,
                sortOrder
        );
    }



//     EVENTENTRANT QUERY HELPERS


//    private static final SQLiteQueryBuilder sEntrantsByEventIDQueryBuilder;
//
//    static{
//        sEntrantsByEventIDQueryBuilder = new SQLiteQueryBuilder();
//
//        sEntrantsByEventIDQueryBuilder.setTables(
//                ScoreSheetContract.EntrantEntry.TABLE_NAME + ", " + ScoreSheetContract.EventEntrantEntry.TABLE_NAME + " WHERE " + ScoreSheetContract.EntrantEntry.TABLE_NAME + "." + "_id" + " = " + ScoreSheetContract.EventEntrantEntry.TABLE_NAME + "." + ScoreSheetContract.EventEntrantEntry.COLUMN_ENTRANT_ID + " AND " + ScoreSheetContract.EventEntrantEntry.TABLE_NAME + "." + ScoreSheetContract.EventEntrantEntry.COLUMN_EVENT_ID + " = ?");
//    }
//
//    private static final String sEventIdEntrantIds = null;
//
//    private Cursor getEntrantsByEventEntrant(Uri uri, String[] projection, String sortOrder) {
//        String eventid = Long.valueOf(ScoreSheetContract.EventEntry.getEventIdFromUri(uri)).toString();
//
//        return sEntrantsByEventIDQueryBuilder.query(mOpenHelper.getReadableDatabase(),
//                projection,
//                sEventIdEntrantIds,
//                new String[] {eventid},
//                null,
//                null,
//                sortOrder
//        );
//    }



//     USER QUERY HELPERS

    private static final SQLiteQueryBuilder sUserByFirstNameQueryBuilder;

    static{
        sUserByFirstNameQueryBuilder = new SQLiteQueryBuilder();

        sUserByFirstNameQueryBuilder.setTables(
                ScoreSheetContract.UserEntry.TABLE_NAME);
    }


    private static final String sUserFirstName = ScoreSheetContract.UserEntry.COLUMN_FIRST_NAME + " = ? ";

    private Cursor getUserByFirstName(Uri uri, String[] projection, String sortOrder) {
        String description = ScoreSheetContract.UserEntry.getUserFirstNameFromUri(uri);

        return sUserByFirstNameQueryBuilder.query(mOpenHelper.getReadableDatabase(),
                projection,
                sUserFirstName,
                new String[] {description},
                null,
                null,
                sortOrder
        );
    }


    private static final SQLiteQueryBuilder sUsersByEventIDQueryBuilder;

    static{
        sUsersByEventIDQueryBuilder = new SQLiteQueryBuilder();

        sUsersByEventIDQueryBuilder.setTables(
                ScoreSheetContract.UserEntry.TABLE_NAME + ", " + ScoreSheetContract.EventUserEntry.TABLE_NAME + " WHERE " + ScoreSheetContract.UserEntry.TABLE_NAME + "." + "_id" + " = " + ScoreSheetContract.EventUserEntry.TABLE_NAME + "." + ScoreSheetContract.EventUserEntry.COLUMN_USER_ID + " AND " + ScoreSheetContract.EventUserEntry.TABLE_NAME + "." + ScoreSheetContract.EventUserEntry.COLUMN_EVENT_ID + " = ?");
    }

    private static final String sEventIdUserIds = null;

    private Cursor getUsersByEventUser(Uri uri, String[] projection, String sortOrder) {
        String eventid = Long.valueOf(ScoreSheetContract.EventEntry.getEventIdFromUri(uri)).toString();

        return sUsersByEventIDQueryBuilder.query(mOpenHelper.getReadableDatabase(),
                projection,
                sEventIdUserIds,
                new String[] {eventid},
                null,
                null,
                sortOrder
        );
    }



//     EVENTENTRANTSCORECARD QUERY HELPERS

    private static final SQLiteQueryBuilder sEntrantsByEventIDQueryBuilder;

    static{
        sEntrantsByEventIDQueryBuilder = new SQLiteQueryBuilder();

        sEntrantsByEventIDQueryBuilder.setTables(
                ScoreSheetContract.EntrantEntry.TABLE_NAME + ", " + ScoreSheetContract.EventEntrantScorecardEntry.TABLE_NAME + " WHERE " + ScoreSheetContract.EntrantEntry.TABLE_NAME + "." + "_id" + " = " + ScoreSheetContract.EventEntrantScorecardEntry.TABLE_NAME + "." + ScoreSheetContract.EventEntrantScorecardEntry.COLUMN_ENTRANT_ID + " AND " + ScoreSheetContract.EventEntrantScorecardEntry.TABLE_NAME + "." + ScoreSheetContract.EventEntrantScorecardEntry.COLUMN_EVENT_ID + " = ?");
    }

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

        sEventEntrantByEventQueryBuilder.setTables(
                ScoreSheetContract.EventEntrantScorecardEntry.TABLE_NAME + " INNER JOIN " +
                        ScoreSheetContract.EventEntry.TABLE_NAME + "ON" +
                        ScoreSheetContract.EventEntrantScorecardEntry.TABLE_NAME + "." +
                        ScoreSheetContract.EventEntrantScorecardEntry.COLUMN_EVENT_ID + " = " +
                        ScoreSheetContract.EventEntry.TABLE_NAME + "." +
                        ScoreSheetContract.EventEntry._ID);
    }

    private static final String sEvent_ID = ScoreSheetContract.EventEntry.TABLE_NAME + "." + ScoreSheetContract.EventEntry._ID + " =? ";

    private Cursor getEventIdEntrant(Uri uri, String[] projection, String sortOrder) {
        String eventid = ScoreSheetContract.EventEntrantScorecardEntry.getEventIdFromUri(uri);

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
                ScoreSheetContract.EventEntrantScorecardEntry.TABLE_NAME + " INNER JOIN " +
                        ScoreSheetContract.EntrantEntry.TABLE_NAME + " ON " +
                        ScoreSheetContract.EventEntrantScorecardEntry.TABLE_NAME + "." +
                        ScoreSheetContract.EventEntrantScorecardEntry.COLUMN_EVENT_ID + " = " +
                        ScoreSheetContract.EntrantEntry.TABLE_NAME + "." +
                        ScoreSheetContract.EntrantEntry._ID);
    }

    private static final String sEntrant_ID = ScoreSheetContract.EntrantEntry.TABLE_NAME + "." + ScoreSheetContract.EntrantEntry._ID + " =? ";

    private Cursor getEventEntrantId(Uri uri, String[] projection, String sortOrder) {
        String entrantid = ScoreSheetContract.EventEntrantScorecardEntry.getEventIdFromUri(uri);

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
        ScoreSheetContract.EventEntrantScorecardEntry.TABLE_NAME );
    }

    private static final String sIdEventEntrant = ScoreSheetContract.EventEntrantScorecardEntry.TABLE_NAME + "." + ScoreSheetContract.EventEntrantScorecardEntry.COLUMN_EVENT_ID + " = ? " + " AND " +
            ScoreSheetContract.EventEntrantScorecardEntry.TABLE_NAME + "." + ScoreSheetContract.EventEntrantScorecardEntry.COLUMN_ENTRANT_ID + " = ? ";

    private Cursor getIdEventEntrant(Uri uri, String[] projection, String sortOrder) {
        String eventid = ScoreSheetContract.EventEntrantScorecardEntry.getEventIdFromUri(uri);
        String entrantid = ScoreSheetContract.EventEntrantScorecardEntry.getEntrantIdWithEventIdFromUri(uri);
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



    private static final SQLiteQueryBuilder sIdEventEntrantScorecardQueryBuilder;

    static{
        sIdEventEntrantScorecardQueryBuilder = new SQLiteQueryBuilder();
        sIdEventEntrantScorecardQueryBuilder.setTables(
                ScoreSheetContract.EventEntrantScorecardEntry.TABLE_NAME );
    }

    private static final String sIdEventEntrantScorecard = ScoreSheetContract.EventEntrantScorecardEntry.TABLE_NAME + "." + ScoreSheetContract.EventEntrantScorecardEntry.COLUMN_EVENT_ID + " = ? " + " AND " +
            ScoreSheetContract.EventEntrantScorecardEntry.TABLE_NAME + "." + ScoreSheetContract.EventEntrantScorecardEntry.COLUMN_ENTRANT_ID + " = ? ";

    private Cursor getIdEventEntrantScorecard(Uri uri, String[] projection, String sortOrder) {
        String eventid = ScoreSheetContract.EventEntrantScorecardEntry.getEventIdFromUri(uri);
        String entrantid = ScoreSheetContract.EventEntrantScorecardEntry.getEntrantIdWithEventIdFromUri(uri);
        String scorecardid = ScoreSheetContract.EventEntrantScorecardEntry.getScorecardIdWithEventIdEntrantIdFromUri(uri);
        String[] selectionArgs = {eventid, entrantid};
        return sIdEventEntrantScorecardQueryBuilder.query(mOpenHelper.getReadableDatabase(),
                projection,
                sIdEventEntrantScorecard,
                selectionArgs,
                null,
                null,
                sortOrder
        );
    }




    // EVENTUSER QUERY HELPERS

    private static final SQLiteQueryBuilder sIdEventUserQueryBuilder;

    static{
        sIdEventUserQueryBuilder = new SQLiteQueryBuilder();
        sIdEventUserQueryBuilder.setTables(
                ScoreSheetContract.EventUserEntry.TABLE_NAME );
    }

    private static final String sIdEventUser = ScoreSheetContract.EventUserEntry.TABLE_NAME + "." + ScoreSheetContract.EventUserEntry.COLUMN_EVENT_ID + " = ? " + " AND " +
            ScoreSheetContract.EventUserEntry.TABLE_NAME + "." + ScoreSheetContract.EventUserEntry.COLUMN_USER_ID + " = ? ";

    private Cursor getIdEventUser(Uri uri, String[] projection, String sortOrder) {
        String eventid = ScoreSheetContract.EventUserEntry.getEventIdFromUri(uri);
        String entrantid = ScoreSheetContract.EventUserEntry.getUserIdWithEventIdFromUri(uri);
        String[] selectionArgs = {eventid, entrantid};
        return sIdEventUserQueryBuilder.query(mOpenHelper.getReadableDatabase(),
                projection,
                sIdEventUser,
                selectionArgs,
                null,
                null,
                sortOrder
        );
    }



    // SCORECARD QUERY HELPERS

    private static final SQLiteQueryBuilder sScorecardByElementQueryBuilder;

    static{
        sScorecardByElementQueryBuilder = new SQLiteQueryBuilder();

        sScorecardByElementQueryBuilder.setTables(
                ScoreSheetContract.ScorecardEntry.TABLE_NAME);
    }

    private static final String sScorecardElement = ScoreSheetContract.ScorecardEntry.COLUMN_ELEMENT + " = ? ";

    private Cursor getScorecardByElement(Uri uri, String[] projection, String sortOrder) {
        String description = ScoreSheetContract.ScorecardEntry.getScorecardElementFromUri(uri);

        return sScorecardByElementQueryBuilder.query(mOpenHelper.getReadableDatabase(),
                projection,
                sScorecardElement,
                new String[] {description},
                null,
                null,
                sortOrder
        );
    }

    private static final SQLiteQueryBuilder sScorecardByEventIdQueryBuilder;

    static{
        sScorecardByEventIdQueryBuilder = new SQLiteQueryBuilder();

        sScorecardByEventIdQueryBuilder.setTables(
                ScoreSheetContract.ScorecardEntry.TABLE_NAME);
    }

    private static final String sScorecardEventId = ScoreSheetContract.ScorecardEntry.COLUMN_ELEMENT + " = ? ";

    private Cursor getScorecardByEventId(Uri uri, String[] projection, String sortOrder) {
        String eventid = ScoreSheetContract.ScorecardEntry.getScorecardEventIdFromUri(uri);

        return sScorecardByEventIdQueryBuilder.query(mOpenHelper.getReadableDatabase(),
                projection,
                sScorecardEventId,
                new String[] {eventid},
                null,
                null,
                sortOrder
        );
    }

    private static final SQLiteQueryBuilder sScorecardsByEventEntrantQueryBuilder;

    static{
        sScorecardsByEventEntrantQueryBuilder = new SQLiteQueryBuilder();

        sScorecardsByEventEntrantQueryBuilder.setTables(
                ScoreSheetContract.ScorecardEntry.TABLE_NAME + ", " + ScoreSheetContract.EventEntrantScorecardEntry.TABLE_NAME + " WHERE " + ScoreSheetContract.ScorecardEntry.TABLE_NAME + "." + ScoreSheetContract.ScorecardEntry._ID + " = " + ScoreSheetContract.EventEntrantScorecardEntry.TABLE_NAME + "." + ScoreSheetContract.EventEntrantScorecardEntry.COLUMN_SCORECARD_ID + " AND " + ScoreSheetContract.EventEntrantScorecardEntry.COLUMN_EVENT_ID + " = ? AND " + ScoreSheetContract.EventEntrantScorecardEntry.COLUMN_ENTRANT_ID + " = ?");
    }

    private static final String sEventIdEntrantId = null;

    private Cursor getScorecardsByEventEntrant(Uri uri, String[] projection, String sortOrder) {
        String eventid = ScoreSheetContract.ScorecardEntry.getScorecardsEventIdFromUri(uri);
        String entrantid = ScoreSheetContract.ScorecardEntry.getScorecardsEntrantIdFromUri(uri);
        return sScorecardsByEventEntrantQueryBuilder.query(mOpenHelper.getReadableDatabase(),
                projection,
                sEventIdEntrantId,
                new String[] {eventid, entrantid},
                null,
                null,
                sortOrder
        );
    }



    // TALLY QUERY HELPERS

    private static final SQLiteQueryBuilder sTallyByTitleQueryBuilder;

    static{
        sTallyByTitleQueryBuilder = new SQLiteQueryBuilder();

        sTallyByTitleQueryBuilder.setTables(
                ScoreSheetContract.TallyEntry.TABLE_NAME);
    }

    private static final String sTallyTitle = ScoreSheetContract.TallyEntry.COLUMN_TITLE + " = ? ";

    private Cursor getTallyByTitle(Uri uri, String[] projection, String sortOrder) {
        String description = ScoreSheetContract.TallyEntry.getTallyTitleFromUri(uri);

        return sTallyByTitleQueryBuilder.query(mOpenHelper.getReadableDatabase(),
                projection,
                sTallyTitle,
                new String[] {description},
                null,
                null,
                sortOrder
        );
    }

    private static final SQLiteQueryBuilder sTallyByEventIdQueryBuilder;

    static{
        sTallyByEventIdQueryBuilder = new SQLiteQueryBuilder();

        sTallyByEventIdQueryBuilder.setTables(
                ScoreSheetContract.TallyEntry.TABLE_NAME);
    }

    private static final String sTallyEventId = ScoreSheetContract.TallyEntry.COLUMN_TITLE + " = ? ";

    private Cursor getTallyByEventId(Uri uri, String[] projection, String sortOrder) {
        String eventid = Long.valueOf(ScoreSheetContract.TallyEntry.getTallyEventIdFromUri(uri)).toString();

        return sTallyByEventIdQueryBuilder.query(mOpenHelper.getReadableDatabase(),
                projection,
                sTallyEventId,
                new String[] {eventid},
                null,
                null,
                sortOrder
        );
    }




    // EVENTENTRANTTALLY QUERY HELPERS

    private static final SQLiteQueryBuilder sEntrantsByEventIDTlyQueryBuilder;

    static{
        sEntrantsByEventIDTlyQueryBuilder = new SQLiteQueryBuilder();

        sEntrantsByEventIDTlyQueryBuilder.setTables(
                ScoreSheetContract.EntrantEntry.TABLE_NAME + ", " + ScoreSheetContract.EventEntrantTallyEntry.TABLE_NAME + " WHERE " + ScoreSheetContract.EntrantEntry.TABLE_NAME + "." + "_id" + " = " + ScoreSheetContract.EventEntrantTallyEntry.TABLE_NAME + "." + ScoreSheetContract.EventEntrantTallyEntry.COLUMN_ENTRANT_ID + " AND " + ScoreSheetContract.EventEntrantTallyEntry.TABLE_NAME + "." + ScoreSheetContract.EventEntrantTallyEntry.COLUMN_EVENT_ID + " = ?");
    }

    private static final String sEventIdEntrantIdsTly = null;

    private Cursor getEntrantsByEventEntrantTly(Uri uri, String[] projection, String sortOrder) {
        String eventid = Long.valueOf(ScoreSheetContract.EventEntry.getEventIdFromUri(uri)).toString();

        return sEntrantsByEventIDTlyQueryBuilder.query(mOpenHelper.getReadableDatabase(),
                projection,
                sEventIdEntrantIdsTly,
                new String[] {eventid},
                null,
                null,
                sortOrder
        );
    }

    private static final SQLiteQueryBuilder sEventEntrantByEntrantTlyQueryBuilder;

    static{
        sEventEntrantByEntrantTlyQueryBuilder = new SQLiteQueryBuilder();
        sEventEntrantByEntrantTlyQueryBuilder.setTables(
                ScoreSheetContract.EventEntrantTallyEntry.TABLE_NAME + " INNER JOIN " +
                        ScoreSheetContract.EntrantEntry.TABLE_NAME + "ON" +
                        ScoreSheetContract.EventEntrantTallyEntry.TABLE_NAME + "." +
                        ScoreSheetContract.EventEntrantTallyEntry.COLUMN_EVENT_ID + " = " +
                        ScoreSheetContract.EntrantEntry.TABLE_NAME + "." +
                        ScoreSheetContract.EntrantEntry._ID);
    }

    private static final String sEntrant_IDTly = ScoreSheetContract.EntrantEntry.TABLE_NAME + "." + ScoreSheetContract.EntrantEntry._ID + " =? ";

    private Cursor getEventEntrantIdTly(Uri uri, String[] projection, String sortOrder) {
        String entrantid = ScoreSheetContract.EventEntrantTallyEntry.getEventIdFromUri(uri);

        return sEventEntrantByEntrantTlyQueryBuilder.query(mOpenHelper.getReadableDatabase(),
                projection,
                sEntrant_IDTly,
                new String[] {entrantid},
                null,
                null,
                sortOrder
        );
    }



    private static final SQLiteQueryBuilder sIdEventEntrantTlyQueryBuilder;

    static{
        sIdEventEntrantTlyQueryBuilder = new SQLiteQueryBuilder();
        sIdEventEntrantQueryBuilder.setTables(
                ScoreSheetContract.EventEntrantTallyEntry.TABLE_NAME );
    }

    private static final String sIdEventEntrantTly = ScoreSheetContract.EventEntrantTallyEntry.TABLE_NAME + "." + ScoreSheetContract.EventEntrantTallyEntry.COLUMN_EVENT_ID + " = ? " + " AND " +
            ScoreSheetContract.EventEntrantTallyEntry.TABLE_NAME + "." + ScoreSheetContract.EventEntrantTallyEntry.COLUMN_ENTRANT_ID + " = ? ";

    private Cursor getIdEventEntrantTly(Uri uri, String[] projection, String sortOrder) {
        String eventid = ScoreSheetContract.EventEntrantTallyEntry.getEventIdFromUri(uri);
        String entrantid = ScoreSheetContract.EventEntrantTallyEntry.getEntrantIdWithEventIdFromUri(uri);
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

    private static final SQLiteQueryBuilder sIdEventEntrantTallyQueryBuilder;

    static{
        sIdEventEntrantTallyQueryBuilder = new SQLiteQueryBuilder();
        sIdEventEntrantTallyQueryBuilder.setTables(
                ScoreSheetContract.EventEntrantTallyEntry.TABLE_NAME );
    }

    private static final String sIdEventEntrantTally = ScoreSheetContract.EventEntrantTallyEntry.TABLE_NAME + "." + ScoreSheetContract.EventEntrantTallyEntry.COLUMN_EVENT_ID + " = ? " + " AND " +
            ScoreSheetContract.EventEntrantTallyEntry.TABLE_NAME + "." + ScoreSheetContract.EventEntrantTallyEntry.COLUMN_ENTRANT_ID + " = ? ";

    private Cursor getIdEventEntrantTally(Uri uri, String[] projection, String sortOrder) {
        String eventid = ScoreSheetContract.EventEntrantTallyEntry.getEventIdFromUri(uri);
        String entrantid = ScoreSheetContract.EventEntrantTallyEntry.getEntrantIdWithEventIdFromUri(uri);
        String tallyid = ScoreSheetContract.EventEntrantTallyEntry.getTallyIdWithEventIdEntrantIdFromUri(uri);
        String[] selectionArgs = {eventid, entrantid};
        return sIdEventEntrantTallyQueryBuilder.query(mOpenHelper.getReadableDatabase(),
                projection,
                sIdEventEntrantTally,
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
        // Contract to help define the types to the UriMatcher.
        matcher.addURI(authority, ScoreSheetContract.PATH_EVENT, EVENT);
        matcher.addURI(authority, ScoreSheetContract.PATH_EVENT + "/#", EVENT_ID);
        matcher.addURI(authority, ScoreSheetContract.PATH_EVENT + "/short_desc", EVENT_NAME);

        matcher.addURI(authority, ScoreSheetContract.PATH_ENTRANT, ENTRANT);
        matcher.addURI(authority, ScoreSheetContract.PATH_ENTRANT + "/team_desc", ENTRANT_WITH_FIRST_NAME);
        matcher.addURI(authority, ScoreSheetContract.PATH_ENTRANT + "/#", ENTRANT_WITH_EVENT_ID);
        matcher.addURI(authority, ScoreSheetContract.PATH_ENTRANT + "/#/checked", ENTRANT_WITH_EVENT_ID_CHECKED);

//        matcher.addURI(authority, ScoreSheetContract.PATH_EVENTENTRANT, EVENTENTRANT);
//        matcher.addURI(authority, ScoreSheetContract.PATH_EVENTENTRANT + "/#", EVENTENTRANT_WITH_EVENT_ID);
//        matcher.addURI(authority, ScoreSheetContract.PATH_EVENTENTRANT + "/#", EVENTENTRANT_WITH_ENTRANT_ID);

        matcher.addURI(authority, ScoreSheetContract.PATH_USER, USER);
        matcher.addURI(authority, ScoreSheetContract.PATH_USER + "/user_desc", USER_WITH_FIRST_NAME);
        matcher.addURI(authority, ScoreSheetContract.PATH_USER + "/#", USER_WITH_EVENT_ID);
        matcher.addURI(authority, ScoreSheetContract.PATH_USER + "/#/checked", USER_WITH_EVENT_ID_CHECKED);

        matcher.addURI(authority, ScoreSheetContract.PATH_EVENTUSER, EVENTUSER);
        matcher.addURI(authority, ScoreSheetContract.PATH_EVENTUSER + "/#/#", EVENTUSER_EVENTIDUSERID);

        matcher.addURI(authority, ScoreSheetContract.PATH_EVENTENTRANTSCORECARD, EVENTENTRANTSCORECARD);
        matcher.addURI(authority, ScoreSheetContract.PATH_EVENTENTRANTSCORECARD + "/#", EVENTENTRANTSCORECARD_WITH_EVENT_ID);
        matcher.addURI(authority, ScoreSheetContract.PATH_EVENTENTRANTSCORECARD + "/#", EVENTENTRANTSCORECARD_WITH_ENTRANT_ID);
        matcher.addURI(authority, ScoreSheetContract.PATH_EVENTENTRANTSCORECARD + "/#", EVENTENTRANTSCORECARD_WITH_SCORECARD_ID);
        matcher.addURI(authority, ScoreSheetContract.PATH_EVENTENTRANTSCORECARD + "/#/#", EVENTENTRANTSCORECARD_EVENTIDENTRANTID);
        matcher.addURI(authority, ScoreSheetContract.PATH_EVENTENTRANTSCORECARD + "/#/#/#", EVENTENTRANTSCORECARD_EVENTIDENTRANTIDSCORECARDID);

        matcher.addURI(authority, ScoreSheetContract.PATH_SCORECARD, SCORECARD);
        matcher.addURI(authority, ScoreSheetContract.PATH_SCORECARD + "/scorecard_desc", SCORECARD_WITH_ELEMENT);
        matcher.addURI(authority, ScoreSheetContract.PATH_SCORECARD + "/#", SCORECARD_WITH_EVENTID);
        matcher.addURI(authority, ScoreSheetContract.PATH_SCORECARD + "/#/#", SCORECARD_WITH_EVENTID_ENTRANTID);

        matcher.addURI(authority, ScoreSheetContract.PATH_TALLY, TALLY);
        matcher.addURI(authority, ScoreSheetContract.PATH_TALLY + "/tally_desc", TALLY_WITH_TITLE);
        matcher.addURI(authority, ScoreSheetContract.PATH_TALLY + "/#", TALLY_WITH_EVENT_ID);

        matcher.addURI(authority, ScoreSheetContract.PATH_EVENTENTRANTTALLY, EVENTENTRANTTALLY);
        matcher.addURI(authority, ScoreSheetContract.PATH_EVENTENTRANTTALLY + "/#", EVENTENTRANTTALLY_WITH_EVENT_ID);
        matcher.addURI(authority, ScoreSheetContract.PATH_EVENTENTRANTTALLY + "/#", EVENTENTRANTTALLY_WITH_ENTRANT_ID);
        matcher.addURI(authority, ScoreSheetContract.PATH_EVENTENTRANTTALLY + "/#", EVENTENTRANTTALLY_WITH_TALLY_ID);
        matcher.addURI(authority, ScoreSheetContract.PATH_EVENTENTRANTTALLY + "/#/#", EVENTENTRANTTALLY_EVENTIDENTRANTID);
        matcher.addURI(authority, ScoreSheetContract.PATH_EVENTENTRANTTALLY + "/#/#/#", EVENTENTRANTTALLY_EVENTIDENTRANTIDTALLYID);

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
            case EVENT_NAME:
                return ScoreSheetContract.EventEntry.CONTENT_TYPE;

//            case EVENTENTRANT:
//                return ScoreSheetContract.EventEntrantEntry.CONTENT_TYPE;
//            case EVENTENTRANT_WITH_EVENT_ID:
//                return ScoreSheetContract.EventEntrantEntry.CONTENT_TYPE;
//            case EVENTENTRANT_WITH_ENTRANT_ID:
//                return ScoreSheetContract.EventEntrantEntry.CONTENT_TYPE;

            case ENTRANT:
                return ScoreSheetContract.EntrantEntry.CONTENT_TYPE;
            case ENTRANT_WITH_FIRST_NAME:
                return ScoreSheetContract.EntrantEntry.CONTENT_TYPE;
            case ENTRANT_WITH_EVENT_ID:
                return ScoreSheetContract.EntrantEntry.CONTENT_TYPE;
            case ENTRANT_WITH_EVENT_ID_CHECKED:
                return ScoreSheetContract.EntrantEntry.CONTENT_TYPE;

            case USER:
                return ScoreSheetContract.UserEntry.CONTENT_TYPE;
            case USER_WITH_FIRST_NAME:
                return ScoreSheetContract.UserEntry.CONTENT_TYPE;
            case USER_WITH_EVENT_ID:
                return ScoreSheetContract.UserEntry.CONTENT_TYPE;
            case USER_WITH_EVENT_ID_CHECKED:
                return ScoreSheetContract.UserEntry.CONTENT_TYPE;

            case EVENTUSER:
                return ScoreSheetContract.EventUserEntry.CONTENT_TYPE;
            case EVENTUSER_WITH_EVENT_ID:
                return ScoreSheetContract.EventUserEntry.CONTENT_TYPE;
            case EVENTUSER_EVENTIDUSERID:
                return ScoreSheetContract.EventUserEntry.CONTENT_TYPE;

            case EVENTENTRANTSCORECARD:
                return ScoreSheetContract.EventEntrantScorecardEntry.CONTENT_TYPE;
            case EVENTENTRANTSCORECARD_WITH_EVENT_ID:
                return ScoreSheetContract.EventEntrantScorecardEntry.CONTENT_TYPE;
            case EVENTENTRANTSCORECARD_WITH_ENTRANT_ID:
                return ScoreSheetContract.EventEntrantScorecardEntry.CONTENT_TYPE;
            case EVENTENTRANTSCORECARD_WITH_SCORECARD_ID:
                return ScoreSheetContract.EventEntrantScorecardEntry.CONTENT_TYPE;
            case EVENTENTRANTSCORECARD_EVENTIDENTRANTID:
                return ScoreSheetContract.EventEntrantScorecardEntry.CONTENT_TYPE;
            case EVENTENTRANTSCORECARD_EVENTIDENTRANTIDSCORECARDID:
                return ScoreSheetContract.EventEntrantScorecardEntry.CONTENT_TYPE;

            case SCORECARD:
                return ScoreSheetContract.ScorecardEntry.CONTENT_TYPE;
            case SCORECARD_WITH_ELEMENT:
                return ScoreSheetContract.ScorecardEntry.CONTENT_TYPE;
            case SCORECARD_WITH_EVENTID:
                return ScoreSheetContract.ScorecardEntry.CONTENT_TYPE;
            case SCORECARD_WITH_EVENTID_ENTRANTID:
                return ScoreSheetContract.ScorecardEntry.CONTENT_TYPE;

            case TALLY:
                return ScoreSheetContract.TallyEntry.CONTENT_TYPE;
            case TALLY_WITH_TITLE:
                return ScoreSheetContract.TallyEntry.CONTENT_TYPE;
            case TALLY_WITH_EVENT_ID:
                return ScoreSheetContract.TallyEntry.CONTENT_TYPE;

            case EVENTENTRANTTALLY:
                return ScoreSheetContract.TallyEntry.CONTENT_TYPE;
            case EVENTENTRANTTALLY_WITH_EVENT_ID:
                return ScoreSheetContract.EventEntrantTallyEntry.CONTENT_TYPE;
            case EVENTENTRANTTALLY_WITH_ENTRANT_ID:
                return ScoreSheetContract.EventEntrantTallyEntry.CONTENT_TYPE;
            case EVENTENTRANTTALLY_WITH_TALLY_ID:
                return ScoreSheetContract.EventEntrantTallyEntry.CONTENT_TYPE;
            case EVENTENTRANTTALLY_EVENTIDENTRANTID:
                return ScoreSheetContract.EventEntrantTallyEntry.CONTENT_TYPE;
            case EVENTENTRANTTALLY_EVENTIDENTRANTIDTALLYID:
                return ScoreSheetContract.EventEntrantTallyEntry.CONTENT_TYPE;

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
                retCursor = getEventByIDforName(uri, projection, sortOrder);
                break;
            }
            case EVENT_NAME: {
                retCursor = getEventByName(uri, projection, sortOrder);
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

            case ENTRANT_WITH_FIRST_NAME: {
                retCursor = getEntrantByFirstName(uri, projection, sortOrder);
                break;
            }
            case ENTRANT_WITH_EVENT_ID: {
                retCursor = getEntrantsByEventEntrantTly(uri, projection, sortOrder);
                break;
            }
            case ENTRANT_WITH_EVENT_ID_CHECKED:{
                retCursor = getEntrantsByEventEntrantTly(uri, projection, sortOrder);
                break;
            }



//            case EVENTENTRANT: {
//
//                retCursor = mOpenHelper.getReadableDatabase().query(
//                        ScoreSheetContract.EventEntrantEntry.TABLE_NAME,
//                        projection,
//                        selection,
//                        selectionArgs,
//                        null,
//                        null,
//                        sortOrder
//                );
//
//                break;
//            }
//
//            case EVENTENTRANT_WITH_EVENT_ID: {
//                retCursor = getIdEventEntrant(uri, projection, sortOrder);
//                break;
//            }
//            case EVENTENTRANT_WITH_ENTRANT_ID: {
//                retCursor = getIdEventEntrant(uri, projection, sortOrder);
//                break;
//            }



            case USER: {

                retCursor = mOpenHelper.getReadableDatabase().query(
                        ScoreSheetContract.UserEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );

                break;
            }
            case USER_WITH_FIRST_NAME: {
                retCursor = getUserByFirstName(uri, projection, sortOrder);
                break;
            }
            case USER_WITH_EVENT_ID: {
                retCursor = getUsersByEventUser(uri, projection, sortOrder);
                break;
            }
            case USER_WITH_EVENT_ID_CHECKED:{
                retCursor = getUsersByEventUser(uri, projection, sortOrder);
                break;
            }



            case EVENTUSER: {

                retCursor = mOpenHelper.getReadableDatabase().query(
                        ScoreSheetContract.EventUserEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );

                break;
            }
            case EVENTUSER_EVENTIDUSERID: {
                retCursor = getIdEventUser(uri, projection, sortOrder);
                break;
            }



            case EVENTENTRANTSCORECARD: {

                retCursor = mOpenHelper.getReadableDatabase().query(
                        ScoreSheetContract.EventEntrantScorecardEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );

                break;
            }


            case EVENTENTRANTSCORECARD_EVENTIDENTRANTID: {
                retCursor = getIdEventEntrant(uri, projection, sortOrder);
                break;
            }
            case EVENTENTRANTSCORECARD_EVENTIDENTRANTIDSCORECARDID: {
                retCursor = getIdEventEntrantScorecard(uri, projection, sortOrder);
                break;
            }



            case SCORECARD: {

                retCursor = mOpenHelper.getReadableDatabase().query(
                        ScoreSheetContract.ScorecardEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );

                break;
            }

            case SCORECARD_WITH_ELEMENT: {
                retCursor = getScorecardByElement(uri, projection, sortOrder);
                break;
            }
            case SCORECARD_WITH_EVENTID: {
                retCursor = getScorecardByEventId(uri, projection, sortOrder);
                break;
            }
            case SCORECARD_WITH_EVENTID_ENTRANTID: {
                retCursor = getScorecardsByEventEntrant(uri, projection, sortOrder);
                break;
            }


            case TALLY: {
                retCursor = mOpenHelper.getReadableDatabase().query(
                        ScoreSheetContract.TallyEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );

                break;
            }
            case TALLY_WITH_TITLE: {
                retCursor = getTallyByTitle(uri, projection, sortOrder);
                break;
            }
            case TALLY_WITH_EVENT_ID: {
                retCursor = getTallyByEventId(uri, projection, sortOrder);
                break;
            }



            case EVENTENTRANTTALLY: {
                retCursor = mOpenHelper.getReadableDatabase().query(
                        ScoreSheetContract.EventEntrantTallyEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );

                break;
            }
            case EVENTENTRANTTALLY_EVENTIDENTRANTID: {
                retCursor = getIdEventEntrantTly(uri, projection, sortOrder);
                break;
            }
            case EVENTENTRANTTALLY_EVENTIDENTRANTIDTALLYID: {
                retCursor = getIdEventEntrantTally(uri, projection, sortOrder);
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
            case EVENT_NAME: {
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
            case ENTRANT_WITH_FIRST_NAME: {
                long _id = db.insert(ScoreSheetContract.EntrantEntry.TABLE_NAME, null, values);
                if ( _id > 0 )
                    returnUri = ScoreSheetContract.EntrantEntry.buildEntrantIdUri(_id);
                else
                    throw new android.database.SQLException("Failed to insert row into " + uri);
                break;
            }

            case USER: {
                long _id = db.insert(ScoreSheetContract.UserEntry.TABLE_NAME, null, values);
                if ( _id > 0 )
                    returnUri = ScoreSheetContract.UserEntry.buildUserIdUri(_id);
                else
                    throw new android.database.SQLException("Failed to insert row into " + uri);
                break;
            }
            case USER_WITH_FIRST_NAME: {
                long _id = db.insert(ScoreSheetContract.UserEntry.TABLE_NAME, null, values);
                if ( _id > 0 )
                    returnUri = ScoreSheetContract.UserEntry.buildUserIdUri(_id);
                else
                    throw new android.database.SQLException("Failed to insert row into " + uri);
                break;
            }

            case EVENTUSER: {
                long _id = db.insert(ScoreSheetContract.EventUserEntry.TABLE_NAME, null, values);
                if ( _id > 0 )
                    returnUri = ScoreSheetContract.EventUserEntry.buildIdEventUserUri(_id);
                else
                    throw new android.database.SQLException("Failed to insert row into " + uri);
                break;
            }

            case EVENTENTRANTSCORECARD: {
                long _id = db.insert(ScoreSheetContract.EventEntrantScorecardEntry.TABLE_NAME, null, values);
                if ( _id > 0 )
                    returnUri = ScoreSheetContract.EventEntrantScorecardEntry.buildIdEventEntrantScorecardUri(_id);
                else
                    throw new android.database.SQLException("Failed to insert row into " + uri);
                break;
            }

            case SCORECARD: {
                long _id = db.insert(ScoreSheetContract.ScorecardEntry.TABLE_NAME, null, values);
                if ( _id > 0 )
                    returnUri = ScoreSheetContract.ScorecardEntry.buildScorecardIdUri(_id);
                else
                    throw new android.database.SQLException("Failed to insert row into " + uri);
                break;
            }
            case SCORECARD_WITH_ELEMENT: {
                long _id = db.insert(ScoreSheetContract.ScorecardEntry.TABLE_NAME, null, values);
                if ( _id > 0 )
                    returnUri = ScoreSheetContract.ScorecardEntry.buildScorecardIdUri(_id);
                else
                    throw new android.database.SQLException("Failed to insert row into " + uri);
                break;
            }

            case TALLY: {
                long _id = db.insert(ScoreSheetContract.TallyEntry.TABLE_NAME, null, values);
                if ( _id > 0 )
                    returnUri = ScoreSheetContract.TallyEntry.buildTallyIdUri(_id);
                else
                    throw new android.database.SQLException("Failed to insert row into " + uri);
                break;
            }
            case TALLY_WITH_TITLE: {
                long _id = db.insert(ScoreSheetContract.TallyEntry.TABLE_NAME, null, values);
                if ( _id > 0 )
                    returnUri = ScoreSheetContract.TallyEntry.buildTallyIdUri(_id);
                else
                    throw new android.database.SQLException("Failed to insert row into " + uri);
                break;
            }

            case EVENTENTRANTTALLY: {
                long _id = db.insert(ScoreSheetContract.EventEntrantTallyEntry.TABLE_NAME, null, values);
                if ( _id > 0 )
                    returnUri = ScoreSheetContract.EventEntrantTallyEntry.buildIdEventEntrantTallyUri(_id);
                else
                    throw new android.database.SQLException("Failed to insert row into " + uri);
                break;
            }

            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        getContext().getContentResolver().notifyChange(uri, null);

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
            case EVENT_NAME: {
                rowsDeleted = db.delete(ScoreSheetContract.EventEntry.TABLE_NAME, selection, selectionArgs);
                break;
            }

            case ENTRANT: {
                rowsDeleted = db.delete(ScoreSheetContract.EntrantEntry.TABLE_NAME, selection, selectionArgs);
                break;
            }
            case ENTRANT_WITH_FIRST_NAME: {
                rowsDeleted = db.delete(ScoreSheetContract.EntrantEntry.TABLE_NAME, selection, selectionArgs);
                break;
            }

            case USER: {
                rowsDeleted = db.delete(ScoreSheetContract.UserEntry.TABLE_NAME, selection, selectionArgs);
                break;
            }
            case USER_WITH_FIRST_NAME: {
                rowsDeleted = db.delete(ScoreSheetContract.UserEntry.TABLE_NAME, selection, selectionArgs);
                break;
            }

            case EVENTUSER: {
                rowsDeleted = db.delete(ScoreSheetContract.EventUserEntry.TABLE_NAME, selection, selectionArgs);
                break;
            }

            case EVENTENTRANTSCORECARD: {
                rowsDeleted = db.delete(ScoreSheetContract.EventEntrantScorecardEntry.TABLE_NAME, selection, selectionArgs);
                break;
            }
//            case EVENTENTRANTSCORECARD_WITH_EVENT_ID: {
//                rowsDeleted = db.delete(ScoreSheetContract.EventEntrantScorecardEntry.TABLE_NAME, selection, selectionArgs);
//                break;
//            }
//            case EVENTENTRANTSCORECARD_WITH_ENTRANT_ID: {
//                rowsDeleted = db.delete(ScoreSheetContract.EventEntrantScorecardEntry.TABLE_NAME, selection, selectionArgs);
//                break;
//            }

            case SCORECARD: {
                rowsDeleted = db.delete(ScoreSheetContract.ScorecardEntry.TABLE_NAME, selection, selectionArgs);
                break;
            }
            case SCORECARD_WITH_ELEMENT: {
                rowsDeleted = db.delete(ScoreSheetContract.ScorecardEntry.TABLE_NAME, selection, selectionArgs);
                break;
            }

            case TALLY: {
                rowsDeleted = db.delete(ScoreSheetContract.TallyEntry.TABLE_NAME, selection, selectionArgs);
                break;
            }
            case TALLY_WITH_TITLE: {
                rowsDeleted = db.delete(ScoreSheetContract.TallyEntry.TABLE_NAME, selection, selectionArgs);
                break;
            }

            case EVENTENTRANTTALLY: {
                rowsDeleted = db.delete(ScoreSheetContract.EventEntrantTallyEntry.TABLE_NAME, selection, selectionArgs);
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
            case EVENT_NAME: {
                rowsUpdated = db.update(ScoreSheetContract.EventEntry.TABLE_NAME, values, selection, selectionArgs);
//                db.execSQL("UPDATE event SET " + selection + "=" + values + " WHERE " + );

                break;
            }

            case ENTRANT: {
                rowsUpdated = db.update(ScoreSheetContract.EntrantEntry.TABLE_NAME, values, selection, selectionArgs);
                break;
            }
            case ENTRANT_WITH_FIRST_NAME: {
                rowsUpdated = db.update(ScoreSheetContract.EntrantEntry.TABLE_NAME, values, selection, selectionArgs);
                break;
            }

            case USER: {
                rowsUpdated = db.update(ScoreSheetContract.UserEntry.TABLE_NAME, values, selection, selectionArgs);
                break;
            }
            case USER_WITH_FIRST_NAME: {
                rowsUpdated = db.update(ScoreSheetContract.UserEntry.TABLE_NAME, values, selection, selectionArgs);
                break;
            }

            case EVENTUSER: {
                rowsUpdated = db.update(ScoreSheetContract.EventUserEntry.TABLE_NAME, values, selection, selectionArgs);
                break;
            }

            case EVENTENTRANTSCORECARD: {
                rowsUpdated = db.update(ScoreSheetContract.EventEntrantScorecardEntry.TABLE_NAME, values, selection, selectionArgs);
                break;
            }

            case SCORECARD: {
                rowsUpdated = db.update(ScoreSheetContract.ScorecardEntry.TABLE_NAME, values, selection, selectionArgs);
                break;
            }
            case SCORECARD_WITH_ELEMENT: {
                rowsUpdated = db.update(ScoreSheetContract.ScorecardEntry.TABLE_NAME, values, selection, selectionArgs);
                break;
            }

            case TALLY: {
                rowsUpdated = db.update(ScoreSheetContract.TallyEntry.TABLE_NAME, values, selection, selectionArgs);
                break;
            }
            case TALLY_WITH_TITLE: {
                rowsUpdated = db.update(ScoreSheetContract.TallyEntry.TABLE_NAME, values, selection, selectionArgs);
                break;
            }

            case EVENTENTRANTTALLY: {
                rowsUpdated = db.update(ScoreSheetContract.EventEntrantTallyEntry.TABLE_NAME, values, selection, selectionArgs);
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
            case USER:
                db.beginTransaction();
                returnCount = 0;
                try {
                    for (ContentValues value : values) {
                        long _id = db.insert(ScoreSheetContract.UserEntry.TABLE_NAME, null, value);
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
            case EVENTUSER:
                db.beginTransaction();
                returnCount = 0;
                try {
                    for (ContentValues value : values) {
                        long _id = db.insert(ScoreSheetContract.EventUserEntry.TABLE_NAME, null, value);
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
            case EVENTENTRANTSCORECARD:
                db.beginTransaction();
                returnCount = 0;
                try {
                    for (ContentValues value : values) {
                        long _id = db.insert(ScoreSheetContract.EventEntrantScorecardEntry.TABLE_NAME, null, value);
                        if (_id != -1) {
                            returnCount++;
                        }
                    }
                    db.setTransactionSuccessful();
                } finally {
                    db.endTransaction();
                }
                getContext().getContentResolver().notifyChange(uri, null);
            case SCORECARD:
                db.beginTransaction();
                returnCount = 0;
                try {
                    for (ContentValues value : values) {
                        long _id = db.insert(ScoreSheetContract.ScorecardEntry.TABLE_NAME, null, value);
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
            case TALLY:
                db.beginTransaction();
                returnCount = 0;
                try {
                    for (ContentValues value : values) {
                        long _id = db.insert(ScoreSheetContract.TallyEntry.TABLE_NAME, null, value);
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
            case EVENTENTRANTTALLY:
                db.beginTransaction();
                returnCount = 0;
                try {
                    for (ContentValues value : values) {
                        long _id = db.insert(ScoreSheetContract.EventEntrantTallyEntry.TABLE_NAME, null, value);
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
