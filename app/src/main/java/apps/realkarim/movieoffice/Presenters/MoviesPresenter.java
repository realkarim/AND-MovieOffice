package apps.realkarim.movieoffice.Presenters;

import android.content.Context;

import apps.realkarim.movieoffice.AsyncTasks.ApiCall;
import apps.realkarim.movieoffice.Interfaces.OnDataFetchedListener;
import apps.realkarim.movieoffice.Interfaces.OnFavoriteDataRetreived;
import apps.realkarim.movieoffice.R;
import apps.realkarim.movieoffice.data.MoviesProvider;

/**
 * Created by karim on 18-Apr-16.
 */
public class MoviesPresenter {
    String TAG = ApiCall.class.getName();
    Context context;

    public MoviesPresenter(Context context) {
        this.context = context;
    }

    public void getMovies(OnDataFetchedListener onDataFetchedListener, OnFavoriteDataRetreived onFavoriteDataRetreived, String sort_order) {

        if (sort_order.equals(context.getResources().getString(R.string.Most_Popular))) {
            ApiCall retriever = new ApiCall(onDataFetchedListener, context.getResources().getString(R.string.URL_Most_Popular),
                    context.getResources().getString(R.string.key));
            retriever.execute();

        } else if (sort_order.equals(context.getResources().getString(R.string.Top_Rated))) {
            ApiCall retriever = new ApiCall(onDataFetchedListener, context.getResources().getString(R.string.URL_Top_Rated),
                    context.getResources().getString(R.string.key));
            retriever.execute();

        } else {
            MoviesProvider moviesProvider = new MoviesProvider(context, onFavoriteDataRetreived);
            moviesProvider.getFavoriteMovies();
        }
    }

    public void getReviews(OnDataFetchedListener onDataFetchedListener, String id) {
        String url = context.getResources().getString(R.string.URL_Base) + "movie/" + id + "/reviews";
        ApiCall apiCall = new ApiCall(onDataFetchedListener, url, context.getResources().getString(R.string.key));
        apiCall.execute();
    }

    public void getTrailers(OnDataFetchedListener onDataFetchedListener, String id) {
        String url = context.getResources().getString(R.string.URL_Base) + "movie/" + id + "/videos";
        ApiCall apiCall = new ApiCall(onDataFetchedListener, url, context.getResources().getString(R.string.key));
        apiCall.execute();
    }

}
