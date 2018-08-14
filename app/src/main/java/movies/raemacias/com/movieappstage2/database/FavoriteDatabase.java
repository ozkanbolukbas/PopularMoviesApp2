package movies.raemacias.com.movieappstage2.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import movies.raemacias.com.movieappstage2.model.FavoriteEntry;


//This code for Room was implemented based on tutorials from David Gassner on Lynda.com
//and from the Udacity Architecture Components lessons.
//Also used wiseAss from YouTube.
//REVISED - code implemented from CodeLabs, Room with a View


//It is important that this class is abstract
//Can also use export schema. Need to update versions as table needs updating, otherwise
//users can lose all their data. NOT good!

@Database(entities = {FavoriteEntry.class}, version = 1)
public abstract class FavoriteDatabase extends RoomDatabase {


    public abstract FavoriteItemDao mFavoriteItemDao();

    private static FavoriteDatabase INSTANCE;

    public static FavoriteDatabase getFavoriteDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (FavoriteDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            FavoriteDatabase.class, "favorite_database")
                            // Wipes and rebuilds instead of migrating if no Migration object.
//                            .fallbackToDestructiveMigration()
//                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}