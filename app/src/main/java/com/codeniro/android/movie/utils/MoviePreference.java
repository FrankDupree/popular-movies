package com.codeniro.android.movie.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.preference.PreferenceManager;

import com.codeniro.android.movie.R;

/**
 * Created by Dupree on 12/05/2017.
 */
public class MoviePreference {

    public static String getSortOrder(Context vcontext){
        SharedPreferences shared = PreferenceManager.getDefaultSharedPreferences(vcontext);
        return shared.getString(vcontext.getString(R.string.tmdb_sort_type),vcontext.getString(R.string.tmdb_popular));
    }

}
