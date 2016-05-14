package apps.realkarim.movieoffice.Parsers;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import apps.realkarim.movieoffice.Interfaces.IParser;
import apps.realkarim.movieoffice.Models.Review;
import apps.realkarim.movieoffice.Models.Trailer;

/**
 * Created by Karim Mostafa on 14-May-16.
 */
public class TrailersParser implements IParser {
    String TAG = TrailersParser.class.getName();

    @Override
    public ArrayList parse(JSONObject json) {
        ArrayList<Trailer> trailers = new ArrayList<>();

        try {
            JSONArray jsonArray = json.getJSONArray("results");
            for (int i = 0; i < jsonArray.length(); i++) {
                try {
                    JSONObject jsonMovie = jsonArray.getJSONObject(i);
                    Trailer trailer = new Trailer();
                    trailer.setKey(jsonMovie.getString("key"));
                    trailer.setName(jsonMovie.getString("name"));
                    trailer.setSite(jsonMovie.getString("site"));
                    trailers.add(trailer);
                } catch (JSONException e) {
                    Log.e(TAG, e.getMessage());
                }
            }
        } catch (JSONException e) {
            Log.e(TAG, e.getMessage());
        }
        return  trailers;
    }
}
