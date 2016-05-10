package apps.realkarim.movieoffice.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import apps.realkarim.movieoffice.Models.Movie;
import apps.realkarim.movieoffice.R;

/**
 * Created by karim on 28-Apr-16.
 */
public class TrailersFragment extends Fragment {
    Movie movie;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_trailers, container, false);
        return view;
    }
}
