package movies.raemacias.com.movieappstage2;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
import movies.raemacias.com.movieappstage2.adapter.FavoriteAdapter;
import movies.raemacias.com.movieappstage2.adapter.ReviewAdapter;
import movies.raemacias.com.movieappstage2.adapter.TrailerAdapter;
import movies.raemacias.com.movieappstage2.api.Client;
import movies.raemacias.com.movieappstage2.api.MovieInterface;
import movies.raemacias.com.movieappstage2.database.FavoriteDatabase;
import movies.raemacias.com.movieappstage2.database.FavoriteItemRepository;
import movies.raemacias.com.movieappstage2.model.FavoriteEntry;
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

    int movie_id;
    String reviews;
    String author;
    String content;
    LikeButton heartButton;

    private FavoriteDatabase db;

    private final AppCompatActivity activity = DetailActivity.this;
    private FavoriteEntry favoriteEntry;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


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
            favoriteEntry = new FavoriteEntry(movieTitle);


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

        heartButton = (LikeButton)findViewById(R.id.heart_button);
        heartButton.setOnLikeListener(new OnLikeListener() {
            @Override
            public void liked(LikeButton likeButton) {
                FavoriteItemRepository favoriteItemRepository = new FavoriteItemRepository(getApplication());
                favoriteItemRepository.insert(favoriteEntry);
            }

            @Override
            public void unLiked(LikeButton likeButton) {

            }
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