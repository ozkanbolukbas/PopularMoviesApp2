package movies.raemacias.com.movieappstage2.database;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

import movies.raemacias.com.movieappstage2.model.Result;

//The use of this repository adds another layer of abstraction
//so the view model doesn't know what kind of database it's talking to.
//"Data hiding in practice."

public class FavoriteItemRepository {

    private final FavoriteItemDao favoriteItemDao;
    private LiveData<List<Result>> favoriteResults;

    public FavoriteItemRepository(Application application) {
        FavoriteDatabase db = FavoriteDatabase.getFavoriteDatabase(application);
        favoriteItemDao = db.mFavoriteItemDao();
        favoriteResults = favoriteItemDao.loadAllFavorites();
    }

    public LiveData<List<Result>> getFavoriteItems() {

        return favoriteResults;
    }


    public void insert (Result favoriteResults) {
        new insertAsyncTask(favoriteItemDao).execute(favoriteResults);
    }

    private static class insertAsyncTask extends AsyncTask<Result, Void, Void> {

        private FavoriteItemDao mAsyncTaskDao;

        insertAsyncTask(FavoriteItemDao dao) {

            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Result... params) {
            mAsyncTaskDao.insertFavorite(params[0]);
            return null;
        }

    }
}
