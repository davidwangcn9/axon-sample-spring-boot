package com.ote.test.axonsample.dataservice

import com.ote.test.axonsample.datamodel.AccountEntity
import com.ote.test.axonsample.repository.AccountRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class AccountDataService(@Autowired val accountRepository: AccountRepository) {

    fun getAccountById(accountId: UUID): AccountEntity {
        return this.accountRepository.getOne(accountId)
    }

    fun getAllAccounts(): List<AccountEntity> {
        return this.accountRepository.findAll()
    }
}