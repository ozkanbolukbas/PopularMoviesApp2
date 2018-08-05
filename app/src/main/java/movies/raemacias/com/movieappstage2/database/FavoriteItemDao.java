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

@Dao
public interface FavoriteItemDao {

    @Query("SELECT * FROM favorite ORDER BY id")
    LiveData<List<FavoriteEntry>> getAllFavorites();

    @Insert
    void insertTask(FavoriteEntry favoriteEntry);


    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateTask(FavoriteEntry favoriteEntry);

    @Delete
    void deleteTask(FavoriteEntry favoriteEntry);

}
