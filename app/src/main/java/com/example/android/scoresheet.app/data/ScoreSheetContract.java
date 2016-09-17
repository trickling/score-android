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

    public static final class EventEntry implements BaseColumns {

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_EVENT).build();

        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_EVENT;
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_EVENT;

        public static final String TABLE_NAME = "event";

        public static final String COLUMN_SHORT_DESC = "short_desc";

        public static Uri buildEventDesc(String short_desc) {

            return CONTENT_URI.buildUpon().appendPath(short_desc).build();
        }

        public static Uri buildEvent(String TABLE_NAME) {
            return CONTENT_URI;
        }

        public static Uri buildEventUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

        public static String getEventDescriptionFromUri(Uri uri) {
            return uri.getPathSegments().get(1);
        }

    }

    public static final class EntrantEntry implements BaseColumns {

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_ENTRANT).build();

        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_EVENT;
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_EVENT;

        public static final String TABLE_NAME = "entrant";

        public static final String COLUMN_TEAM_DESC = "team_descr";

        public static Uri buildEntrantDesc(String team_desc) {

            return CONTENT_URI.buildUpon().appendPath(team_desc).build();
        }

        public static Uri buildEntrant(String TABLE_NAME) {
            return CONTENT_URI;
        }

        public static Uri buildEntrantUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

        public static String getEntrantDescriptionFromUri(Uri uri) {
            return uri.getPathSegments().get(1);
        }

    }

}
