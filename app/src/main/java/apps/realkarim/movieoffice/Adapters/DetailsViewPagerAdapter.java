package apps.realkarim.movieoffice.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import apps.realkarim.movieoffice.Fragments.OverviewFragment;
import apps.realkarim.movieoffice.Fragments.ReviewsFragment;
import apps.realkarim.movieoffice.Fragments.TrailersFragment;

/**
 * Created by karim on 28-Apr-16.
 */
public class DetailsViewPagerAdapter extends FragmentStatePagerAdapter {
    String[] tabsTitles;

    public DetailsViewPagerAdapter(FragmentManager fm, String[] tabsTitles) {
        super(fm);
        this.tabsTitles = tabsTitles;
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
