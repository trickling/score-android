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

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;


public final class ScoreSheetContract {

    private ScoreSheetContract() {}

    public static final String CONTENT_AUTHORITY = "com.example.android.scoresheet.app";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String PATH_EVENT = "event";
    public static final String PATH_ENTRANT = "entrant";
    public static final String PATH_USER = "user";
    public static final String PATH_EVENTUSER = "eventuser";
    public static final String PATH_EVENTENTRANTSCORECARD = "evententrantscorecard";
    public static final String PATH_SCORECARD = "scorecard";
    public static final String PATH_TALLY = "tally";




    public static final class EventEntry implements BaseColumns {

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_EVENT).build();

        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_EVENT;
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_EVENT;

        public static final String TABLE_NAME = "event";

        public static final String COLUMN_SHORT_DESC = "short_desc";

        public static Uri buildEventDescUri(String short_desc) {
            return CONTENT_URI.buildUpon().appendPath(short_desc).build();
        }

        public static Uri buildEventidUri(String _id) {
            return CONTENT_URI.buildUpon().appendPath(_id).build();
        }

        public static Uri buildEventUri() {
            return CONTENT_URI;
        }

        public static Uri buildEventIdUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

        public static Uri buildEventIdDescUri(long id, String short_desc) {
            return ContentUris.withAppendedId(CONTENT_URI, id).buildUpon().appendPath(short_desc).build();
        }

        public static String getEventDescriptionFromUri(Uri uri) {
            return uri.getPathSegments().get(1);
        }

        public static String getEventIdDescriptionFromUri(Uri uri) {
            return uri.getPathSegments().get(2);
        }

