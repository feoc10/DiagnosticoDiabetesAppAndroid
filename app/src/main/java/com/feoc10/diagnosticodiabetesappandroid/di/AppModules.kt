package com.feoc10.diagnosticodiabetesappandroid.di

import com.feoc10.diagnosticodiabetesappandroid.model.Predictions
import com.feoc10.diagnosticodiabetesappandroid.model.Predictors
import com.feoc10.diagnosticodiabetesappandroid.viewModels.PredictionViewModel
import com.feoc10.diagnosticodiabetesappandroid.viewModels.PredictorsViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    factory { androidContext().resources }
    factory { Predictors(get()) }
    factory { Predictions(get()) }

    viewModel { PredictorsViewModel(get()) }
    viewModel { PredictionViewModel(get()) }

}