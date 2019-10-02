package com.example.submission5_v2.API;

import com.example.submission5_v2.entity.DetailMovie;
import com.example.submission5_v2.entity.MovieResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiTvInterface {

    @GET("tv/{tv_id}")
    Call<DetailMovie> getDetailMovie(@Path("tv_id") int movie_id, @Query("api_key") String apiKey);

    @GET("search/tv")
    Call<MovieResponse> getSearchMovies(@Query("api_key") String apiKey, @Query("language") String language, @Query("query") String querySearch);

    @GET("tv/popular")
    Call<MovieResponse> getPopularMovies(@Query("api_key") String apiKey, @Query("language") String language, @Query("page") int page, @Query("region") String region);

    @GET("tv/airing_today")
    Call<MovieResponse> getAiringMovies(@Query("api_key") String apiKey, @Query("language") String language, @Query("page") int page, @Query("region") String region);
}
