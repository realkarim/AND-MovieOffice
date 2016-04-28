package apps.realkarim.movieoffice.Fragments;

import android.content.Intent;
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

import apps.realkarim.movieoffice.Adapters.MoviesGridAdapter;
import apps.realkarim.movieoffice.DetailsActivity;
import apps.realkarim.movieoffice.Interfaces.OnMoviesFetchedListener;
import apps.realkarim.movieoffice.Models.Movie;
import apps.realkarim.movieoffice.Parsers.MoviesParser;
import apps.realkarim.movieoffice.Presenters.MoviesPresenter;
import apps.realkarim.movieoffice.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class MoviesActivityFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener, OnMoviesFetchedListener {

    String TAG = MoviesActivityFragment.class.getName();
    MoviesPresenter presenter;
    MoviesGridAdapter adapter;

    public MoviesActivityFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        presenter = new MoviesPresenter(getActivity());
        adapter = new MoviesGridAdapter(getActivity());

        View view = inflater.inflate(R.layout.fragment_movies, container, false);
        GridView gridMovies = (GridView) view.findViewById(R.id.grid_movies);
        gridMovies.setAdapter(adapter);
        gridMovies.setOnItemClickListener(this);


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.getMovies(this, getResources().getString(R.string.Most_Popular));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.grid_movies:

                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(getActivity(), DetailsActivity.class);
        intent.putExtra("movie", (Movie) parent.getItemAtPosition(position));
        startActivity(intent);
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
