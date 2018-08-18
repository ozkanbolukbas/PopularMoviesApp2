package movies.raemacias.com.movieappstage2.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import movies.raemacias.com.movieappstage2.model.Result;

//This code was generate by watching the tutorial from wiseAss on YouTube.
//https://www.youtube.com/watch?v=LCOKWgHdBvE&t=170s
@Dao
public interface FavoriteItemDao {

    @Query("SELECT * FROM favorites")
    LiveData<List<Result>> loadAllFavorites();

    @Query("SELECT * FROM favorites where id= :movie_id")
    LiveData<Result> loadFavoriteResults (int movie_id);

    @Insert
    void insertFavorite(Result favoriteResults);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateFavorite(Result favoriteResults);

    @Delete
    void deleteFavorite(Result favoriteResults);
}