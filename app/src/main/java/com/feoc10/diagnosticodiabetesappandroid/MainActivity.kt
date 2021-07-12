package com.feoc10.diagnosticodiabetesappandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import com.feoc10.diagnosticodiabetesappandroid.api.PrevisaoJson
import com.feoc10.diagnosticodiabetesappandroid.api.ResponseJson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.awaitResponse
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "http://192.168.100.69:5000"

class MainActivity : AppCompatActivity() {
    private var TAG_MODELS = "getModels"
    private var TAG_PREDICTION = "getPredictions"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getModels(findViewById(android.R.id.content))
//        getPredictions(findViewById(android.R.id.content))

    }

    fun getModels(view: View) {
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        progressBar.visibility = View.VISIBLE
        val radioButton1 = findViewById<RadioButton>(R.id.radioButton1)
        val radioButton2 = findViewById<RadioButton>(R.id.radioButton2)


        val api = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiRequests::class.java)

        GlobalScope.launch(Dispatchers.IO) {
            try {
                val response = api.getModelos().awaitResponse()
                if (response.isSuccessful) {
                    val data = response.body()
                    Log.d(TAG_MODELS, data?.modelos.toString())

                    withContext(Dispatchers.Main) {
                        progressBar.visibility = View.GONE

                        radioButton1.text = data?.modelos?.get(0)!!
                        radioButton1.visibility= View.VISIBLE

                        radioButton2.text = data.modelos[1]
                        radioButton2.visibility= View.VISIBLE
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(applicationContext, "Serviço Fora do Ar...", Toast.LENGTH_LONG)
                        .show()
                }
            }
        }
    }

    fun getPredictions(view: View) {
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        progressBar.visibility = View.VISIBLE
        val previsao = findViewById<TextView>(R.id.previsao)
        val radioButton1 = findViewById<RadioButton>(R.id.radioButton1)
        val radioButton2 = findViewById<RadioButton>(R.id.radioButton2)


        val api = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiRequests::class.java)

        GlobalScope.launch(Dispatchers.IO) {
            try {

                val response: Response<ResponseJson>
                if (radioButton1.isChecked) {
                    response = api.postXGBoost(
                        PrevisaoJson(
                            colesterol_ruim = 193,
                            glucose = 1000,
                            colesterol_bom = 49,
                            idade = 19,
                            peso = 119,
                            altura = 1.55,
                            pressao_arterial = "118x70",
                            cintura = 81.28
                        )
                    ).awaitResponse()
                }else{
                    response = api.postRandomForest(
                        PrevisaoJson(
                            colesterol_ruim = 193,
                            glucose = 90,
                            colesterol_bom = 49,
                            idade = 19,
                            peso = 119,
                            altura = 1.55,
                            pressao_arterial = "118x70",
                            cintura = 81.28
                        )
                    ).awaitResponse()
                }
                if (response.isSuccessful) {
                    val data:ResponseJson? = response.body()
                    Log.d(TAG_PREDICTION, data?.code.toString())
                    Log.d(TAG_PREDICTION, data?.diabetes.toString())

                    withContext(Dispatchers.Main) {
                        progressBar.visibility = View.GONE
                        previsao.text = "Tem Diabetes: ${data?.diabetes.toString()}"
                        previsao.visibility = View.VISIBLE
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(applicationContext, "Serviço Fora do Ar...", Toast.LENGTH_LONG)
                        .show()
                }
            }
        }
    }

}