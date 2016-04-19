package apps.realkarim.movieoffice.AsyncTasks;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import apps.realkarim.movieoffice.Adapters.GridMoviesAdapter;
import apps.realkarim.movieoffice.Models.Movie;

/**
 * Created by karim on 18-Apr-16.
 */
public class MoviesRetriever extends AsyncTask<Void, Void, JSONArray> {

    GridMoviesAdapter gridMoviesAdapter;
    String key;
    String url;

    public MoviesRetriever(GridMoviesAdapter gridMoviesAdapter, String url, String key) {
        this.gridMoviesAdapter = gridMoviesAdapter;
        this.key = key;
        this.url = url;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected JSONArray doInBackground(Void... params) {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        String moviesJsonStr = null;


        try {
            final String Movies_BASE_URL = url;

            Uri builtUri = Uri.parse(Movies_BASE_URL).buildUpon()
                    .appendQueryParameter("api_key", key)
                    .build();

            URL url = new URL(builtUri.toString());

            // Create the request and open the connection
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // Read the input stream into a String
            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                // Nothing to do.
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                // But it does make debugging a *lot* easier if you print out the completed
                // buffer for debugging.
                buffer.append(line + "\n");
            }

            if (buffer.length() == 0) {
                // Stream was empty.  No point in parsing.
                return null;
            }
            moviesJsonStr = buffer.toString();
        } catch (IOException e) {
            return null;
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                }
            }
        }
        try {
            JSONArray result = new JSONObject(moviesJsonStr).getJSONArray("results");
            return result;
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(JSONArray jsonArray) {
        super.onPostExecute(jsonArray);

        ArrayList<Movie> movies = new ArrayList<>();

        for(int i=0;i<jsonArray.length();i++){
            try {
                JSONObject jsonMovie = jsonArray.getJSONObject(i);
                Movie movie = new Movie();
                movie.setTitle(jsonMovie.getString("title"));
                movie.setOverview(jsonMovie.getString("overview"));
                movie.setRatting(jsonMovie.getString("vote_average") + "/10");
                movie.setPosterPath(jsonMovie.getString("poster_path"));
                movies.add(movie);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        gridMoviesAdapter.setData(movies);
        gridMoviesAdapter.notifyDataSetChanged();

    }
}
