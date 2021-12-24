package com.jaikeerthick.lovecalculator.api

import com.jaikeerthick.lovecalculator.ui.model.LoveResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface APIHub {


    @Headers("x-rapidapi-host:love-calculator.p.rapidapi.com",
    "x-rapidapi-key:YOUR_API_KEY")
    @GET(".")
    suspend fun getPercentage(
        @Query("fname") fName : String,
        @Query("sname") sName : String,
    ) : LoveResponse

}