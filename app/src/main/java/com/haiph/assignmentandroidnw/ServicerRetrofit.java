package com.haiph.assignmentandroidnw;

import com.haiph.assignmentandroidnw.model.Example;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ServicerRetrofit {

    @GET("wp-json/wp/v2/posts")
    Call<List<Example>> getExampleList(@Query("category") String category, @Query("per_page") String perPage, @Query("paging") String Paging);


}
