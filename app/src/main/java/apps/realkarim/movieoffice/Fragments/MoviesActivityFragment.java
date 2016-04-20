package apps.realkarim.movieoffice.Fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import apps.realkarim.movieoffice.Adapters.GridMoviesAdapter;
import apps.realkarim.movieoffice.AsyncTasks.MoviesRetriever;
import apps.realkarim.movieoffice.Interfaces.OnDataFetchedListener;
import apps.realkarim.movieoffice.Models.Movie;
import apps.realkarim.movieoffice.Parsers.MoviesParser;
import apps.realkarim.movieoffice.Presenters.MoviesPresenter;
import apps.realkarim.movieoffice.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class MoviesActivityFragment extends Fragment implements View.OnClickListener, OnDataFetchedListener{

    String TAG = MoviesActivityFragment.class.getName();
    MoviesPresenter presenter;
    GridMoviesAdapter adapter;

    public MoviesActivityFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        presenter = new MoviesPresenter(getActivity());
        adapter = new GridMoviesAdapter(getActivity());

        View view = inflater.inflate(R.layout.fragment_movies, container, false);
        GridView gridMovies = (GridView) view.findViewById(R.id.grid_movies);
        gridMovies.setAdapter(adapter);


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.getMovies(this, getResources().getString(R.string.Most_Popular));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.grid_movies:

                break;
        }
    }

    @Override
    public void onDataFetched(String data) {
        try {
            JSONObject result = new JSONObject(data);
            MoviesParser moviesParser = new MoviesParser();
            ArrayList<Movie> movies = moviesParser.parse(result);

            adapter.setData(movies);
            adapter.notifyDataSetChanged();
        } catch (JSONException e) {
            Log.e(TAG, e.getMessage());
        }
    }

    @Override
    public void onDataError(String error) {
        Toast.makeText(getActivity(), error, Toast.LENGTH_LONG).show();
        Log.e(TAG, error);
    }
}
