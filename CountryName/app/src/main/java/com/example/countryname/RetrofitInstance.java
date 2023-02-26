package com.example.countryname;

import java.lang.annotation.Retention;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {
    private static Retrofit retrofit = null;
    public static RetrofitInstance retrofitInstance;
    private static String url ="https://api.printful.com/";


    RetrofitInstance(){

            retrofit = new Retrofit.Builder()
                    .baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build();
    }


    public static synchronized RetrofitInstance getInstance(){
            if (retrofitInstance == null){
                    retrofitInstance = new RetrofitInstance();
            }
            return retrofitInstance;
    }

    GetCountryDataService getApi(){
            return retrofit.create(GetCountryDataService.class);
    }

//    public static  GetCountryDataService getService(){
//
//        if(retrofit ==  null){
//            retrofit = new Retrofit.Builder()
//                    .baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build();
//
//        }
//
//
//        return retrofit.create(GetCountryDataService.class);
//
//    }


}
