package apps.realkarim.movieoffice.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Karim Mostafa on 14-May-16.
 */
public class MoviesDBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;

    static final String DATABASE_NAME = "weather.db";

    public MoviesDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_MOVIES_TABLE = "CREATE TABLE " + MoviesContract.TABLE_NAME + " ( " +
                MoviesContract.COLUMN_ID + " TEXT UNIQUE NOT NULL, " +
                MoviesContract.COLUMN_TITLE + " TEXT NOT NULL, " +
                MoviesContract.COLUMN_OVERVIEW + " TEXT NOT NULL, " +
                MoviesContract.COLUMN_POSTER_PATH + " REAL NOT NULL, " +
                MoviesContract.COLUMN_RATTING + " TEXT NOT NULL, " +
                MoviesContract.COLUMN_RELEASE_DATE + " TEXT NOT NULL );";

        db.execSQL(SQL_CREATE_MOVIES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + MoviesContract.TABLE_NAME);
        onCreate(db);
    }
}
