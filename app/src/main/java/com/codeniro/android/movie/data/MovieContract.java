package com.codeniro.android.movie.data;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Dupree on 12/05/2017.
 */

public class MovieContract {
    public static final String CONTENT_AUTHORITY = "com.codeniro.android.movie";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final String PATH_FAVORITES = "favorites";

    public final static class MovieEntry implements BaseColumns {
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon()
                .appendPath(PATH_FAVORITES)
                .build();

        // Table columns
        public static final String TABLE_NAME = "favorites";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_OVERVIEW = "overview";
        public static final String COLUMN_VOTE_AVERAGE = "vote_Average";
        public static final String COLUMN_RELEASE_DATE = "release_date";
        public static final String COLUMN_IMG = "img";
    }
}
