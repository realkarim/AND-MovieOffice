package apps.realkarim.movieoffice.Interfaces;

import java.util.ArrayList;

import apps.realkarim.movieoffice.Models.Movie;

/**
 * Created by Karim Mostafa on 17-May-16.
 */
public interface OnFavoriteDataRetreived {

    public void onFavoriteDataFetched(ArrayList<Movie> movies);
}
