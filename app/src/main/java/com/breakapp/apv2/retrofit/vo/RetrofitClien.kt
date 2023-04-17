package com.breakapp.apv2.retrofit.vo

import com.breakapp.apv2.retrofit.WebServices
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClien {
    val APServices by lazy {
        Retrofit.Builder()
            .baseUrl("https://anonimuspizza.ga/Anonimus_pizza/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build().create(WebServices::class.java)
    }
}