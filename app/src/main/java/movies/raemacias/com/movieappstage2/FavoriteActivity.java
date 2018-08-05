package movies.raemacias.com.movieappstage2;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;

import java.util.List;

import movies.raemacias.com.movieappstage1.R;
import movies.raemacias.com.movieappstage2.adapter.FavoriteAdapter;
import movies.raemacias.com.movieappstage2.database.FavoriteDatabase;
import movies.raemacias.com.movieappstage2.database.FavoriteItemDao;
import movies.raemacias.com.movieappstage2.model.FavoriteEntry;
import movies.raemacias.com.movieappstage2.model.FavoriteViewModel;


//Code implemented from the Udacity Architecture Components lessons.

public class FavoriteActivity extends AppCompatActivity implements FavoriteAdapter.ItemClickListener {

    private static final String TAG = FavoriteActivity.class.getSimpleName();
    private RecyclerView mRecyclerView;
    private FavoriteAdapter mAdapter;
    private FavoriteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        mRecyclerView = findViewById(R.id.rv_favorites);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize the adapter and attach it to the RecyclerView
        mAdapter = new FavoriteAdapter(this, this);
        mRecyclerView.setAdapter(mAdapter);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            // Called when a user swipes left or right on a ViewHolder
            @Override
            public void onSwiped(final RecyclerView.ViewHolder viewHolder, int swipeDir) {
                // Here is where you'll implement swipe to delete
                AppExecutors.getInstance().diskIO().execute(new Runnable() {
                    @Override
                    public void run() {
                        int position = viewHolder.getAdapterPosition();
                        List<FavoriteEntry> tasks = mAdapter.getFavorites();
                        db.getFavoriteItemDao().deleteTask(tasks.get(position));
                    }
                });
            }
        }).attachToRecyclerView(mRecyclerView);

        db = FavoriteDatabase.getFavoriteDatabase(getApplicationContext());
        setupViewModel();
    }

    private void setupViewModel() {
        FavoriteViewModel viewModel = ViewModelProviders.of(this).get(FavoriteViewModel.class);
        viewModel.getFavorites().observe(this, new Observer<List<FavoriteEntry>>() {
            @Override
            public void onChanged(@Nullable List<FavoriteEntry> favoriteEntries) {
                Log.d(TAG, "Updating list of movies from LiveData in ViewModel");
                mAdapter.setFavorites(favoriteEntries);
            }
        });
    }

    @Override
    public void onItemClickListener(String movie_id) {
        Intent intent = new Intent(FavoriteActivity.this, ButtonActivity.class);
        intent.putExtra(ButtonActivity.FAVORITE_ID, movie_id);
        startActivity(intent);

    }
}