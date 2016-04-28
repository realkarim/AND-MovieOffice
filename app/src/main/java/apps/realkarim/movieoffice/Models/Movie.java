package apps.realkarim.movieoffice.Models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by karim on 18-Apr-16.
 */
public class Movie implements Parcelable {
    String id;
    String title;
    String overview;
    String ratting;
    String posterPath;
    String release_date;




    public Movie(){
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public void setRatting(String ratting) {
        this.ratting = ratting;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getRelease_date() {
        return release_date;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }

    public String getRatting() {
        return ratting;
    }

    public String getPosterPath() {
        return posterPath;
    }

    protected Movie(Parcel in) {
        id = in.readString();
        title = in.readString();
        overview = in.readString();
        ratting = in.readString();
        posterPath = in.readString();
        release_date = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(title);
        dest.writeString(overview);
        dest.writeString(ratting);
        dest.writeString(posterPath);
        dest.writeString(release_date);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
}