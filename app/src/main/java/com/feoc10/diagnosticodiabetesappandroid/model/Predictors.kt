package com.feoc10.diagnosticodiabetesappandroid.model

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.RadioButton
import android.widget.Toast
import com.feoc10.diagnosticodiabetesappandroid.ApiRequests
import com.feoc10.diagnosticodiabetesappandroid.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.awaitResponse
import retrofit2.converter.gson.GsonConverterFactory

const val TAG_MODELS = "TAG Predictors"
const val BASE_URL = "http://192.168.100.69:5000"


class Predictors(private val context: Context) {


    suspend fun getPredictors(activity: Activity) {
        val progressBar = activity.findViewById<ProgressBar>(R.id.progressBar)
        progressBar.visibility = View.VISIBLE
        val radioButton1 = activity.findViewById<RadioButton>(R.id.radioButton1)
        val radioButton2 = activity.findViewById<RadioButton>(R.id.radioButton2)


        val api = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiRequests::class.java)

        try {
            val response = api.getModelos().awaitResponse()
            if (response.isSuccessful) {
                val data = response.body()
                Log.d(TAG_MODELS, data?.modelos.toString())

                withContext(Dispatchers.Main) {
                    progressBar.visibility = View.GONE

                    radioButton1.text = data?.modelos?.get(0)!!
                    radioButton1.visibility = View.VISIBLE

                    radioButton2.text = data.modelos[1]
                    radioButton2.visibility = View.VISIBLE
                }
            }
        } catch (e: Exception) {
            withContext(Dispatchers.Main) {
                Toast.makeText(
                    context.applicationContext,
                    "Serviço Fora do Ar...",
                    Toast.LENGTH_LONG
                )
                    .show()
            }
        }
    }
}