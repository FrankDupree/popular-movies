package com.codeniro.android.movie.datatypes;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static android.provider.Contacts.SettingsColumns.KEY;

/**
 * Created by Dupree on 12/05/2017.
 */
public class TrailersList {
    private static final String RESULT = "results";
    private static final String AUTHOR = "author";
    private static final String CONTENT = "content";
    private static final String ID = "id";

    private List<Trailer>parsedTrailer;
    private Trailer trailerresponse;

    public List<Trailer> getTrailerList(String jsonResponse) throws JSONException {
        JSONObject object = new JSONObject(jsonResponse);
        JSONArray data = object.getJSONArray(RESULT);
        parsedTrailer = new ArrayList<>();
        for(int i= 0; i<data.length(); i++){
            JSONObject reviewdata = (JSONObject)data.get(i);
            trailerresponse = new Trailer();
            //Log.d("Responses", reviewdata.toString());
            trailerresponse.setAuthor(reviewdata.getString(AUTHOR));
            trailerresponse.setContent(reviewdata.getString(CONTENT));
            parsedTrailer.add(trailerresponse);
        }

        return  parsedTrailer;

    }

    public  List<Trailer> getTrailerListVids(String jsonResponse) throws JSONException {
        JSONObject object = new JSONObject(jsonResponse);
        JSONArray data = object.getJSONArray(RESULT);
        parsedTrailer = new ArrayList<>();
        for(int i= 0; i<data.length(); i++){
            JSONObject trailerdata = (JSONObject)data.get(i);
            trailerresponse = new Trailer();
            trailerresponse.setId(trailerdata.getString(ID));
            trailerresponse.setKey(trailerdata.getString(KEY));
            parsedTrailer.add(trailerresponse);
        }
        return  parsedTrailer;
    }
}



