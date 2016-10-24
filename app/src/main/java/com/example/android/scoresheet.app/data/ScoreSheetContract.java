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
//    public static final String PATH_EVENTENTRANT = "evententrant";
    public static final String PATH_ENTRANT = "entrant";
    public static final String PATH_USER = "user";
    public static final String PATH_EVENTUSER = "eventuser";
    public static final String PATH_EVENTENTRANTSCORECARD = "evententrantscorecard";
    public static final String PATH_SCORECARD = "scorecard";
    public static final String PATH_TALLY = "tally";
    public static final String PATH_EVENTENTRANTTALLY = "evententranttally";



    public static final class EventEntry implements BaseColumns {

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_EVENT).build();

        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_EVENT;
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_EVENT;

        public static final String TABLE_NAME = "event";

        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_LOCATION = "location";
        public static final String COLUMN_DATE = "date";
        public static final String COLUMN_HOST = "host";
        public static final String COLUMN_STATUS = "status";
        public static final String COLUMN_DIVISION = "division";
        public static final String COLUMN_INT_SEARCH_AREAS = "int_search_areas";
        public static final String COLUMN_EXT_SEARCH_AREAS = "ext_search_areas";
        public static final String COLUMN_CONT_SEARCH_AREAS = "cont_search_areas";
        public static final String COLUMN_VEH_SEARCH_AREAS = "veh_search_areas";
        public static final String COLUMN_ELITE_SEARCH_AREAS = "elite_search_areas";
        public static final String COLUMN_INT_HIDES = "int_hides";
        public static final String COLUMN_EXT_HIDES = "ext_hides";
        public static final String COLUMN_CONT_HIDES = "cont_hides";
        public static final String COLUMN_VEH_HIDES = "veh_hides";
        public static final String COLUMN_ELITE_HIDES = "elite_hides";

        public static Uri buildEventNameUri(String name) {
            return CONTENT_URI.buildUpon().appendPath(name).build();
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

        public static Uri buildEventIdNameUri(long id, String name) {
            return ContentUris.withAppendedId(CONTENT_URI, id).buildUpon().appendPath(name).build();
        }

        public static String getEventNameFromUri(Uri uri) {
            return uri.getPathSegments().get(1);
        }

        public static String getEventIdNameFromUri(Uri uri) {
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

        public static final String COLUMN_FIRST_NAME = "first_name";
        public static final String COLUMN_LAST_NAME = "last_name";
        public static final String COLUMN_ID_NUMBER = "id_number";
        public static final String COLUMN_DOG_NAME = "dog_name";
        public static final String COLUMN_DOG_ID_NUMBER = "dog_id_number";
        public static final String COLUMN_BREED = "breed";

        public static Uri buildEntrantFirstNameUri(String first_name) {
            return CONTENT_URI.buildUpon().appendPath(first_name).build();
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

        public static String getEntrantFirstNameFromUri(Uri uri) {
            return uri.getPathSegments().get(1);
        }

        public static long getEntrantIdFromUri(Uri uri){
            return Long.parseLong(uri.getPathSegments().get(1));
        }

        public static String getEntrantEventIdFromUri(Uri uri){
            return uri.getPathSegments().get(2);
        }
    }



//    public static final class EventEntrantEntry implements BaseColumns {
//
//        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_EVENTENTRANT).build();
//
//        public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_EVENTENTRANT;
//        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_EVENTENTRANT;
//
//        public static final String TABLE_NAME = "evententrant";
//
//        public static final String COLUMN_EVENT_ID= "event_id";
//        public static final String COLUMN_ENTRANT_ID = "entrant_id";
//
//        public static Uri buildEventEntrantUri() {
//            return CONTENT_URI;
//        }
//
//        public static Uri buildEventEntrantIdUri(String entrant_id) {
//            return CONTENT_URI.buildUpon().appendPath(entrant_id).build();
//        }
//
//        public static Uri buildEventIdEntrantUri(String event_id) {
//            return CONTENT_URI.buildUpon().appendPath(event_id).build();
//        }
//
//        public static Uri buildIdEventEntrantUri(long id) {
//            return ContentUris.withAppendedId(CONTENT_URI, id);
//        }
//
//        public static String getEntrantIdFromUri(Uri uri) {
//            return uri.getPathSegments().get(1);
//        }
//
//        public static String getEventIdFromUri(Uri uri){
//            return uri.getPathSegments().get(1);
//        }
//
//        public static String getEntrantIdWithEventIdFromUri(Uri uri){
//            return uri.getPathSegments().get(2);
//        }
//    }



    public static final class UserEntry implements BaseColumns {

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_USER).build();

        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_USER;
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_USER;

        public static final String TABLE_NAME = "user";

        public static final String COLUMN_FIRST_NAME = "first_name";
        public static final String COLUMN_LAST_NAME = "last_name";
        public static final String COLUMN_ROLE = "role";
        public static final String COLUMN_APPROVED = "approved";
        public static final String COLUMN_STATUS = "status";
        public static final String COLUMN_EMAIL = "email";
        public static final String COLUMN_PASSWORD = "password";

        public static Uri buildUserFirstNameUri(String first_name) {
            return CONTENT_URI.buildUpon().appendPath(first_name).build();
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

        public static String getUserFirstNameFromUri(Uri uri) {
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

        public static final String COLUMN_ELEMENT = "element";
        public static final String COLUMN_MAXTIME_M = "maxtime_m";
        public static final String COLUMN_MAXTIME_S = "maxtime_s";
        public static final String COLUMN_FINISH_CALL = "finish_call";
        public static final String COLUMN_FALSE_ALERT_FRINGE = "false_alert_fringe";
        public static final String COLUMN_TIMED_OUT = "timed_out";
        public static final String COLUMN_DISMISSED = "dismissed";
        public static final String COLUMN_EXCUSED = "excused";
        public static final String COLUMN_ABSENT = "absent";
        public static final String COLUMN_ELIMINATED_DURING_SEARCH = "eliminated_during_search";
        public static final String COLUMN_OTHER_FAULTS_DESCR = "other_faults_descr";
        public static final String COLUMN_OTHER_FAULTS_COUNT = "other_faults_count";
        public static final String COLUMN_COMMENTS = "comments";
        public static final String COLUMN_TOTAL_TIME = "total_time";
        public static final String COLUMN_PRONOUNCED = "pronounced";
        public static final String COLUMN_JUDGE_SIGNATURE = "judge_signature";
        public static final String COLUMN_SEARCH_AREA = "search_area";
        public static final String COLUMN_HIDES_MAX = "hides_max";
        public static final String COLUMN_HIDES_FOUND = "hides_found";
        public static final String COLUMN_HIDES_MISSED = "hides_missed";
        public static final String COLUMN_TOTAL_FAULTS = "total_faults";
        public static final String COLUMN_MAXPOINT = "maxpoint";
        public static final String COLUMN_TOTAL_POINTS = "total_points";

        public static Uri buildScorecardElementUri(String element) {
            return CONTENT_URI.buildUpon().appendPath(element).build();
        }

        public static Uri buildScorecardUri() {
            return CONTENT_URI;
        }

        public static Uri buildScorecardidUri(String _id) {
            return CONTENT_URI.buildUpon().appendPath(_id).build();
        }

        public static Uri buildScorecardEventIdEntrantIdUri(String eventid, String entrantid) {
            return CONTENT_URI.buildUpon().appendPath(eventid).appendPath(entrantid).build();
        }

        public static Uri buildScorecardIdUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

        public static String getScorecardElementFromUri(Uri uri) {
            return uri.getPathSegments().get(1);
        }

        public static long getScorecardIdFromUri(Uri uri){
            return Long.parseLong(uri.getPathSegments().get(1));
        }

        public static String getScorecardEventIdFromUri(Uri uri){
            return uri.getPathSegments().get(2);
        }

        public static String getScorecardsEventIdFromUri(Uri uri){
            return uri.getPathSegments().get(1);
        }
        public static String getScorecardsEntrantIdFromUri(Uri uri){
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

        public static final String COLUMN_TOTAL_TIME = "total_time";
        public static final String COLUMN_TOTAL_FAULTS = "total_faults";
        public static final String COLUMN_TOTAL_POINTS = "total_points";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_QUALIFYING_SCORE = "qualifying_score";
        public static final String COLUMN_QUALIFYING_SCORES = "qualifying_scores";

        public static Uri buildTallyTitleUri(String title) {
            return CONTENT_URI.buildUpon().appendPath(title).build();
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


        public static String getTallyTitleFromUri(Uri uri) {
            return uri.getPathSegments().get(1);
        }

        public static long getTallyIdFromUri(Uri uri){
            return Long.parseLong(uri.getPathSegments().get(1));
        }

        public static long getTallyEventIdFromUri(Uri uri){
            return Long.parseLong(uri.getPathSegments().get(1));
        }
    }



    public static final class EventEntrantTallyEntry implements BaseColumns {

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_EVENTENTRANTTALLY).build();

        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_EVENTENTRANTTALLY;
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_EVENTENTRANTTALLY;

        public static final String TABLE_NAME = "evententranttally";

        public static final String COLUMN_EVENT_ID = "event_id";

        public static final String COLUMN_ENTRANT_ID = "entrant_id";

        public static final String COLUMN_TALLY_ID = "tally_id";

        public static Uri buildEventEntrantTallyUri() {
            return CONTENT_URI;
        }

        public static Uri buildEventEntrantTallyidUri(String _id) {
            return CONTENT_URI.buildUpon().appendPath(_id).build();
        }

        public static Uri buildIdEventEntrantTally(String eventid, String entrantid){
            return CONTENT_URI.buildUpon().appendPath(eventid).appendPath(entrantid).build();
        }

        public static Uri buildEventEntrantTallyIdUri(String tally_id) {
            return CONTENT_URI.buildUpon().appendPath(tally_id).build();
        }

        public static Uri buildIdEventEntrantTallyUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

        public static long getEventEntrantTallyIdFromUri(Uri uri){
            return Long.parseLong(uri.getPathSegments().get(1));
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

        public static String getTallyIdWithEventIdEntrantIdFromUri(Uri uri){
            return uri.getPathSegments().get(3);
        }
    }
}