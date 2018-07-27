package movies.raemacias.com.movieappstage2.api;

import movies.raemacias.com.movieappstage1.BuildConfig;
import movies.raemacias.com.movieappstage2.model.MovieModel;
//import movies.raemacias.com.movieappstage1.model.MovieResponse;
import movies.raemacias.com.movieappstage2.model.ReviewModel;
import movies.raemacias.com.movieappstage2.model.TrailerModel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieInterface {

    //The interface defines the possible HTTP operations

    //define base URL
    String BASE_URL = "http://api.themoviedb.org/3/movie/";
    String API_KEY = BuildConfig.API_KEY;
    String POPULAR = "popular";
    String TOP_RATED = "top_rated";
    String POSTER_WIDTH = "w185/";
    String TITLE = "original_title";
    String PLOT = "overview";
    String RELEASE_DATE = "release_date";
    String POSTER_PATH = "poster_path";

    //http request - adds to end of base url
    //add a method - call <type> e.g. array would be list,
    // type of list, list of type movie
    //can name get anything I want

    @GET("popular")
    Call<MovieModel> getPopularMovies(
            @Query("api_key") String api_key);

    @GET("top_rated")
    Call<MovieModel> getVoteAverage(
            @Query("api_key") String api_key);

    @GET("{movie_id}/reviews")
    Call<ReviewModel> getReviewList(@Path("movie_id") int id, String reviews, @Query("api_key") String api_key);

    @GET("{movie_id}/videos")
    Call<TrailerModel> getMovieTrailer(@Path("movie_id") int id, @Query("api_key") String api_key);


}
