package com.feoc10.diagnosticodiabetesappandroid.api

import java.io.Serializable

data class PrevisaoJson(
    val altura: Double,
    val cintura: Double,
    val colesterol_bom: Int,
    val colesterol_ruim: Int,
    val glucose: Int,
    val idade: Int,
    val peso: Int,
    val pressao_arterial: String
) : Serializable