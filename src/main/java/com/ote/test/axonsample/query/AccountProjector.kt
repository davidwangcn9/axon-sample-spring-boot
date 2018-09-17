package com.ote.test.axonsample.query

import com.ote.test.axonsample.datamodel.AccountEntity
import com.ote.test.axonsample.event.AccountCreatedEvent
import com.ote.test.axonsample.event.MoneyDepositedEvent
import com.ote.test.axonsample.event.MoneyWithdrawnEvent
import com.ote.test.axonsample.repository.AccountRepository
import org.axonframework.eventhandling.EventHandler
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class AccountProjector(@Autowired val repository: AccountRepository) {

    @EventHandler
    fun on(event: AccountCreatedEvent) {
        val accountView = AccountEntity.builder().accountId(event.accountId).name(event.name).build()
        repository.save(accountView)
    }

    @EventHandler
    fun on(event: MoneyDepositedEvent) {
        val accountId = event.accountId
        val accountView = repository.getOne(accountId)
        val newBalance = accountView.balance + event.amount
        val updatedView = AccountEntity.builder().copyOf(accountView).balance(newBalance).build()
        repository.save(updatedView)
    }

    @EventHandler
    fun on(event: MoneyWithdrawnEvent) {
        val accountId = event.accountId
        val accountView = repository.getOne(accountId)
        val newBalance = accountView.balance - event.amount
        val updatedView = AccountEntity.builder().copyOf(accountView).balance(newBalance).build()
        repository.save(updatedView)
    }
}
