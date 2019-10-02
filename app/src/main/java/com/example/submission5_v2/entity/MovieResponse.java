package com.example.submission5_v2.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieResponse {

    @SerializedName("page")
    @Expose
    private Integer page;
    @SerializedName("total_results")
    @Expose
    private Integer totalResults;
    @SerializedName("total_pages")
    @Expose
    private Integer totalPages;
    @SerializedName("results")
    @Expose
    private List<MovieResult> MovieResults = null;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public List<MovieResult> getMovieResults() {
        return MovieResults;
    }

    public void setMovieResults(List<MovieResult> movieResults) {
        MovieResults = movieResults;
    }

    public MovieResponse(Integer page, Integer totalResults, Integer totalPages, List<MovieResult> movieResults) {
        this.page = page;
        this.totalResults = totalResults;
        this.totalPages = totalPages;
        MovieResults = movieResults;
    }
}
