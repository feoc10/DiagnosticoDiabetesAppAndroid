package com.feoc10.diagnosticodiabetesappandroid.model

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import com.feoc10.diagnosticodiabetesappandroid.ApiRequests
import com.feoc10.diagnosticodiabetesappandroid.R
import com.feoc10.diagnosticodiabetesappandroid.api.PrevisaoJson
import com.feoc10.diagnosticodiabetesappandroid.api.ResponseJson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.awaitResponse
import retrofit2.converter.gson.GsonConverterFactory

const val TAG_PREDICTION = "TAG Prediction"

class Predictions(private val context: Context) {

    suspend fun predictions(activity: Activity, modelo: String, json: PrevisaoJson) {
        val progressBar = activity.findViewById<ProgressBar>(R.id.progressBar_resultActivity)
        progressBar.visibility = View.VISIBLE
        val api = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiRequests::class.java)
        try {
            val response: Response<ResponseJson> = api.postmodel(modelo, json).awaitResponse()
            if (response.isSuccessful) {
                val data: ResponseJson? = response.body()
                Log.d(TAG_PREDICTION, data?.code.toString())
                Log.d(TAG_PREDICTION, data?.diabetes.toString())

                withContext(Dispatchers.Main) {
                    progressBar.visibility = View.GONE
                    activity.findViewById<TextView>(R.id.resultadoTextView).text =
                        data?.diabetes.toString()
                }
            }
        } catch (e: Exception) {
            withContext(Dispatchers.Main) {
                progressBar.visibility = View.GONE
                activity.findViewById<TextView>(R.id.resultadoTextView).text = "Sem Previsão"
                Toast.makeText(
                    context.applicationContext,
                    "Serviço Fora do Ar...",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}