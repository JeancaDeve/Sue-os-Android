package com.codycod.dreamsreservation.data.services

import com.codycod.dreamsreservation.data.models.MdResponseApiReniec
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiReniecService {
    @GET("dni")
    fun getPeopleByDni(@Query("numero") number : String) : Call<MdResponseApiReniec>
}