package apps.realkarim.movieoffice.Adapters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import apps.realkarim.movieoffice.Fragments.OverviewFragment;
import apps.realkarim.movieoffice.Fragments.ReviewsFragment;
import apps.realkarim.movieoffice.Fragments.TrailersFragment;
import apps.realkarim.movieoffice.Models.Movie;

/**
 * Created by karim on 28-Apr-16.
 */
public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    String[] tabsTitles;
    Movie movie;


    public ViewPagerAdapter(FragmentManager fm, String[] tabsTitles, Movie movie) {
        super(fm);
        this.tabsTitles = tabsTitles;
        this.movie = movie;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new OverviewFragment();
                break;
            case 1:
                fragment = new ReviewsFragment();
                break;
            case 2:
                fragment = new TrailersFragment();
        }
        Bundle bundle = new Bundle();
        bundle.putParcelable("movie", movie);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return this.tabsTitles[position];
    }
}
