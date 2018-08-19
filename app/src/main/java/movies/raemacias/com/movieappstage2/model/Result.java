package movies.raemacias.com.movieappstage2.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "favorites")
public class Result {

    @PrimaryKey
    @SerializedName("id")
    @Expose
    private int id;

    @ColumnInfo (name = "original_title")
    @SerializedName("original_title")
    @Expose
    private String originalTitle;

    @ColumnInfo
    @SerializedName("overview")
    @Expose
    private String overview;

    @ColumnInfo
    @SerializedName("release_date")
    @Expose
    private String releaseDate;

    @ColumnInfo
    @SerializedName("poster_path")
    @Expose
    private String posterPath;

    @ColumnInfo
    @SerializedName("vote_count")
    @Expose
    private int voteCount;

    @ColumnInfo
    @SerializedName("video")
    @Expose
    private boolean video;

    @ColumnInfo
    @SerializedName("vote_average")
    @Expose
    private double voteAverage;

    @ColumnInfo
    @SerializedName("title")
    @Expose
    private String title;

    @ColumnInfo
    @SerializedName("popularity")
    @Expose
    private double popularity;

    @ColumnInfo
    @SerializedName("original_language")
    @Expose
    private String originalLanguage;

    @ColumnInfo
    @SerializedName("backdrop_path")
    @Expose
    private String backdropPath;

    @ColumnInfo
    @SerializedName("adult")
    @Expose
    private boolean adult;

    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isVideo() {
        return video;
    }

    public void setVideo(boolean video) {
        this.video = video;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Result(@NonNull int id, String originalTitle, String posterPath, String releaseDate, double voteAverage, String overview) {
        this.id = id;
        this.originalTitle = originalTitle;
        this.posterPath = posterPath;
        this.releaseDate = releaseDate;
        this.voteAverage = voteAverage;
        this.overview = overview;
    }
}
