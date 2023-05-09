package com.creditas.loan.service

import com.creditas.loan.dto.LoanRequest
import com.creditas.loan.dto.LoanResponse
import com.creditas.loan.dto.LoansData

class PersonalLoanValidationHandler : LoanValidationHandler {

    override fun validate(request: LoanRequest): LoansData {
        return LoansData(
            type = TYPE,
            taxes = TAXES
        )
    }

    companion object {
        private const val TYPE = "PERSONAL"
        private const val TAXES = 4
    }

}
