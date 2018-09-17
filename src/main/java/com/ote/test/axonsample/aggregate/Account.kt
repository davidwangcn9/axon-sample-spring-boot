package com.ote.test.axonsample.aggregate

import com.ote.test.axonsample.command.CreateAccountCommand
import com.ote.test.axonsample.command.DepositMoneyCommand
import com.ote.test.axonsample.command.WithdrawMoneyCommand
import com.ote.test.axonsample.event.AccountCreatedEvent
import com.ote.test.axonsample.event.MoneyDepositedEvent
import com.ote.test.axonsample.event.MoneyWithdrawnEvent
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.commandhandling.model.AggregateIdentifier
import org.axonframework.commandhandling.model.AggregateLifecycle
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.spring.stereotype.Aggregate
import java.util.*


@Aggregate
class Account() {

    @AggregateIdentifier
    lateinit var accountId: UUID

    var balance: Double = 0.0

    @CommandHandler
    constructor (command: CreateAccountCommand) : this() {
        AggregateLifecycle.apply(AccountCreatedEvent(command.accountId, command.name))
    }

    @EventSourcingHandler
    protected fun on(event: AccountCreatedEvent) {
        this.accountId = event.accountId;
        this.balance = 0.0
    }

    @CommandHandler
    fun handle(command: DepositMoneyCommand) {
        AggregateLifecycle.apply(MoneyDepositedEvent(command.accountId, command.amount))
    }

    @EventSourcingHandler
    protected fun on(event: MoneyDepositedEvent) {
        this.accountId = event.accountId;
        this.balance += event.amount
    }

    @CommandHandler
    fun handle(command: WithdrawMoneyCommand) {
        if (this.balance - command.amount >= 0) {
            AggregateLifecycle.apply(MoneyWithdrawnEvent(command.accountId, command.amount))
        } else {
            throw IllegalArgumentException("Not enough money on account")
        }
    }

    @EventSourcingHandler
    protected fun on(event: MoneyWithdrawnEvent) {
        this.accountId = event.accountId;
        this.balance -= event.amount
    }
}