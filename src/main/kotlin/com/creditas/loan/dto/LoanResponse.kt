package com.creditas.loan.dto

import kotlinx.serialization.Serializable

@Serializable
data class LoanResponse(
    val customer: String,
    val loans: List<LoansData>
)
