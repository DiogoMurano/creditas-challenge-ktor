package com.creditas.loan.service

import com.creditas.loan.dto.LoanRequest
import com.creditas.loan.dto.LoansData

interface LoanValidationHandler {

    fun validate(request: LoanRequest): LoansData?

}
