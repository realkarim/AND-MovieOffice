package apps.realkarim.movieoffice.Fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import apps.realkarim.movieoffice.Adapters.MoviesGridAdapter;
import apps.realkarim.movieoffice.DetailsActivity;
import apps.realkarim.movieoffice.Interfaces.MovieClickListener;
import apps.realkarim.movieoffice.Interfaces.OnDataFetchedListener;
import apps.realkarim.movieoffice.Interfaces.OnFavoriteDataRetreived;
import apps.realkarim.movieoffice.Models.Movie;
import apps.realkarim.movieoffice.Parsers.MoviesParser;
import apps.realkarim.movieoffice.Presenters.MoviesPresenter;
import apps.realkarim.movieoffice.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class MoviesActivityFragment extends Fragment implements AdapterView.OnItemClickListener, View.OnClickListener, OnDataFetchedListener, OnFavoriteDataRetreived {

    String TAG = MoviesActivityFragment.class.getName();
    MoviesPresenter presenter;
    MoviesGridAdapter adapter;
    ProgressDialog progress;

    RadioGroup radioGroup;

    String cashedData = null;
    ArrayList<Movie> cashedMovie;

    public MoviesActivityFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movies, container, false);

        presenter = new MoviesPresenter(getActivity());
        adapter = new MoviesGridAdapter(getActivity());


        GridView gridMovies = (GridView) view.findViewById(R.id.grid_movies);
        gridMovies.setAdapter(adapter);
        gridMovies.setOnItemClickListener(this);


        radioGroup = (RadioGroup) view.findViewById(R.id.radio_group);
        ((RadioButton) view.findViewById(R.id.most_popular)).setOnClickListener(this);
        ((RadioButton) view.findViewById(R.id.top_rated)).setOnClickListener(this);
        ((RadioButton) view.findViewById(R.id.favorite)).setOnClickListener(this);

        if (savedInstanceState != null) {
            RadioButton checkedButton = (RadioButton) radioGroup.findViewById(savedInstanceState.getInt("sort_order"));
            checkedButton.setChecked(true);
            if (savedInstanceState.getString("cached_data") != null)     // display previously fetched data
                onDataFetched(savedInstanceState.getString("cached_data"));


            if (savedInstanceState.getParcelableArrayList("cached_movies") != null) {    // display previously fetched data
                ArrayList<Movie> movies = savedInstanceState.getParcelableArrayList("cached_movies");
                onFavoriteDataFetched(movies);
            }

        } else
            presenter.getMovies(this, null, getResources().getString(R.string.Most_Popular));

        return view;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ((MovieClickListener) getActivity()).onMovieSelected((Movie) parent.getItemAtPosition(position));
    }

    @Override
    public void onDataStartFetching() {
        if (progress != null && progress.isShowing())
            return;
        progress = new ProgressDialog(getActivity());
        progress.setMessage("Fetching movies...");
        progress.show();
    }

    @Override
    public void onDataFetched(String data) {
        cashedMovie = null;
        cashedData = data;      // Save data to be restore it on activity re-creation instead of re-fetching it.
        try {
            JSONObject result = new JSONObject(data);
            MoviesParser moviesParser = new MoviesParser();
            ArrayList<Movie> movies = moviesParser.parse(result);
            adapter.updateData(movies);
            adapter.notifyDataSetChanged();
        } catch (JSONException e) {
            Log.e(TAG, e.getMessage());
        }

        if (progress != null && progress.isShowing())
            progress.hide();
    }

    @Override
    public void onFavoriteDataFetched(ArrayList<Movie> movies) {
        cashedData = null;
        cashedMovie = movies;              // Save data to be restore it on activity re-creation instead of re-fetching it.
        adapter.updateData(movies);
        adapter.notifyDataSetChanged();

        if (progress != null && progress.isShowing())
            progress.hide();
    }

    @Override
    public void onDataError(String error) {
        adapter.updateData(new ArrayList<Movie>());
        adapter.notifyDataSetChanged();
        Toast.makeText(getActivity(), "Connection Error: Couldn't retrieve movies!", Toast.LENGTH_SHORT).show();
        Log.e(TAG, error);

        if (progress != null && progress.isShowing())
            progress.hide();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.most_popular:
                presenter.getMovies(this, null, getResources().getString(R.string.Most_Popular));
                break;
            case R.id.top_rated:
                presenter.getMovies(this, null, getResources().getString(R.string.Top_Rated));
                break;
            case R.id.favorite:
                presenter.getMovies(null, this, getResources().getString(R.string.Favorite));
                break;
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt("sort_order", radioGroup.getCheckedRadioButtonId());
        if (cashedMovie != null)
            outState.putParcelableArrayList("cached_movies", cashedMovie);

        if (cashedData != null)
            outState.putString("cached_data", cashedData);

        super.onSaveInstanceState(outState);
    }

    @Override
    public void onStop() {
        super.onStop();

        if (progress != null && progress.isShowing())
            progress.hide();
    }

    @Override
    public void onResume() {
        super.onResume();

        if(((RadioButton)getView().findViewById(R.id.favorite)).isChecked())
            presenter.getMovies(null, this, getResources().getString(R.string.Favorite));
    }
}
