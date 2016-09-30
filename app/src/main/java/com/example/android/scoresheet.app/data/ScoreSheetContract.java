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
    public static final String PATH_EVENTENTRANT = "evententrant";


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



    public static final class EventEntrantEntry implements BaseColumns {

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_EVENTENTRANT).build();

        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_EVENTENTRANT;
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_EVENTENTRANT;

        public static final String TABLE_NAME = "evententrant";

        public static final String COLUMN_EVENT_ID= "event_id";
        public static final String COLUMN_ENTRANT_ID = "entrant_id";

        public static Uri buildEventEntrantUri() {
            return CONTENT_URI;
        }

        public static Uri buildEventEntrantIdUri(String entrant_id) {
            return CONTENT_URI.buildUpon().appendPath(entrant_id).build();
        }

        public static Uri buildEventIdEntrantUri(String event_id) {
            return CONTENT_URI.buildUpon().appendPath(event_id).build();
        }

        public static Uri buildIdEventEntrant(String eventid, String entrantid){
            return CONTENT_URI.buildUpon().appendPath(eventid).appendPath(entrantid).build();
        }

//        public static Uri buildEventEntrantUri(String TABLE_NAME) {
//            return CONTENT_URI;
//        }

        public static Uri buildIdEventEntrantUri(long id) {
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
    }
}
