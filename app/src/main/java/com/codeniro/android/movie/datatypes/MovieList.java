package com.codeniro.android.movie.datatypes;

import android.database.Cursor;

import com.codeniro.android.movie.data.MovieContract;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dupree on 12/05/2017.
 */
public class MovieList {
    private static final String BACKDROP = "backdrop_path";
    private static final String GENEREID = "genre_ids";
    private static final String ORIGINALTITLE = "original_title";
    private static final String OVERVIEW = "overview";
    private static final String POSTERPATH = "poster_path";
    private static final String RELEASEDATE = "release_date";
    private static final String RESULT = "results";
    private static final String VOTEAVERAGE = "vote_average";
    private static final String AUTHOR = "author";
    private static final String CONTENT = "content";
    private static final String ID = "id";

    private  List<Movie> parsedMovies;
    private Movie movieresponse;

    public List<Movie> getMovieList(String jsonResponse) throws JSONException {
        JSONObject object = new JSONObject(jsonResponse);
        JSONArray data = object.getJSONArray(RESULT);
        parsedMovies = new ArrayList<>();
        for(int i= 0; i<data.length(); i++){
            JSONObject moviedata = (JSONObject)data.get(i);
            movieresponse = new Movie();

            movieresponse.setId(moviedata.getInt(ID));
            movieresponse.setBackdrop_path(moviedata.getString(BACKDROP));
            movieresponse.setOriginal_title(moviedata.getString(ORIGINALTITLE));
            movieresponse.setOverview(moviedata.getString(OVERVIEW));
            movieresponse.setPoster_path(moviedata.getString(POSTERPATH));
            movieresponse.setRelease_date(moviedata.getString(RELEASEDATE));
            movieresponse.setVote_average(moviedata.getString(VOTEAVERAGE));



            JSONArray genre = moviedata.getJSONArray(GENEREID);
            int [] temp = new int[genre.length()];
            for(int j=0; j<genre.length();j++){
                temp[j] = genre.getInt(j);
            }

            movieresponse.setGenre_ids(temp);

            parsedMovies.add(movieresponse);

        }

        return  parsedMovies;
    }

    public  List<Movie> getMovieListFromCursor(Cursor cursor) throws JSONException {
        parsedMovies = new ArrayList<>();
        while (cursor.moveToNext()){
            movieresponse = new Movie();
            movieresponse.setId(cursor.getInt(cursor.getColumnIndexOrThrow(MovieContract.MovieEntry.COLUMN_ID)));
            movieresponse.setOriginal_title(cursor.getString(cursor.getColumnIndexOrThrow(MovieContract.MovieEntry.COLUMN_TITLE)));
            movieresponse.setOverview(cursor.getString(cursor.getColumnIndexOrThrow(MovieContract.MovieEntry.COLUMN_OVERVIEW)));
            movieresponse.setPoster_path(cursor.getString(cursor.getColumnIndexOrThrow(MovieContract.MovieEntry.COLUMN_IMG)));
            movieresponse.setRelease_date(cursor.getString(cursor.getColumnIndexOrThrow(MovieContract.MovieEntry.COLUMN_RELEASE_DATE)));
            movieresponse.setVote_average(cursor.getString(cursor.getColumnIndexOrThrow(MovieContract.MovieEntry.COLUMN_VOTE_AVERAGE)));

            parsedMovies.add(movieresponse);

        }

        return  parsedMovies;
    }
}
