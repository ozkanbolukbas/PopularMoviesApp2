package movies.raemacias.com.movieappstage2;

//this code was input and interpreted by watching tutorials
//from Delaroy Studios on YouTube. Also input from Android Basics
//Networking course and other student advice and input

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import movies.raemacias.com.movieappstage1.R;
import movies.raemacias.com.movieappstage2.adapter.MoviesAdapter;
import movies.raemacias.com.movieappstage2.api.MovieInterface;
import movies.raemacias.com.movieappstage2.model.MovieModel;
import movies.raemacias.com.movieappstage2.model.Result;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener {

    GridLayoutManager layoutManager;
    private RecyclerView recyclerView;
    private List<Result> results;
    private MoviesAdapter adapter;

    private static final String TAG = "MainActivity";
    private String LOG_TAG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initViews();
        initViews1();

        //per suggested in the implementation guide, this
        //is from https://stackoverflow.com/questions/1560788/how-to-check-internet-access-on-android-inetaddress-never-times-out
        try {
            URL url = new URL("https://www.themoviedb.org/movie" );
            executeReq(url);
        }
        catch(Exception e) {
            Toast.makeText(getApplicationContext(), "Oops! No internet access available!", Toast.LENGTH_SHORT).show();
        }
    }

    private void executeReq(URL urlObject) throws IOException
    {
        HttpURLConnection conn = null;
        conn = (HttpURLConnection) urlObject.openConnection();
        conn.setReadTimeout(30000);//milliseconds
        conn.setConnectTimeout(3500);//milliseconds
        conn.setRequestMethod("GET");
        conn.setDoInput(true);

        // Start connect
        conn.connect();
        InputStream response =conn.getInputStream();
        Log.d("Response:", response.toString());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_popular:
                loadJSON();
                break;

            case R.id.menu_rating:
                loadJSON1();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    private void initViews() {
        checkSortOrder();
    }

    private void loadJSON() {
        results = new ArrayList<>();
        adapter = new MoviesAdapter(getApplicationContext(), results);

        //Set layout manager for recyclerview
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setAdapter(adapter);
        recyclerView.setNestedScrollingEnabled(false);

        //from whatever layout i have in xml - then create string array in onResponse
        //final TextView textView = findViewById(R.id.recyclerview);

        //call the methods
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MovieInterface.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //now we have our retrofit object

        //then we build the api and then call the api
        //the create takes the interface class
        MovieInterface movieInterface = retrofit.create(MovieInterface.class);

        //call the getMovies object from the api
        Call<MovieModel> call = movieInterface.getPopularMovies(MovieInterface.API_KEY);

        //calling the api, takes a callback interface
        //type in new then hit control + space and it will do everything for me

        call.enqueue(new Callback<MovieModel>() {
            @Override
            public void onResponse(@NonNull Call<MovieModel> call, @NonNull Response<MovieModel> response) {
                //inside the response we want to get List type
                if (response.body() != null) {

                    List<Result> results = Arrays.asList(response.body().getResults());
                    if (getResources().getConfiguration().orientation ==
                            Configuration.ORIENTATION_PORTRAIT) {
                        recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));
                    } else {
                        recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, 4));
                    }
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setAdapter(new MoviesAdapter(getApplicationContext(), results));

                    String[] movieNames = new String[results.size()];

                    //then run loop
                    for (int i = 0; i < results.size(); i++) {
                        movieNames[i] = results.get(i).getOriginalTitle();
                    }

                    //we can display all the movie info in the log
                    for (Result h : results) {

                        Log.d("poster_path", h.getPosterPath());
                        Log.d("overview", h.getOriginalTitle());
                        Log.d("release_date", h.getReleaseDate());
                        Log.d("original_title", h.getOriginalTitle());
                        Log.d("vote_average", String.valueOf(h.getVoteAverage()));
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<MovieModel> call, @NonNull Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void initViews1() {
        checkSortOrder();
    }
    //for 2nd API call
    private void loadJSON1() {
        results = new ArrayList<>();
        adapter = new MoviesAdapter(getApplicationContext(), results);

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setAdapter(adapter);
        recyclerView.setNestedScrollingEnabled(false);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MovieInterface.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MovieInterface movieInterface = retrofit.create(MovieInterface.class);

        Call<MovieModel> call = movieInterface.getVoteAverage(MovieInterface.API_KEY);

        call.enqueue(new Callback<MovieModel>() {
            @Override
            public void onResponse(@NonNull Call<MovieModel> call, @NonNull Response<MovieModel> response) {
                //inside the response we want to get List type
                if (response.body() != null) {

                    List<Result> results = Arrays.asList(response.body().getResults());
                    if (getResources().getConfiguration().orientation ==
                            Configuration.ORIENTATION_PORTRAIT) {
                        recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));
                    } else {
                        recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, 4));
                    }
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setAdapter(new MoviesAdapter(getApplicationContext(), results));

                    String[] movieNames = new String[results.size()];

                    //then run loop
                    for (int i = 0; i < results.size(); i++) {
                        movieNames[i] = String.valueOf(results.get(i).getVoteAverage());
                    }
                    for (Result h : results) {
                        Log.d("vote_average", String.valueOf(h.getVoteAverage()));
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<MovieModel> call, @NonNull Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        Log.d(LOG_TAG, "Preferences updated.");
        checkSortOrder();

    }
    private void checkSortOrder() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String sortOrder = preferences.getString(
                this.getString(R.string.pref_sort_key),
                this.getString(R.string.pref_sort_popular));

        if (sortOrder.equals(this.getString(R.string.pref_sort_popular))) {
            Log.d(LOG_TAG, "Sort by most popular.");
            loadJSON();
        } else if (sortOrder.equals(this.getString(R.string.pref_sort_rating))) {
            Log.d(LOG_TAG, "Sort by highest rating.");
            loadJSON1();
        }
    }
    @Override
    public void onResume(){
        super.onResume();
        if (results.isEmpty()){
            checkSortOrder();
        } else {

            checkSortOrder();
        }

    }
}