        public static long getEventIdFromUri(Uri uri) {
            return Long.parseLong(uri.getPathSegments().get(1));
        }

    }



    public static final class EntrantEntry implements BaseColumns {

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_ENTRANT).build();

        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_ENTRANT;
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_ENTRANT;

        public static final String TABLE_NAME = "entrant";

        public static final String COLUMN_TEAM_DESC = "team_descr";

        public static Uri buildEntrantDescUri(String team_desc) {
            return CONTENT_URI.buildUpon().appendPath(team_desc).build();
        }

        public static Uri buildEntrantUri() {
            return CONTENT_URI;
        }

        public static Uri buildEntrantidUri(String _id) {
            return CONTENT_URI.buildUpon().appendPath(_id).build();
        }

        public static Uri buildEntrantIdUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

        public static Uri buildEntrantEventIdCheckedUri(long id, String path){
            return ContentUris.withAppendedId(CONTENT_URI, id).buildUpon().appendPath(path).build();
        }

        public static String getEntrantDescFromUri(Uri uri) {
            return uri.getPathSegments().get(1);
        }

        public static long getEntrantIdFromUri(Uri uri){
            return Long.parseLong(uri.getPathSegments().get(1));
        }

        public static String getEntrantEventIdFromUri(Uri uri){
            return uri.getPathSegments().get(2);
        }
    }



    public static final class UserEntry implements BaseColumns {

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_USER).build();

        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_USER;
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_USER;

        public static final String TABLE_NAME = "user";

        public static final String COLUMN_USER_DESC = "user_descr";

        public static Uri buildUserDescUri(String user_desc) {
            return CONTENT_URI.buildUpon().appendPath(user_desc).build();
        }

        public static Uri buildUserUri() {
            return CONTENT_URI;
        }

        public static Uri buildUseridUri(String _id) {
            return CONTENT_URI.buildUpon().appendPath(_id).build();
        }

        public static Uri buildUserIdUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

        public static Uri buildUserEventIdCheckedUri(long id, String path){
            return ContentUris.withAppendedId(CONTENT_URI, id).buildUpon().appendPath(path).build();
        }

        public static String getUserDescFromUri(Uri uri) {
            return uri.getPathSegments().get(1);
        }

        public static long getUserIdFromUri(Uri uri){
            return Long.parseLong(uri.getPathSegments().get(1));
        }

        public static String getUserEventIdFromUri(Uri uri){
            return uri.getPathSegments().get(2);
        }
    }



    public static final class EventUserEntry implements BaseColumns {

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_EVENTUSER).build();

        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_EVENTUSER;
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_EVENTUSER;

        public static final String TABLE_NAME = "eventuser";

        public static final String COLUMN_EVENT_ID= "event_id";
        public static final String COLUMN_USER_ID = "user_id";

        public static Uri buildEventUserUri() {
            return CONTENT_URI;
        }

        public static Uri buildEventUserIdUri(String entrant_id) {
            return CONTENT_URI.buildUpon().appendPath(entrant_id).build();
        }

        public static Uri buildEventIdUserUri(String event_id) {
            return CONTENT_URI.buildUpon().appendPath(event_id).build();
        }

        public static Uri buildIdEventUser(String eventid, String entrantid){
            return CONTENT_URI.buildUpon().appendPath(eventid).appendPath(entrantid).build();
        }

        public static Uri buildIdEventUserUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

        public static String getUserIdFromUri(Uri uri) {
            return uri.getPathSegments().get(1);
        }

        public static String getEventIdFromUri(Uri uri){
            return uri.getPathSegments().get(1);
        }

        public static String getUserIdWithEventIdFromUri(Uri uri){
            return uri.getPathSegments().get(2);
        }
    }



    public static final class EventEntrantScorecardEntry implements BaseColumns {

        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_EVENTENTRANTSCORECARD).build();

        public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_EVENTENTRANTSCORECARD;
        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_EVENTENTRANTSCORECARD;

        public static final String TABLE_NAME = "evententrantscorecard";

        public static final String COLUMN_EVENT_ID= "event_id";
        public static final String COLUMN_ENTRANT_ID = "entrant_id";
        public static final String COLUMN_SCORECARD_ID = "scorecard_id";

        public static Uri buildEventEntrantScorecardUri() {
            return CONTENT_URI;
        }

        public static Uri buildEventEntrantIdScorecardUri(String entrant_id) {
            return CONTENT_URI.buildUpon().appendPath(entrant_id).build();
        }

        public static Uri buildEventIdEntrantScorecardUri(String event_id) {
            return CONTENT_URI.buildUpon().appendPath(event_id).build();
        }

        public static Uri buildEventEntrantScorecardIdUri(String scorecard_id) {
            return CONTENT_URI.buildUpon().appendPath(scorecard_id).build();
        }

        public static Uri buildIdEventEntrantScorecard(String eventid, String entrantid){
            return CONTENT_URI.buildUpon().appendPath(eventid).appendPath(entrantid).build();
        }

        public static Uri buildEventEntrantScorecardEvEnSc(String eventid, String entrantid, String scorecardid){
            return CONTENT_URI.buildUpon().appendPath(eventid).appendPath(entrantid).appendPath(scorecardid).build();
        }

        public static Uri buildIdEventEntrantScorecardUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

        public static String getEntrantIdFromUri(Uri uri) {
            return uri.getPathSegments().get(1);
        }

        public static String getEventIdFromUri(Uri uri){
            return uri.getPathSegments().get(1);
        }

        public static String getEntrantIdWithEventIdFromUri(Uri uri){
            return uri.getPathSegments().get(2);
        }

        public static String getScorecardIdWithEventIdEntrantIdFromUri(Uri uri){
            return uri.getPathSegments().get(3);
        }
    }



    public static final class ScorecardEntry implements BaseColumns {

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_SCORECARD).build();

        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_SCORECARD;
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_SCORECARD;

        public static final String TABLE_NAME = "scorecard";

        public static final String COLUMN_SCORECARD_DESC = "scorecard_desc";

        public static Uri buildScorecardDescUri(String scorecard_desc) {
            return CONTENT_URI.buildUpon().appendPath(scorecard_desc).build();
        }

        public static Uri buildScorecardUri() {
            return CONTENT_URI;
        }

        public static Uri buildScorecardidUri(String _id) {
            return CONTENT_URI.buildUpon().appendPath(_id).build();
        }

        public static Uri buildScorecardIdUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

        public static Uri buildEventIdCheckedUri(long id, String path){
            return ContentUris.withAppendedId(CONTENT_URI, id).buildUpon().appendPath(path).build();
        }

        public static String getScorecardDescFromUri(Uri uri) {
            return uri.getPathSegments().get(1);
        }

        public static long getScorecardIdFromUri(Uri uri){
            return Long.parseLong(uri.getPathSegments().get(1));
        }

        public static String getScorecardEventIdFromUri(Uri uri){
            return uri.getPathSegments().get(2);
        }
        public static String getScorecardEventIdEntrantIdFromUri(Uri uri){
            return uri.getPathSegments().get(3);
        }
    }



    public static final class TallyEntry implements BaseColumns {

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_TALLY).build();

        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_TALLY;
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_TALLY;

        public static final String TABLE_NAME = "tally";

        public static final String COLUMN_TALLY_DESC = "tally_descr";

        public static final String COLUMN_EVENTID = "event_id";

        public static Uri buildTallyDescUri(String tally_desc) {
            return CONTENT_URI.buildUpon().appendPath(tally_desc).build();
        }

        public static Uri buildTallyUri() {
            return CONTENT_URI;
        }

        public static Uri buildTallyidUri(String _id) {
            return CONTENT_URI.buildUpon().appendPath(_id).build();
        }

        public static Uri buildTallyIdUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

        public static Uri buildTallyEventIdCheckedUri(long id, String path){
            return ContentUris.withAppendedId(CONTENT_URI, id).buildUpon().appendPath(path).build();
        }

            public static String getTallyDescFromUri(Uri uri) {
            return uri.getPathSegments().get(1);
        }

        public static long getTallyIdFromUri(Uri uri){
            return Long.parseLong(uri.getPathSegments().get(1));
        }

        public static long getTallyEventIdFromUri(Uri uri){
            return Long.parseLong(uri.getPathSegments().get(1));
        }
    }
}
