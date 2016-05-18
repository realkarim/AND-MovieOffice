package apps.realkarim.movieoffice.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.util.Log;

import java.util.ArrayList;

import apps.realkarim.movieoffice.Interfaces.OnFavoriteDataRetreived;
import apps.realkarim.movieoffice.Models.Movie;

/**
 * Created by Karim Mostafa on 14-May-16.
 */
public class MoviesProvider {
    OnFavoriteDataRetreived onFavoriteDataFetched;
    Context context;

    public MoviesProvider(Context context, OnFavoriteDataRetreived onFavoriteDataFetched) {
        this.context = context;
        this.onFavoriteDataFetched = onFavoriteDataFetched;

    }

    public boolean addFavoriteMovie(Movie movie) {
        MoviesDBHelper moviesDBHelper;
        moviesDBHelper = new MoviesDBHelper(this.context);
        ContentValues content = new ContentValues();
        content.put(MoviesContract.COLUMN_ID, movie.getId());
        content.put(MoviesContract.COLUMN_TITLE, movie.getTitle());
        content.put(MoviesContract.COLUMN_OVERVIEW, movie.getOverview());
        content.put(MoviesContract.COLUMN_POSTER_PATH, movie.getPosterPath());
        content.put(MoviesContract.COLUMN_RATTING, movie.getRatting());
        content.put(MoviesContract.COLUMN_RELEASE_DATE, movie.getRelease_date());


        try {
            moviesDBHelper.getWritableDatabase().insert(MoviesContract.TABLE_NAME, null, content);
        } catch (SQLException e) {
            Log.e(MoviesProvider.class.getName(), e.getMessage());
            return false;
        }

        moviesDBHelper.close();

        return true;
    }

    public boolean removeFavoriteMovie(Movie movie) {
        MoviesDBHelper moviesDBHelper;
        moviesDBHelper = new MoviesDBHelper(this.context);

        try {
            moviesDBHelper.getWritableDatabase().delete(MoviesContract.TABLE_NAME, MoviesContract.COLUMN_ID + "=" + movie.getId(), null);
        } catch (SQLException e) {
            Log.e(MoviesProvider.class.getName(), e.getMessage());
            return false;
        }
        moviesDBHelper.close();
        return true;
    }

    public boolean isFavoriteMovie(Movie movie) {
        ArrayList<Movie> allMovies = getFavoriteMovies();
        String id = movie.getId();

        for (int i = 0; i < allMovies.size(); i++)
            if (id.equals(allMovies.get(i).getId()))
                return true;
        
        return false;
    }

    public ArrayList getFavoriteMovies() {

        MoviesDBHelper moviesDBHelper;
        moviesDBHelper = new MoviesDBHelper(this.context);


        ArrayList<Movie> allMovies = new ArrayList<>();
        Cursor cursor = moviesDBHelper.getReadableDatabase().rawQuery("select * from " + MoviesContract.TABLE_NAME, null);
        if (cursor.moveToFirst()) {
            do {
                Movie movie = new Movie();
                movie.setId(cursor.getString(0));
                movie.setTitle(cursor.getString(1));
                movie.setOverview(cursor.getString(2));
                movie.setPosterPath(cursor.getString(3));
                movie.setRatting(cursor.getString(4));
                movie.setRelease_date(cursor.getString(5));
                movie.setFavorite(true);

                allMovies.add(movie);
            } while (cursor.moveToNext());
        }

        moviesDBHelper.close();

        if (onFavoriteDataFetched != null)
            onFavoriteDataFetched.onFavoriteDataFetched(allMovies);

        return allMovies;
    }
}
