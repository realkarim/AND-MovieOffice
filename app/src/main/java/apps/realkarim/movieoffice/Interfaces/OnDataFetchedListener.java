package apps.realkarim.movieoffice.Interfaces;

import java.util.ArrayList;

import apps.realkarim.movieoffice.Models.Movie;

/**
 * Created by karim on 20-Apr-16.
 */
public interface OnDataFetchedListener {
    public void onDataStartFetching();

    public void onDataFetched(String data);

    public void onDataError(String error);
}
