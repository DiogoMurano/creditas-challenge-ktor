package com.creditas.loan.dto

import kotlinx.serialization.Serializable
import java.math.BigDecimal

@Serializable
data class LoanRequest(
    val customer: CustomerData
) {
    @Serializable
    data class CustomerData(
        val name: String,
        val cpf: String,
        val age: Int,
        val location: String,
        val income: String,
    ) {
        val incomeValue: BigDecimal
            get() = income.toBigDecimal()
    }
}
