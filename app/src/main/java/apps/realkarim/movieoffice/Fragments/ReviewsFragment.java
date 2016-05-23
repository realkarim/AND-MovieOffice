package apps.realkarim.movieoffice.Fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import apps.realkarim.movieoffice.Adapters.ReviewListAdapter;
import apps.realkarim.movieoffice.Interfaces.OnDataFetchedListener;
import apps.realkarim.movieoffice.Models.Movie;
import apps.realkarim.movieoffice.Models.Review;
import apps.realkarim.movieoffice.Parsers.MoviesParser;
import apps.realkarim.movieoffice.Parsers.ReviewsParser;
import apps.realkarim.movieoffice.Presenters.MoviesPresenter;
import apps.realkarim.movieoffice.R;

/**
 * Created by karim on 28-Apr-16.
 */
public class ReviewsFragment extends Fragment implements OnDataFetchedListener {
    String TAG = ReviewsFragment.class.getName();

    ProgressDialog progress;
    ReviewListAdapter reviewListAdapter;

    String cashedData = "";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reviews, container, false);

        reviewListAdapter = new ReviewListAdapter(getActivity());
        ListView reviews = (ListView) view.findViewById(R.id.list);
        reviews.setAdapter(reviewListAdapter);

        if (savedInstanceState != null)
            onDataFetched(savedInstanceState.getString("cached_data"));         // display previously fetched data
        else {
            Movie movie = getArguments().getParcelable("movie");
            new MoviesPresenter(getActivity()).getReviews(this, movie.getId());
        }
        return view;
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString("cached_data", cashedData);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onDataStartFetching() {
//        if (progress != null && progress.isShowing())
//            return;
//
//        progress = new ProgressDialog(getActivity());
//        progress.setMessage("Fetching reviews...");
//        progress.show();
    }

    @Override
    public void onDataFetched(String data) {
        cashedData = data;  // Save data to be restore it on activity re-creation instead of re-fetching it.
        try {
            JSONObject result = new JSONObject(data);
            ReviewsParser reviewsParser = new ReviewsParser();
            ArrayList<Review> reviews = reviewsParser.parse(result);

            reviewListAdapter.updateData(reviews);
            reviewListAdapter.notifyDataSetChanged();
        } catch (JSONException e) {
            Log.e(TAG, e.getMessage());
        }

//        if (progress != null)
//            progress.hide();
    }

    @Override
    public void onDataError(String error) {
        Toast.makeText(getActivity(), "Connection Error: Couldn't retrieve reviews!", Toast.LENGTH_SHORT).show();
        Log.e(TAG, error);
//        if (progress != null)
//            progress.hide();
    }


}
