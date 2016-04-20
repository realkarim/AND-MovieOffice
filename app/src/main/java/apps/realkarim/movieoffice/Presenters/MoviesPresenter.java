package apps.realkarim.movieoffice.Presenters;

import android.content.Context;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import apps.realkarim.movieoffice.Adapters.GridMoviesAdapter;
import apps.realkarim.movieoffice.AsyncTasks.MoviesRetriever;
import apps.realkarim.movieoffice.Interfaces.OnDataFetchedListener;
import apps.realkarim.movieoffice.Models.Movie;
import apps.realkarim.movieoffice.Parsers.MoviesParser;
import apps.realkarim.movieoffice.R;

/**
 * Created by karim on 18-Apr-16.
 */
public class MoviesPresenter {
    String TAG = MoviesRetriever.class.getName();
    Context context;

    public MoviesPresenter(Context context) {
        this.context = context;
    }

    public void getMovies(OnDataFetchedListener onDataFetchedListener, String sort_order) {
//        if(sort_order.equals(context.getResources().getString(R.string.Most_Popular)))
        MoviesRetriever retriever = new MoviesRetriever(onDataFetchedListener, context.getResources().getString(R.string.URL_Most_Popular),
                context.getResources().getString(R.string.key));

        retriever.execute();
    }
}
