package apps.realkarim.movieoffice.Fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import apps.realkarim.movieoffice.Adapters.DetailsViewPagerAdapter;
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

        tabsTitles = getResources().getStringArray(R.array.tabs_titles);
        DetailsViewPagerAdapter detailsViewPagerAdapter = new DetailsViewPagerAdapter(getActivity().getSupportFragmentManager(), tabsTitles);
        pager.setAdapter(detailsViewPagerAdapter);

        return view;
    }
}
