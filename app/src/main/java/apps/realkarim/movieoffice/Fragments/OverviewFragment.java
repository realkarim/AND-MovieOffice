package apps.realkarim.movieoffice.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import apps.realkarim.movieoffice.Models.Movie;
import apps.realkarim.movieoffice.R;

/**
 * Created by karim on 28-Apr-16.
 */
public class OverviewFragment extends Fragment {

    public OverviewFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_overview, container, false);

        Movie movie = getArguments().getParcelable("movie");
        ((TextView) view.findViewById(R.id.movie_title)).setText(movie.getTitle());
        ((TextView) view.findViewById(R.id.movie_plot)).setText(movie.getOverview());
        ((TextView) view.findViewById(R.id.movie_vote_average)).setText(movie.getRatting());
        ((TextView) view.findViewById(R.id.movie_release_date)).setText(movie.getRelease_date());


        Picasso.with(getActivity())
                .load(getActivity().getResources().getString(R.string.Image_Base_URL) + movie.getPosterPath())
                .into(((ImageView) view.findViewById(R.id.movie_poster)));

        return view;
    }
}
