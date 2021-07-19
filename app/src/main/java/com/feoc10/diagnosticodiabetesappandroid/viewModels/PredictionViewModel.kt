package com.feoc10.diagnosticodiabetesappandroid.viewModels

import android.app.Activity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.feoc10.diagnosticodiabetesappandroid.api.PrevisaoJson
import com.feoc10.diagnosticodiabetesappandroid.model.Predictions
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PredictionViewModel(private val prediction: Predictions) : ViewModel() {

    fun prediction(activity: Activity, modelo: String, json: PrevisaoJson){
        viewModelScope.launch(Dispatchers.IO) {
            prediction.predictions(activity, modelo, json)

        }
    }
}