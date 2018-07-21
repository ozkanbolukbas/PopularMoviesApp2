package movies.raemacias.com.movieappstage2.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReviewResult {

    public String getContent() {
        return content;
    }

    public String getAuthor() {
        return author;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @SerializedName("content")
    @Expose
    private String content;

    @SerializedName("author")
    @Expose
    private String author;



}
