package com.creditas.loan

import com.creditas.loan.dto.LoanRequest
import com.creditas.loan.dto.LoanResponse
import com.creditas.loan.service.ConsignedLoanValidationHandler
import com.creditas.loan.service.LoanValidationHandler
import com.creditas.loan.service.PersonalLoanValidationHandler
import com.creditas.loan.service.SecuredLoanValidationHandler
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.dsl.bind
import org.koin.dsl.module
import org.koin.java.KoinJavaComponent.getKoin
import org.koin.ktor.plugin.Koin

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::myApplicationModule).start(wait = true)
}

val loanValidationModule = module {
    single { PersonalLoanValidationHandler() } bind LoanValidationHandler::class
    single { SecuredLoanValidationHandler() } bind LoanValidationHandler::class
    single { ConsignedLoanValidationHandler() } bind LoanValidationHandler::class
}

fun Application.myApplicationModule() {
    install(Koin) {
        modules(loanValidationModule)
    }

    install(ContentNegotiation) {
        json()
    }

    routing {
        post("/loan") {
            val request = call.receive<LoanRequest>()
            val loanValidationHandlers: List<LoanValidationHandler> by lazy { getKoin().getAll() }

            call.respond(
                status = HttpStatusCode.OK,
                message = LoanResponse(
                    customer = request.customer.name,
                    loans = loanValidationHandlers.mapNotNull { it.validate(request) }
                )
            )
        }
    }
}
