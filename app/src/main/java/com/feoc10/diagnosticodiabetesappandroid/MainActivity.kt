package com.feoc10.diagnosticodiabetesappandroid

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatActivity
import com.feoc10.diagnosticodiabetesappandroid.viewModels.PredictorsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

const val EXTRA_MODELO = "modelo"


class MainActivity : AppCompatActivity() {

    val predictorsViewModel: PredictorsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        predictorsViewModel.predictors(this)
    }

    fun getDataActivity(view: View) {
        val radiobutton1 = findViewById<RadioButton>(R.id.radioButton1)
        val radiobutton2 = findViewById<RadioButton>(R.id.radioButton2)

        val modeloSelecionado =
            if (radiobutton1.isChecked) radiobutton1.text.toString() else radiobutton2.text.toString()
        val intent = Intent(this, FormActivity::class.java).apply{
            putExtra(EXTRA_MODELO, modeloSelecionado)
        }
        startActivity(intent)
    }

}