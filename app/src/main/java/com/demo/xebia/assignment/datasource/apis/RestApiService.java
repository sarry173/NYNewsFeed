package com.demo.xebia.assignment.datasource.apis;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RestApiService
{
    @GET("{count}.json")
    Observable<String> getNewsList(@Path("count") String count, @Query("api-key") String apiKey);
}