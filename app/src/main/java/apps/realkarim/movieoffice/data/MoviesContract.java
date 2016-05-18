package apps.realkarim.movieoffice.data;

import android.provider.BaseColumns;

/**
 * Created by Karim Mostafa on 14-May-16.
 */
public class MoviesContract implements BaseColumns {

    public static final String TABLE_NAME = "movies";

    public static final String COLUMN_ID = "COLUMN_ID";
    public static final String COLUMN_TITLE = "COLUMN_TITLE";
    public static final String COLUMN_OVERVIEW = "COLUMN_OVERVIEW";

    public static final String COLUMN_RATTING = "COLUMN_RATTING";
    public static final String COLUMN_POSTER_PATH = "COLUMN_POSTER_PATH";
    public static final String COLUMN_RELEASE_DATE = "COLUMN_RELEASE_DATE";

}
