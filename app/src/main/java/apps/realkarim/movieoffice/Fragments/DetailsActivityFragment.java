package apps.realkarim.movieoffice.Fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import apps.realkarim.movieoffice.Adapters.ViewPagerAdapter;
import apps.realkarim.movieoffice.Models.Movie;
import apps.realkarim.movieoffice.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class DetailsActivityFragment extends Fragment {

    private String[] tabsTitles;
    ViewPager pager;

    public DetailsActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_details, container, false);
        pager = (ViewPager) view.findViewById(R.id.pager);

        Movie movie = getActivity().getIntent().getParcelableExtra("movie");

        tabsTitles = getResources().getStringArray(R.array.tabs_titles);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager(), tabsTitles, movie);
        pager.setAdapter(viewPagerAdapter);

        return view;
    }
}
