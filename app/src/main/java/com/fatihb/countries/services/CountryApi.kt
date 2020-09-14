package com.fatihb.countries.services

import com.fatihb.countries.model.Country
import io.reactivex.Single
import retrofit2.http.GET

//https://raw.githubusercontent.com/atilsamancioglu/IA19-DataSetCountries/master/countrydataset.json

interface CountryApi {
    @GET("atilsamancioglu/IA19-DataSetCountries/master/countrydataset.json")
    fun getCountries():Single<List<Country>>
}