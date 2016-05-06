package apps.realkarim.movieoffice.AsyncTasks;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import apps.realkarim.movieoffice.Interfaces.OnMoviesFetchedListener;

/**
 * Created by karim on 18-Apr-16.
 */
public class MoviesRetriever extends AsyncTask<Void, Void, String> {

    String TAG = MoviesRetriever.class.getName();

    OnMoviesFetchedListener onMoviesFetchedListener;
    String key;
    String url;

    public MoviesRetriever(OnMoviesFetchedListener onMoviesFetchedListener, String url, String key) {
        this.onMoviesFetchedListener = onMoviesFetchedListener;
        this.key = key;
        this.url = url;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        onMoviesFetchedListener.onDataStartFetching();
    }

    @Override
    protected String doInBackground(Void... params) {
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
            Log.e(TAG, e.getMessage());
            return e.getMessage();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    Log.e(TAG, e.getMessage());
                    return e.getMessage();
                }
            }
        }
        return moviesJsonStr;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

        try{
            JSONObject json = new JSONObject(result);
            onMoviesFetchedListener.onDataFetched(result);
        } catch (JSONException e) {
            onMoviesFetchedListener.onDataError(result);
        }
    }
}
