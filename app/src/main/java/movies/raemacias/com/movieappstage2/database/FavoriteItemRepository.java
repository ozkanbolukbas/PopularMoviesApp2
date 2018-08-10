package movies.raemacias.com.movieappstage2.database;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

import movies.raemacias.com.movieappstage2.model.FavoriteEntry;

//The use of this repository adds another layer of abstraction
//so the view model doesn't know what kind of database it's talking to.
//"Data hiding in practice."

public class FavoriteItemRepository {

    private final FavoriteItemDao favoriteItemDao;
    private LiveData<List<FavoriteEntry>> favoriteEntry;

    public FavoriteItemRepository(Application application) {
        FavoriteDatabase db = FavoriteDatabase.getFavoriteDatabase(application);
        favoriteItemDao = db.mFavoriteItemDao();
        favoriteEntry = favoriteItemDao.getFavoriteItems();
    }

    public LiveData<List<FavoriteEntry>> getFavoriteItems() {

        return favoriteEntry;
    }


    public void insert (FavoriteEntry favoriteEntry) {
        new insertAsyncTask(favoriteItemDao).execute(favoriteEntry);
    }

    private static class insertAsyncTask extends AsyncTask<FavoriteEntry, Void, Void> {

        private FavoriteItemDao mAsyncTaskDao;

        insertAsyncTask(FavoriteItemDao dao) {

            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final FavoriteEntry... params) {
            mAsyncTaskDao.insertFavoriteItem(params[0]);
            return null;
        }

    }
}
