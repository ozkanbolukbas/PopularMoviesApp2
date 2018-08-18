package movies.raemacias.com.movieappstage2;

import android.annotation.TargetApi;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import com.like.LikeButton;
import com.like.OnLikeListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import movies.raemacias.com.movieappstage1.BuildConfig;
import movies.raemacias.com.movieappstage1.R;
import movies.raemacias.com.movieappstage2.adapter.ReviewAdapter;
import movies.raemacias.com.movieappstage2.adapter.TrailerAdapter;
import movies.raemacias.com.movieappstage2.api.Client;
import movies.raemacias.com.movieappstage2.api.MovieInterface;
import movies.raemacias.com.movieappstage2.database.FavoriteDatabase;
import movies.raemacias.com.movieappstage2.database.FavoriteItemDao;
import movies.raemacias.com.movieappstage2.model.FavoriteViewModel;
import movies.raemacias.com.movieappstage2.model.Result;
import movies.raemacias.com.movieappstage2.model.ReviewModel;
import movies.raemacias.com.movieappstage2.model.ReviewResult;
import movies.raemacias.com.movieappstage2.model.TrailerModel;
import movies.raemacias.com.movieappstage2.model.TrailerResult;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {

    private List<Result> results;

    private RecyclerView recyclerView;
    private TrailerAdapter adapter;

    RecyclerView mRecyclerView;
    List<ReviewResult> mResults;
    ReviewAdapter mReviewAdapter;
    Result favoriteResults;

    int movie_id;
    String reviews;
    String author;
    String content;
    LikeButton heartButton;

    FavoriteDatabase db;
    private FavoriteItemDao favoriteDatabaseDao;

    private final AppCompatActivity activity = DetailActivity.this;
    FavoriteViewModel mViewModel;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)

    Boolean isFavorite = false;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        db = FavoriteDatabase.getFavoriteDatabase(this);
        favoriteDatabaseDao = db.mFavoriteItemDao();


        Toolbar toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        TextView textViewOriginalTitle = findViewById(R.id.original_title_tv);
        ImageView imageViewMovieListItem = findViewById(R.id.movie_poster_iv);
        ImageView imageViewMovieThumb = findViewById(R.id.movie_thumb_iv);
        TextView textViewVoteAverage = findViewById(R.id.vote_average_tv);
        TextView textViewPlotSynopsis = findViewById(R.id.plot_synopsis_tv);
        TextView textViewReleaseDate = findViewById(R.id.release_tv);
        final LikeButton heartButton = findViewById(R.id.heart_button);

        Intent intent = getIntent();
        if (intent.hasExtra("original_title")) {

            String poster = getIntent().getExtras().getString("poster_path");
            String movieTitle = getIntent().getExtras().getString("original_title");
            String synopsis = getIntent().getExtras().getString("overview");
            String rating = getIntent().getExtras().getString("vote_average");
            String release = getIntent().getExtras().getString("release_date");
            movie_id = getIntent().getExtras().getInt("movie_id");
            reviews = getIntent().getExtras().getString("reviews");
            content = getIntent().getExtras().getString("content");
            author = getIntent().getExtras().getString("author");
            double voteAverage = 0;
            favoriteResults = new Result(movie_id,  movieTitle, poster, release, voteAverage, synopsis);

            mViewModel = ViewModelProviders.of(this).get(FavoriteViewModel.class);

            // Add an observer on the LiveData returned by getAlphabetizedWords.
            // The onChanged() method fires when the observed data changes and the activity is
            // in the foreground.
            mViewModel.getFavoriteItems().observe(this, new Observer<List<Result>>() {
                @Override
                public void onChanged(@Nullable final List<Result> favoriteEntries) {
                    for (Result item: favoriteEntries) {
                        if (item.getId() == favoriteResults.getId()) {
                            heartButton.setLiked(true);
                        }
                    }
                    // Update the cached copy of the words in the adapter.
                }
            });

            Picasso.get()
                    .load("http://image.tmdb.org/t/p/w342" + poster)
                    .placeholder(R.drawable.popcorn)
                    .into(imageViewMovieThumb);

            textViewOriginalTitle.setText(movieTitle);
            textViewVoteAverage.setText(rating);
            textViewPlotSynopsis.setText(synopsis);
            textViewReleaseDate.setText(release);
        } else {
            Toast.makeText(this, "Information not available.", Toast.LENGTH_SHORT).show();
        }
        loadJSON();
        loadJSON1();


        heartButton.setOnLikeListener(new OnLikeListener() {


            public void liked (LikeButton likeButton) {
                // Code here executes on main thread after user presses button
                final Result getFavoriteItems = new Result(favoriteResults.getId(), favoriteResults.getOriginalTitle(),
                        favoriteResults.getPosterPath(), favoriteResults.getReleaseDate(), favoriteResults.getVoteAverage(), favoriteResults.getOverview());

                AppExecutors.getInstance().diskIO().execute(new Runnable() {
                    @Override
                    public void run() {
                        db.mFavoriteItemDao().insertFavorite(getFavoriteItems);
//                        Log.d(TAG, insertFavoriteItems.getId() + " has been added to your favorites.");
                    }
                });

            }

            //This is not correct - need to get the Like working first.
            @Override
            public void unLiked(LikeButton likeButton) {

                final Result deleteFavoriteItems = new Result(favoriteResults.getId(), favoriteResults.getOriginalTitle(),
                        favoriteResults.getPosterPath(), favoriteResults.getReleaseDate(), favoriteResults.getVoteAverage(), favoriteResults.getOverview());

                AppExecutors.getInstance().diskIO().execute(new Runnable() {
                    @Override
                    public void run() {
                        favoriteDatabaseDao.deleteFavorite(deleteFavoriteItems);
                        Log.d(TAG, deleteFavoriteItems.getOriginalTitle() + " has been deleted from your favorites.");
                    }
                });

            }

            public static final String TAG = "Detail Activity";


        });


    }


    private void loadJSON() {

        List<TrailerResult> trailerResult = new ArrayList<>();
        adapter = new TrailerAdapter(this, trailerResult);

        recyclerView = findViewById(R.id.recyclerview_trailer);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);

        try {

            Client Client = new Client();
            MovieInterface movieInterface = Client.getClient().create(MovieInterface.class);
            Call<TrailerModel> call = movieInterface.getMovieTrailer(movie_id, BuildConfig.API_KEY);
            call.enqueue(new Callback<TrailerModel>() {

                @Override
                public void onResponse(Call<TrailerModel> call, Response<TrailerModel> response) {
                    if (response.message().contentEquals("OK")) {
                        List<TrailerResult> results = response.body().getResults();
                        recyclerView.setAdapter(new TrailerAdapter(getApplicationContext(), results));
                        recyclerView.smoothScrollToPosition(0);
                    }
                }

                @Override
                public void onFailure(Call<TrailerModel> call, Throwable t) {
                    Log.d("Error", t.getMessage());
                    Toast.makeText(DetailActivity.this, "Error fetching trailer data", Toast.LENGTH_SHORT).show();

                }
            });

        } catch (Exception e) {
            Log.d("Error", e.getMessage());
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    private void loadJSON1() {


        List<ReviewResult> mResults = new ArrayList<>();
        mReviewAdapter = new ReviewAdapter(this, mResults);

        mRecyclerView = findViewById(R.id.recyclerview_reviews);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        try {

            Client Client = new Client();
            MovieInterface movieInterface = Client.getClient().create(MovieInterface.class);
            //when calling movie_id only it overrides the trailer space on app
            //when only calling reviews, nothing shows up on app
            Call<ReviewModel> call = movieInterface.getReviewList(movie_id, BuildConfig.API_KEY);
            call.enqueue(new Callback<ReviewModel>() {

                @Override
                public void onResponse(Call<ReviewModel> call, Response<ReviewModel> response) {
                    if (response.message().contentEquals("OK")) {

                        List<ReviewResult> mResults = response.body().getReviewList();
                        mRecyclerView.setAdapter(new ReviewAdapter(getApplicationContext(), mResults));
                        mRecyclerView.smoothScrollToPosition(0);
                    }
                }

                @Override
                public void onFailure(Call<ReviewModel> call, Throwable t) {
                    Log.d("Error", t.getMessage());
                    Toast.makeText(DetailActivity.this, "Error fetching review data", Toast.LENGTH_SHORT).show();

                }
            });

        } catch (Exception e) {
            Log.d("Error", e.getMessage());
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }

    }
}