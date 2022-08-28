package com.example.countriesapp.service

import com.example.countriesapp.model.Country
import io.reactivex.Single

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class CountryAPIService {

    private val baseUrl = "https://raw.githubusercontent.com/"

    private val api = Retrofit.Builder()
        .baseUrl(baseUrl)

        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(CountryAPI::class.java)

    fun getData() : Single<List<Country>> {
     return api.getCountries()
    }
}