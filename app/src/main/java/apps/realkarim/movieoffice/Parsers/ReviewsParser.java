package apps.realkarim.movieoffice.Parsers;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import apps.realkarim.movieoffice.Interfaces.IParser;
import apps.realkarim.movieoffice.Models.Movie;
import apps.realkarim.movieoffice.Models.Review;

/**
 * Created by Karim Mostafa on 09-May-16.
 */
public class ReviewsParser implements IParser {

    String TAG = ReviewsParser.class.getName();


    @Override
    public ArrayList parse(JSONObject json) {
        ArrayList<Review> reviews = new ArrayList<>();

        try {
            JSONArray jsonArray = json.getJSONArray("results");
            for (int i = 0; i < jsonArray.length(); i++) {
                try {
                    JSONObject jsonMovie = jsonArray.getJSONObject(i);
                    Review review = new Review();
                    review.setAuthor(jsonMovie.getString("author"));
                    review.setReview(jsonMovie.getString("content"));
                    reviews.add(review);
                } catch (JSONException e) {
                    Log.e(TAG, e.getMessage());
                }
            }
        } catch (JSONException e) {
            Log.e(TAG, e.getMessage());
        }
        return  reviews;
    }
}
