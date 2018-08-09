package movies.raemacias.com.movieappstage2.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class FavoriteEntry {

        @PrimaryKey
        private int id;

        @ColumnInfo
        private String original_title;

        @ColumnInfo
        private String poster_path;

        @ColumnInfo
        private String release_date;

        @ColumnInfo
        private String rating;

        @ColumnInfo
        private String overview;

        @Ignore
        public FavoriteEntry(String original_title) {
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

    public void setId(int id) {
        this.id = id;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public int getId() {
            return id;
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
