package apps.realkarim.movieoffice.Fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import apps.realkarim.movieoffice.Adapters.GridMoviesAdapter;
import apps.realkarim.movieoffice.Presenters.MoviesPresenter;
import apps.realkarim.movieoffice.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class MoviesActivityFragment extends Fragment {

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

        presenter.getMovies(getResources().getString(R.string.Most_Popular), adapter);
    }
}
