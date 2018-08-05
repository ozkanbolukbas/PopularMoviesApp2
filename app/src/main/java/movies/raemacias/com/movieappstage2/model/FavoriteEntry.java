package movies.raemacias.com.movieappstage2.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "favorite")
public class FavoriteEntry {

        @PrimaryKey
        @NonNull
        @ColumnInfo
        private int id;
        private String original_title;
        private String poster_path;
        private String release_date;
        private String rating;
        private String overview;

        @Ignore
        public FavoriteEntry(String original_title, String poster_path, String release_date, String rating, String overview) {
        this.original_title = original_title;
        this.poster_path = poster_path;
        this.release_date = release_date;
        this.rating = rating;
        this.overview = overview;
    }

        public FavoriteEntry(@NonNull int id, String original_title, String poster_path, String release_date, String rating, String overview) {
            this.id = id;
            this.original_title = original_title;
            this.poster_path = poster_path;
            this.release_date = release_date;
            this.rating = rating;
            this.overview = overview;
        }

        public int getId() {
            return this.id;
        }

        public String getOriginal_title() {
            return this.original_title;
        }

        public String getPoster_path() {
            return this.poster_path;
        }

        public String getRelease_date() {
            return this.release_date;
        }

        public String getRating() {
            return this.rating;
        }

        public String getOverview() {
            return this.overview;
        }
}
