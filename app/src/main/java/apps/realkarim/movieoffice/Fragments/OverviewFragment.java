package apps.realkarim.movieoffice.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import apps.realkarim.movieoffice.Models.Movie;
import apps.realkarim.movieoffice.R;
import apps.realkarim.movieoffice.data.MoviesProvider;


/**
 * Created by karim on 28-Apr-16.
 */
public class OverviewFragment extends Fragment implements View.OnClickListener {

    Button favorite;
    MoviesProvider moviesProvider;
    Movie movie;


    public OverviewFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_overview, container, false);

        moviesProvider = new MoviesProvider(getActivity(), null);

        if (getArguments().getParcelable("movie") != null)
            movie = getArguments().getParcelable("movie");
        else
            movie = getActivity().getIntent().getParcelableExtra("movie");

        ((TextView) view.findViewById(R.id.movie_title)).setText(movie.getTitle());
        ((TextView) view.findViewById(R.id.movie_plot)).setText(movie.getOverview());
        ((TextView) view.findViewById(R.id.movie_vote_average)).setText(movie.getRatting());
        ((TextView) view.findViewById(R.id.movie_release_date)).setText(movie.getRelease_date());

        favorite = (Button) view.findViewById(R.id.favorite_button);
        if (moviesProvider.isFavoriteMovie(movie)) {
            favorite.setText("Favorite");
            favorite.setTextColor(ContextCompat.getColor(getActivity(), R.color.Golden));
        } else {
            favorite.setText("Add To Favorite");
            favorite.setTextColor(ContextCompat.getColor(getActivity(), R.color.colorAccent));
        }
        favorite.setOnClickListener(this);


        Picasso.with(getActivity())
                .load(getActivity().getResources().getString(R.string.Image_Base_URL) + movie.getPosterPath())
                .into(((ImageView) view.findViewById(R.id.movie_poster)));

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.favorite_button:
                if (moviesProvider.isFavoriteMovie(movie)) {
                    moviesProvider.removeFavoriteMovie(movie);
                    favorite.setText("Add to Favorite");
                    favorite.setTextColor(ContextCompat.getColor(getActivity(), R.color.colorAccent));
                } else {
                    moviesProvider.addFavoriteMovie(movie);
                    favorite.setText("Favorite");
                    favorite.setTextColor(ContextCompat.getColor(getActivity(), R.color.Golden));
                }
        }
    }
}
