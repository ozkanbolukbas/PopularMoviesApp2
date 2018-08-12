package movies.raemacias.com.movieappstage2.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import movies.raemacias.com.movieappstage2.model.FavoriteEntry;
import movies.raemacias.com.movieappstage2.model.Result;

//This code was generate by watching the tutorial from wiseAss on YouTube.
//https://www.youtube.com/watch?v=LCOKWgHdBvE&t=170s
@Dao
public interface FavoriteItemDao {

    @Query("SELECT * FROM FavoriteEntry ORDER BY id")
    LiveData<List<FavoriteEntry>> getFavoriteItems();



    @Query("SELECT * FROM FavoriteEntry WHERE id = :id")
    LiveData<FavoriteEntry> getFavoriteItemById(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertFavoriteItems(FavoriteEntry favoriteEntry);

    @Delete
    void deleteFavoriteItems(FavoriteEntry favoriteEntry);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateFavoriteItems(FavoriteEntry favoriteEntry);


}
