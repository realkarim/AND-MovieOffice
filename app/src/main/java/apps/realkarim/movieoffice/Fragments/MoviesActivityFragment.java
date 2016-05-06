package apps.realkarim.movieoffice.Fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.provider.MediaStore;
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
import apps.realkarim.movieoffice.Interfaces.OnMoviesFetchedListener;
import apps.realkarim.movieoffice.Models.Movie;
import apps.realkarim.movieoffice.Parsers.MoviesParser;
import apps.realkarim.movieoffice.Presenters.MoviesPresenter;
import apps.realkarim.movieoffice.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class MoviesActivityFragment extends Fragment implements AdapterView.OnItemClickListener, View.OnClickListener, OnMoviesFetchedListener {

    String TAG = MoviesActivityFragment.class.getName();
    MoviesPresenter presenter;
    MoviesGridAdapter adapter;
    ProgressDialog progress;
    RadioGroup radioGroup;

    String cashedData = "";

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


        radioGroup = (RadioGroup) view.findViewById(R.id.radio_group);
        ((RadioButton) view.findViewById(R.id.most_popular)).setOnClickListener(this);
        ((RadioButton) view.findViewById(R.id.top_rated)).setOnClickListener(this);
        ((RadioButton) view.findViewById(R.id.favorite)).setOnClickListener(this);

        if (savedInstanceState != null) {
            RadioButton checkedButton = (RadioButton) radioGroup.findViewById(savedInstanceState.getInt("sort_order"));
            checkedButton.setChecked(true);
            onDataFetched(savedInstanceState.getString("cached_data"));         // display previously fetched data
        } else
            presenter.getMovies(this, getResources().getString(R.string.Most_Popular));

        return view;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(getActivity(), DetailsActivity.class);
        intent.putExtra("movie", (Movie) parent.getItemAtPosition(position));
        startActivity(intent);
    }

    @Override
    public void onDataStartFetching() {
        progress = new ProgressDialog(getActivity());
        progress.setMessage("Fetching movies...");
        progress.show();
    }

    @Override
    public void onDataFetched(String data) {
        cashedData = data;  // Save data to be restore it on activity re-creation instead of re-fetching it.
        try {
            JSONObject result = new JSONObject(data);
            MoviesParser moviesParser = new MoviesParser();
            ArrayList<Movie> movies = moviesParser.parse(result);

            adapter.setData(movies);
            adapter.notifyDataSetChanged();
        } catch (JSONException e) {
            Log.e(TAG, e.getMessage());
        }

        if (progress != null)
            progress.hide();
    }

    @Override
    public void onDataError(String error) {
        Toast.makeText(getActivity(), error, Toast.LENGTH_LONG).show();
        Log.e(TAG, error);
        if (progress != null)
            progress.hide();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.most_popular:
                presenter.getMovies(this, getResources().getString(R.string.Most_Popular));
                break;
            case R.id.top_rated:
                presenter.getMovies(this, getResources().getString(R.string.Top_Rated));
                break;
            case R.id.favorite:
                break;
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt("sort_order", radioGroup.getCheckedRadioButtonId());
        outState.putString("cached_data", cashedData);
        super.onSaveInstanceState(outState);
    }


}
