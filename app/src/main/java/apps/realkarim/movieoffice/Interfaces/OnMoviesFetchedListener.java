package apps.realkarim.movieoffice.Interfaces;

/**
 * Created by karim on 20-Apr-16.
 */
public interface OnMoviesFetchedListener {
    public void onDataStartFetching();

    public void onDataFetched(String data);

    public void onDataError(String error);
}
