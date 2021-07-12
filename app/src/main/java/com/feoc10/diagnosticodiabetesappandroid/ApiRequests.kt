package com.feoc10.diagnosticodiabetesappandroid

import com.feoc10.diagnosticodiabetesappandroid.api.PrevisaoJson
import com.feoc10.diagnosticodiabetesappandroid.api.ResponseJson
import com.feoc10.diagnosticodiabetesappandroid.api.ModelosJson
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiRequests {

    @GET("/modelos")
    fun getModelos(): Call<ModelosJson>

    @POST("/xgboost")
    fun postXGBoost(@Body previsaoJson: PrevisaoJson):Call<ResponseJson>

    @POST("/rdf")
    fun postRandomForest(@Body previsaoJson: PrevisaoJson):Call<ResponseJson>
}