package com.ote.test.axonsample

import com.ote.test.axonsample.datamodel.AccountEntity
import com.ote.test.axonsample.dataservice.AccountDataService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/api/v1")
class AccountViewController(@Autowired val accountDataService: AccountDataService) {

    @GetMapping("/accounts/{accountId}")
    fun getAccountById(@PathVariable("accountId") accountId: UUID): AccountEntity? {
        return this.accountDataService.getAccountById(accountId);
    }

    @GetMapping("/accounts")
    fun getAllAccounts(): List<AccountEntity> {
        return this.accountDataService.getAllAccounts();
    }
}