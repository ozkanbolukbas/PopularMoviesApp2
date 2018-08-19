package movies.raemacias.com.movieappstage2.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ReviewModel {

    @SerializedName("results")
    @Expose
    private List<ReviewResult> mResults = null;

    public List<ReviewResult> getReviewList() {
        return mResults;


    }
}
