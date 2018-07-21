package movies.raemacias.com.movieappstage2.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TrailerModel {

    @SerializedName("id")
    @Expose
    private int id_trailer;

    public int getId_trailer() {
        return id_trailer;
    }

    public void setId_trailer(int id_trailer) {
        this.id_trailer = id_trailer;
    }

    public List<TrailerResult> getResults() {
        return results;
    }

    public void setResults(List<TrailerResult> results) {
        this.results = results;
    }

    @SerializedName("results")
    @Expose
    private List<TrailerResult> results;

}

