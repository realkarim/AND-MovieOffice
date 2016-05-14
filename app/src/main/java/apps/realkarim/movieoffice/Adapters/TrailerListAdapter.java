package apps.realkarim.movieoffice.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import apps.realkarim.movieoffice.Models.Review;
import apps.realkarim.movieoffice.Models.Trailer;
import apps.realkarim.movieoffice.R;

/**
 * Created by Karim Mostafa on 14-May-16.
 */
public class TrailerListAdapter extends BaseAdapter {

    ArrayList<Trailer> trailers;
    Context context;

    public TrailerListAdapter(Context context) {
        trailers = new ArrayList<>();
        this.context = context;
    }

    public void updateData(ArrayList<Trailer> trailers) {
        this.trailers.clear();
        this.trailers.addAll(trailers);
    }

    @Override
    public int getCount() {
        return trailers.size();
    }

    @Override
    public Object getItem(int position) {
        return trailers.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.trailer_item_layout, null);
        }

        TextView name = (TextView) convertView.findViewById(R.id.name);
        TextView site = (TextView) convertView.findViewById(R.id.site);
        TextView url = (TextView) convertView.findViewById(R.id.url);
        name.setText(trailers.get(position).getName());
        site.setText(trailers.get(position).getSite());
        url.setText("https://www.youtube.com/watch?v=" + trailers.get(position).getKey());

        return convertView;
    }
}
