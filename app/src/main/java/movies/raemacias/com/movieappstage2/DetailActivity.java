package movies.raemacias.com.movieappstage2;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;
import com.squareup.picasso.Picasso;
import java.util.List;

import movies.raemacias.com.movieappstage1.R;
import movies.raemacias.com.movieappstage2.model.Result;

public class DetailActivity extends AppCompatActivity {

    private List<Result> results;

    private TextView textViewOriginalTitle;
    private TextView textViewVoteAverage;
    private TextView textViewPlotSynopsis;
    private TextView textViewReleaseDate ;
    private ImageView imageViewMovieListItem;
    private ImageView imageViewMovieThumb;

    private final AppCompatActivity activity = DetailActivity.this;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);

        if(getSupportActionBar() != null){ getSupportActionBar().setDisplayHomeAsUpEnabled(true); }

        textViewOriginalTitle = findViewById(R.id.original_title_tv);
        imageViewMovieListItem = findViewById(R.id.movie_poster_iv);
        imageViewMovieThumb = findViewById(R.id.movie_thumb_iv);
        textViewVoteAverage = findViewById(R.id.vote_average_tv);
        textViewPlotSynopsis = findViewById(R.id.plot_synopsis_tv);
        textViewReleaseDate = findViewById(R.id.release_tv);

        Intent intent = getIntent();
        if (intent.hasExtra("original_title")) {

            String poster = getIntent().getExtras().getString("poster_path");
            String movieTitle = getIntent().getExtras().getString("original_title");
            String synopsis = getIntent().getExtras().getString("overview");
            String rating = getIntent().getExtras().getString("vote_average");
            String release = getIntent().getExtras().getString("release_date");

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
    }

}

