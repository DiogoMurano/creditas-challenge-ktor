package com.creditas.loan.dto

import kotlinx.serialization.Serializable

@Serializable
data class LoansData(
    val type: String,
    val taxes: Int
)
