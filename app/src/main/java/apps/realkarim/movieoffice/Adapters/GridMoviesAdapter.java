package apps.realkarim.movieoffice.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import apps.realkarim.movieoffice.Models.Movie;
import apps.realkarim.movieoffice.R;

/**
 * Created by karim on 18-Apr-16.
 */
public class GridMoviesAdapter extends BaseAdapter {
    ArrayList<Movie> movies;
    Context context;

    public GridMoviesAdapter(Context context) {
        movies = new ArrayList<>();
        this.context = context;
    }

    public void setData(ArrayList<Movie> movies) {
        this.movies.clear();
        this.movies.addAll(movies);
    }

    @Override
    public int getCount() {
        return movies.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.grid_item_layout, null);
        }

        Picasso.with(context)
                .load(context.getResources().getString(R.string.Image_Base_URL) + movies.get(position).getPosterPath())
                .into((ImageView) convertView.findViewById(R.id.img));

        return convertView;
    }
}
