package apps.realkarim.movieoffice.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import apps.realkarim.movieoffice.Models.Movie;
import apps.realkarim.movieoffice.Models.Review;
import apps.realkarim.movieoffice.R;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Karim Mostafa on 09-May-16.
 */
public class ReviewListAdapter extends BaseAdapter {

    ArrayList<Review> reviews;
    Context context;

    public ReviewListAdapter(Context context) {
        reviews = new ArrayList<>();
        this.context = context;
    }

    public void updateData(ArrayList<Review> review) {
        this.reviews.clear();
        this.reviews.addAll(review);
    }

    @Override
    public int getCount() {
        return reviews.size();
    }

    @Override
    public Object getItem(int position) {
        return reviews.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.review_item_layout, null);
        }

        TextView reviewer = (TextView) convertView.findViewById(R.id.reviewer);
        TextView review = (TextView) convertView.findViewById(R.id.review);

        reviewer.setText(reviews.get(position).getAuthor());
        review.setText(reviews.get(position).getReview());



        return convertView;
    }
}
