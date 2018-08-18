package movies.raemacias.com.movieappstage2.model;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

import movies.raemacias.com.movieappstage2.database.FavoriteItemRepository;

public class FavoriteViewModel extends AndroidViewModel {

    private static final String TAG = FavoriteViewModel.class.getSimpleName();
    private FavoriteItemRepository mRepository;

    private LiveData<List<Result>> favoriteResults;

    public FavoriteViewModel(Application application) {
        super(application);
        mRepository = new FavoriteItemRepository(application);
        favoriteResults = mRepository.getFavoriteItems();
    }


    public LiveData<List<Result>> getFavoriteItems() {
        return favoriteResults;
    }

    public void insert(Result favoriteResults) {
            mRepository.insert(favoriteResults);
    }
}


