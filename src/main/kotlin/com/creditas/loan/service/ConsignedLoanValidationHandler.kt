package com.creditas.loan.service

import com.creditas.loan.dto.LoanRequest
import com.creditas.loan.dto.LoanResponse
import com.creditas.loan.dto.LoansData
import com.creditas.loan.utils.bd

class ConsignedLoanValidationHandler : LoanValidationHandler {

    override fun validate(request: LoanRequest): LoansData? {
        return if (request.customer.incomeValue >= 5000.bd) {
            LoansData(
                type = TYPE,
                taxes = TAXES
            )
        } else null
    }

    companion object {
        private const val TYPE = "CONSIGNED"
        private const val TAXES = 2
    }

}
