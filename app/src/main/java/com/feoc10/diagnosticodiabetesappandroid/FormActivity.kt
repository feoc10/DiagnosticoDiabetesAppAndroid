package com.feoc10.diagnosticodiabetesappandroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.feoc10.diagnosticodiabetesappandroid.api.PrevisaoJson
import com.feoc10.diagnosticodiabetesappandroid.viewModels.PredictionViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

const val EXTRA_PREVISAO = "previsao"

class FormActivity : AppCompatActivity() {


    lateinit var modeloSelecionado: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form)
        modeloSelecionado = intent.getStringExtra(EXTRA_MODELO).toString()
        val text: String =
            java.lang.String.format(
                resources.getString(R.string.modelo_selecionado_phrase),
                modeloSelecionado.toString()
            )
        findViewById<TextView>(R.id.modeloSelecionado).text = text
    }

    override fun onResume() {
        super.onResume()
        modeloSelecionado = intent.getStringExtra(EXTRA_MODELO).toString()
        val text: String =
            java.lang.String.format(
                resources.getString(R.string.modelo_selecionado_phrase),
                modeloSelecionado.toString()
            )
        findViewById<TextView>(R.id.modeloSelecionado).text = text

    }

    fun getPrediction(view: View) {
        val previsao_data = PrevisaoJson(
            colesterol_ruim = findViewById<EditText>(R.id.colesterol_ruim_editTextText).text.toString()
                .toInt(),
            glucose = findViewById<EditText>(R.id.glucose_editTextText).text.toString().toInt(),
            colesterol_bom = findViewById<EditText>(R.id.colesterol_bom_editTextText).text.toString()
                .toInt(),
            idade = findViewById<EditText>(R.id.idade_editTextText).text.toString().toInt(),
            peso = findViewById<EditText>(R.id.peso_editTextText).text.toString().toInt(),
            altura = findViewById<EditText>(R.id.altura_editTextText).text.toString().toDouble(),
            pressao_arterial = findViewById<EditText>(R.id.pressao_editTextText).text.toString(),
            cintura = findViewById<EditText>(R.id.cintura_editTextText).text.toString().toDouble()
        )
        val intent = Intent(this, ResultadoActivity::class.java).apply{
            putExtra(EXTRA_MODELO, modeloSelecionado)
            putExtra(EXTRA_PREVISAO, previsao_data)
        }
        startActivity(intent)
    }

}