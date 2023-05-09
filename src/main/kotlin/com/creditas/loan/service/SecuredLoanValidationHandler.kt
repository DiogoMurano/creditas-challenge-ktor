package com.creditas.loan.service

import com.creditas.loan.dto.LoanRequest
import com.creditas.loan.dto.LoansData
import com.creditas.loan.utils.bd

class SecuredLoanValidationHandler : LoanValidationHandler {

    override fun validate(request: LoanRequest): LoansData? {
        return with(request.customer) {
            approveWhen { incomeValue >= 5000.bd && age < 30 }
                ?: approveWhen { incomeValue > 3000.bd && incomeValue < 5000.bd && location == LOCATION_REQUIREMENT }
                ?: approveWhen { incomeValue <= 3000.bd && age < 30 && location == LOCATION_REQUIREMENT }
        }
    }

    private fun LoanRequest.CustomerData.approveWhen(
        f: (data: LoanRequest.CustomerData) -> Boolean
    ): LoansData? {
        return if (f.invoke(this)) {
            LoansData(
                type = TYPE,
                taxes = TAXES
            )
        } else null
    }

    companion object {
        private const val LOCATION_REQUIREMENT = "SP"

        private const val TYPE = "SECURED"
        private const val TAXES = 3
    }

}
