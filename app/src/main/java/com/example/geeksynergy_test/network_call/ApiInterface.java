package com.example.geeksynergy_test.network_call;

import com.example.geeksynergy_test.model.MovieModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("/3/movie/popular")
    Call<MovieModel> getMoviesList(@Query("api_key") String title);
}