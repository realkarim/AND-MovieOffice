package apps.realkarim.movieoffice.Models;

/**
 * Created by karim on 18-Apr-16.
 */
public class Movie {
    String id;
    String title;
    String overview;
    String ratting;
    String posterPath;

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
}
