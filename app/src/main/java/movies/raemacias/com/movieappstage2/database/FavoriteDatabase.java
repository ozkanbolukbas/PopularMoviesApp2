package movies.raemacias.com.movieappstage2.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.util.Log;

import movies.raemacias.com.movieappstage2.model.FavoriteEntry;


//This code for Room was implemented based on tutorials from David Gassner on Lynda.com
//and from the Udacity Architecture Components lessons.

@Database(entities = {FavoriteEntry.class}, version = 1)
public abstract class FavoriteDatabase extends RoomDatabase {

    private static final String LOG_TAG = FavoriteDatabase.class.getSimpleName();
    private static final Object LOCK = new Object();
    private static final String DATABASE_NAME = "favorite";
    private static FavoriteDatabase sInstance;

    public static FavoriteDatabase getFavoriteDatabase (Context context) {
        if (sInstance == null) {
            synchronized (LOCK) {
                Log.d(LOG_TAG, "Creating new database instance");
                sInstance = Room.databaseBuilder(context.getApplicationContext(),
                        FavoriteDatabase.class, FavoriteDatabase.DATABASE_NAME)
                        .build();
            }
        }
        Log.d(LOG_TAG, "Getting the database instance");
        return sInstance;
    }

    public abstract FavoriteItemDao getFavoriteItemDao();
    }


