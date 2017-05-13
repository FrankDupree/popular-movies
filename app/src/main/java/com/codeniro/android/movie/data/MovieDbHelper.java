package com.codeniro.android.movie.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.codeniro.android.movie.data.MovieContract.MovieEntry;

/**
 * Created by Dupree on 12/05/2017.
 */
public class MovieDbHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "movies.db";
    private static final int DATABASE_VERSION = 1;

    final String SQL_CREATE_MOVIE_TABLE =  "CREATE TABLE " + MovieEntry.TABLE_NAME + " (" +
            MovieEntry._ID  + "  INTEGER PRIMARY KEY AUTOINCREMENT, " +
            MovieEntry.COLUMN_ID  + " TEXT UNIQUE , " +
            MovieEntry.COLUMN_RELEASE_DATE  + " TEXT NOT NULL, " +
            MovieEntry.COLUMN_TITLE  + " TEXT NOT NULL, " +
            MovieEntry.COLUMN_VOTE_AVERAGE  + " TEXT NOT NULL, " +
            MovieEntry.COLUMN_OVERVIEW  + " TEXT NOT NULL, " +
            MovieEntry.COLUMN_IMG + " TEXT NOT NULL);";

    public MovieDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_MOVIE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + MovieEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onDowngrade(db, oldVersion, newVersion);
        onUpgrade(db,oldVersion,newVersion);
    }
}
