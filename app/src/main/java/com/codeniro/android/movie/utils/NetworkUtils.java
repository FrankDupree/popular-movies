package com.codeniro.android.movie.utils;

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by Dupree on 12/05/2017.
 */
public class NetworkUtils {

    public static final String PIXURL = "http://image.tmdb.org/t/p/w185/";
    private static final String BASEURL = "https://api.themoviedb.org/3/movie/";
    private static final String QUERY_PARAM = "api_key";

    public static URL buildUrl(String path,String apikey) {
        Uri builtUri = Uri.parse(BASEURL).buildUpon()
                .appendEncodedPath(path)
                .appendQueryParameter(QUERY_PARAM, apikey)
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }
    public static URL buildVideoReviewUrl(String id,String type,String apikey) {
        Uri builtUri = Uri.parse(BASEURL).buildUpon()
                .appendEncodedPath(id)
                .appendEncodedPath(type)
                .appendQueryParameter(QUERY_PARAM, apikey)
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }


    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();
            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }

}

