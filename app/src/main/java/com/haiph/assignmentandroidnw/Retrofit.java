package com.haiph.assignmentandroidnw;

import com.haiph.assignmentandroidnw.model.Example;

import java.util.List;

import retrofit2.Call;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class Retrofit {

    public static ServicerRetrofit service;

    public static ServicerRetrofit getInstance(){

        if (service == null){
            retrofit2.Retrofit retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl("http://asian.dotplays.com")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            service  =  retrofit.create(ServicerRetrofit.class);
        }

        return service;
    }
  }
