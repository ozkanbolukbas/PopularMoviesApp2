package movies.raemacias.com.movieappstage2.model;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

import movies.raemacias.com.movieappstage2.database.FavoriteItemRepository;

public class FavoriteViewModel extends AndroidViewModel {

    private static final String TAG = FavoriteViewModel.class.getSimpleName();
    private FavoriteItemRepository mRepository;

    private LiveData<List<FavoriteEntry>> favoriteEntry;

    public FavoriteViewModel(Application application) {
        super(application);
        mRepository = new FavoriteItemRepository(application);
        favoriteEntry = mRepository.getFavoriteItems();
    }


    public LiveData<List<FavoriteEntry>> getFavoriteItems() {
        return favoriteEntry;
    }

    public void insert(FavoriteEntry favoriteEntry) {
            mRepository.insert(favoriteEntry);
    }
}


