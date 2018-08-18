//package movies.raemacias.com.movieappstage2;
//
//import android.arch.lifecycle.Observer;
//import android.arch.lifecycle.ViewModelProviders;
//import android.os.Bundle;
//import android.support.annotation.Nullable;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.support.v7.widget.Toolbar;
//
//import java.util.List;
//
//import movies.raemacias.com.movieappstage1.R;
//import movies.raemacias.com.movieappstage2.adapter.MoviesAdapter;
//import movies.raemacias.com.movieappstage2.model.FavoriteViewModel;
//import movies.raemacias.com.movieappstage2.model.Result;
//
//
////Code implemented from the Udacity Architecture Components lessons.
//
//public class FavoriteActivity extends AppCompatActivity {
//
//
//    private FavoriteViewModel mViewModel;
//    MoviesAdapter adapter;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_favorite);
//
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        RecyclerView recyclerView = findViewById(R.id.rv_favorites);
//        final MoviesAdapter adapter = new MoviesAdapter(this);
//        recyclerView.setAdapter(adapter);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//
////        final LinearLayoutManager layoutManager = new LinearLayoutManager(context);
////        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
////        recyclerView.setLayoutManager(layoutManager);
//
//        // Get a new or existing ViewModel from the ViewModelProvider.
//        mViewModel = ViewModelProviders.of(this).get(FavoriteViewModel.class);
//
//        // Add an observer on the LiveData returned by getAlphabetizedWords.
//        // The onChanged() method fires when the observed data changes and the activity is
//        // in the foreground.
//        mViewModel.getFavoriteItems().observe(this, new Observer<List<Result>>() {
//            @Override
//            public void onChanged(@Nullable final List<Result> favoriteResults) {
//                // Update the cached copy of the words in the adapter.
//                adapter.setId(favoriteResults);
//            }
//        });
//
//    }
//
////    @Override
////    public boolean onCreateOptionsMenu(Menu menu) {
////        getMenuInflater().inflate(R.menu.menu, menu);
////        return true;
////    }
//}
//
////    @Override
////    public boolean onOptionsItemSelected(MenuItem item) {
////        int id = item.getItemId();
////        if (id == R.id.menu_favorite) {
////            return true;
////        }
////        return super.onOptionsItemSelected(item);
////    }
////}
//
//
//
//
//
