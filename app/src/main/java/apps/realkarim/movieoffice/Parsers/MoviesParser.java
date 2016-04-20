package apps.realkarim.movieoffice.Parsers;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import apps.realkarim.movieoffice.Interfaces.IParser;
import apps.realkarim.movieoffice.Models.Movie;

/**
 * Created by karim on 20-Apr-16.
 */
public class MoviesParser implements IParser {

    String TAG = MoviesParser.class.getName();

    @Override
    public ArrayList parse(JSONObject json) {
        ArrayList<Movie> movies = new ArrayList<>();

        try {
            JSONArray jsonArray = json.getJSONArray("results");
            for (int i = 0; i < jsonArray.length(); i++) {
                try {
                    JSONObject jsonMovie = jsonArray.getJSONObject(i);
                    Movie movie = new Movie();
                    movie.setTitle(jsonMovie.getString("title"));
                    movie.setOverview(jsonMovie.getString("overview"));
                    movie.setRatting(jsonMovie.getString("vote_average") + "/10");
                    movie.setPosterPath(jsonMovie.getString("poster_path"));
                    movies.add(movie);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        } catch (JSONException e) {
            Log.e(TAG, e.getMessage());
        }


        return movies;
    }
}
