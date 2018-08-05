package movies.raemacias.com.movieappstage2.model;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.util.Log;

import java.util.List;

import movies.raemacias.com.movieappstage2.database.FavoriteDatabase;

public class FavoriteViewModel extends AndroidViewModel {

    private static final String TAG = FavoriteViewModel.class.getSimpleName();

    private LiveData<List<FavoriteEntry>> favorites;

    public FavoriteViewModel(Application application) {
        super(application);
        FavoriteDatabase db = FavoriteDatabase.getFavoriteDatabase(this.getApplication());
        this.favorites = db.getFavoriteItemDao().getAllFavorites();
    }


    public LiveData<List<FavoriteEntry>> getFavorites() {
        return favorites;
    }
}
