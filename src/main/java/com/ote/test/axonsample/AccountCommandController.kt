package com.ote.test.axonsample

import com.ote.test.axonsample.command.CreateAccountCommand
import com.ote.test.axonsample.command.DepositMoneyCommand
import com.ote.test.axonsample.command.WithdrawMoneyCommand
import org.axonframework.commandhandling.gateway.CommandGateway
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.util.*
import java.util.concurrent.CompletableFuture

@RestController
@RequestMapping("/api/v1")
class AccountCommandController(@Autowired var commandGateway: CommandGateway) {

    @PostMapping("/accounts/{name}")
    fun createBankAccount(@PathVariable("name") name: String): CompletableFuture<Any> {
        return commandGateway.send(CreateAccountCommand(UUID.randomUUID(), name))
    }

    @PutMapping("/accounts/{accountId}")
    fun makeOperation(@PathVariable("accountId") accountId: UUID,
                      @RequestBody operation: OperationPayload): CompletableFuture<Any> {
        return commandGateway.send(
                when (operation.type) {
                    OperationType.DEPOSIT -> DepositMoneyCommand(accountId, operation.amount)
                    OperationType.WITHDRAW -> WithdrawMoneyCommand(accountId, operation.amount)
                })
    }


    data class OperationPayload(val type: OperationType, val amount: Double)

    enum class OperationType { DEPOSIT, WITHDRAW }
}




