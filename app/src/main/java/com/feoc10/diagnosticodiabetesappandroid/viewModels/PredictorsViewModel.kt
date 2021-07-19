package com.feoc10.diagnosticodiabetesappandroid.viewModels

import android.app.Activity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.feoc10.diagnosticodiabetesappandroid.model.Predictors
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PredictorsViewModel (private val predictors : Predictors): ViewModel() {

    fun predictors(activity: Activity){
        viewModelScope.launch(Dispatchers.IO){
            predictors.getPredictors(activity)
        }
    }

}