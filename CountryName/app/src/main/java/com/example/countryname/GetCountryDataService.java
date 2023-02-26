package com.example.countryname;

import com.example.countryname.Model.Result;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetCountryDataService {
    @GET("countries")
    Call<Result> getResult();
}
