package com.codeniro.android.movie.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.codeniro.android.movie.data.MovieContract.MovieEntry;
/**
 * Created by Dupree on 12/05/2017.
 */

public class MovieProvider extends ContentProvider {
    private static final UriMatcher sUriMatcher = buildUriMatcher();
    private static final int CODE_MOVIE = 100;
    private static final int CODE_MOVIE_ID = 101;


    public static UriMatcher buildUriMatcher() {
        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        final String authority = MovieContract.CONTENT_AUTHORITY;
        matcher.addURI(authority, MovieContract.PATH_FAVORITES, CODE_MOVIE);
        matcher.addURI(authority, MovieContract.PATH_FAVORITES + "/*", CODE_MOVIE_ID);
        return matcher;
    }

    private MovieDbHelper mDbHelper;

    @Override
    public boolean onCreate() {
        mDbHelper = new MovieDbHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] strings, @Nullable String s, @Nullable String[] strings1, @Nullable String s1) {
        final SQLiteDatabase db = mDbHelper.getReadableDatabase();
        Cursor cursor = null;
        switch (sUriMatcher.match(uri)){
            case CODE_MOVIE:
                try {
                    cursor = db.query(MovieEntry.TABLE_NAME,null,null,null,null,null, MovieContract.MovieEntry._ID);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
                break;
            case CODE_MOVIE_ID:
                try {
                    cursor = db.query(MovieEntry.TABLE_NAME,null,null,null,null,null,null);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
                break;
            default:
                throw new UnsupportedOperationException("error:" + uri);
        }
        cursor.setNotificationUri(getContext().getContentResolver(),uri);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        int match = sUriMatcher.match(uri);

        switch (match) {
            case CODE_MOVIE:
                return "vnd.android.cursor.dir" + "/" + MovieContract.CONTENT_AUTHORITY + "/" + MovieContract.PATH_FAVORITES;
            case CODE_MOVIE_ID:
                return "vnd.android.cursor.item" + "/" + MovieContract.CONTENT_AUTHORITY + "/" + MovieContract.PATH_FAVORITES;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues)  {
        final SQLiteDatabase db = mDbHelper.getWritableDatabase();
        Uri returnUri;
        long ids;
        switch (sUriMatcher.match(uri)){
            case CODE_MOVIE:
                ids = db.insert(MovieEntry.TABLE_NAME,null,contentValues);
                if(ids > 0)
                    returnUri = ContentUris.withAppendedId(MovieEntry.CONTENT_URI,ids);
                else
                    returnUri = null;
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri,null);
        return returnUri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }
}
