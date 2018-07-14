package movies.raemacias.com.movieappstage2.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

//@Serializedname is used for the gson to parse the json
//then create constructors and getter and setter methods

public class MovieModel {

    @SerializedName("results")
    @Expose
    private Result[] results;
    public Result[] getResults() {
        return results;
}

    @SerializedName("page")
    @Expose
    private int page;
    @SerializedName("total_results")
    @Expose
    private int totalResults;
    @SerializedName("total_pages")
    @Expose
    private int totalPages;
//    @SerializedName("results")
//    @Expose
//    private List<Result> results = new ArrayList<Result>();

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

//    public List<Result> getResults() {
//        return results;
//    }
//
//    public void setResults(List<Result> results) {
//        this.results = results;
//    }


}
