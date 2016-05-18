package apps.realkarim.movieoffice;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import apps.realkarim.movieoffice.Fragments.DetailsActivityFragment;
import apps.realkarim.movieoffice.Interfaces.MovieClickListener;
import apps.realkarim.movieoffice.Models.Movie;

public class MoviesActivity extends AppCompatActivity implements MovieClickListener {

    Boolean isTablet;
    DetailsActivityFragment detailsActivityFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        if (findViewById(R.id.details) == null)
            isTablet = false;
        else
            isTablet = true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_movies, menu);
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onMovieSelected(Movie movie) {
        if (isTablet) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            Bundle bundle = new Bundle();
            bundle.putParcelable("movie", movie);
            detailsActivityFragment = new DetailsActivityFragment();
            detailsActivityFragment.setArguments(bundle);
            fragmentManager.beginTransaction().replace(R.id.details, detailsActivityFragment).commit();
        } else {
            Intent intent = new Intent(this, DetailsActivity.class);
            intent.putExtra("movie", movie);
            startActivity(intent);
        }
    }
}
