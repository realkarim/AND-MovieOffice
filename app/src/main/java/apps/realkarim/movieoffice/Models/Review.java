package apps.realkarim.movieoffice.Models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Karim Mostafa on 09-May-16.
 */
public class Review implements Parcelable {
    String author;
    String review;

    public Review(){

    }

    public Review(String author, String review) {
        this.author = author;
        this.review = review;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getAuthor() {
        return author;
    }

    public String getReview() {
        return review;
    }



    protected Review(Parcel in) {
        author = in.readString();
        review = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(author);
        dest.writeString(review);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Review> CREATOR = new Parcelable.Creator<Review>() {
        @Override
        public Review createFromParcel(Parcel in) {
            return new Review(in);
        }

        @Override
        public Review[] newArray(int size) {
            return new Review[size];
        }
    };
}
