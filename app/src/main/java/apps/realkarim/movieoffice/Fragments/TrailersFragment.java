package apps.realkarim.movieoffice.Fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import apps.realkarim.movieoffice.Adapters.ReviewListAdapter;
import apps.realkarim.movieoffice.Adapters.TrailerListAdapter;
import apps.realkarim.movieoffice.Interfaces.OnDataFetchedListener;
import apps.realkarim.movieoffice.Models.Movie;
import apps.realkarim.movieoffice.Models.Review;
import apps.realkarim.movieoffice.Models.Trailer;
import apps.realkarim.movieoffice.Parsers.ReviewsParser;
import apps.realkarim.movieoffice.Parsers.TrailersParser;
import apps.realkarim.movieoffice.Presenters.MoviesPresenter;
import apps.realkarim.movieoffice.R;

/**
 * Created by karim on 28-Apr-16.
 */
public class TrailersFragment extends Fragment implements OnDataFetchedListener, AdapterView.OnItemClickListener {
    String TAG = ReviewsFragment.class.getName();

    ProgressDialog progress;
    TrailerListAdapter trailerListAdapter;

    String cashedData = "";


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_trailers, container, false);

        trailerListAdapter = new TrailerListAdapter(getActivity());
        ListView trailers = (ListView) view.findViewById(R.id.list);
        trailers.setAdapter(trailerListAdapter);
        trailers.setOnItemClickListener(this);

        if (savedInstanceState != null)
            onDataFetched(savedInstanceState.getString("cached_data"));         // display previously fetched data
        else {
            Movie movie = getArguments().getParcelable("movie");
            new MoviesPresenter(getActivity()).getTrailers(this, movie.getId());
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
//        progress = new ProgressDialog(getActivity());
//        progress.setMessage("Fetching trailers...");
//        progress.show();
    }

    @Override
    public void onDataFetched(String data) {
        cashedData = data;  // Save data to be restore it on activity re-creation instead of re-fetching it.
        try {
            JSONObject result = new JSONObject(data);
            TrailersParser trailerParser = new TrailersParser();
            ArrayList<Trailer> trailers = trailerParser.parse(result);

            trailerListAdapter.updateData(trailers);
            trailerListAdapter.notifyDataSetChanged();
        } catch (JSONException e) {
            Log.e(TAG, e.getMessage());
        }

//        if (progress != null)
//            progress.hide();
    }

    @Override
    public void onDataError(String error) {
        Toast.makeText(getActivity(), "Connection Error: Couldn't retrieve trailers!", Toast.LENGTH_SHORT).show();
        Log.e(TAG, error);

//        if (progress != null)
//            progress.hide();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(((TextView) view.findViewById(R.id.url)).getText().toString()));
        startActivity(browserIntent);
    }
}
