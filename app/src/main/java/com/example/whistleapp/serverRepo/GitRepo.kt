package com.example.whistleapp.serverRepo

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GitRepo {



    companion object{

        private val logging = HttpLoggingInterceptor();
        private var httpClient :OkHttpClient.Builder
        init {
            logging.level = HttpLoggingInterceptor.Level.BODY;
            httpClient =  OkHttpClient.Builder()
            httpClient.addInterceptor(logging)
        }


        private val retrofit : Retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        val gitService : GitHubService by lazy (LazyThreadSafetyMode.SYNCHRONIZED) { retrofit.create(GitHubService::class.java)}
    }



}








