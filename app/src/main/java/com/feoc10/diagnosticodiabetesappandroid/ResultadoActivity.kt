package com.feoc10.diagnosticodiabetesappandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.feoc10.diagnosticodiabetesappandroid.api.PrevisaoJson
import com.feoc10.diagnosticodiabetesappandroid.viewModels.PredictionViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ResultadoActivity : AppCompatActivity() {

    val predictorsViewModel: PredictionViewModel by viewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultado)
        val modeloSelecionado = intent.getStringExtra(EXTRA_MODELO).toString()
        val previsao_data = intent.getSerializableExtra(EXTRA_PREVISAO) as PrevisaoJson
        predictorsViewModel.prediction(this, modeloSelecionado, previsao_data)
    }

    override fun onResume() {
        super.onResume()
        val modeloSelecionado = intent.getStringExtra(EXTRA_MODELO).toString()
        val previsao_data = intent.getSerializableExtra(EXTRA_PREVISAO) as PrevisaoJson
        predictorsViewModel.prediction(this, modeloSelecionado, previsao_data)
    }

}