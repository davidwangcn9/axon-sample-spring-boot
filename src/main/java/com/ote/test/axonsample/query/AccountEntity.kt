package com.ote.test.axonsample.query;

import java.util.*
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "T_ACCOUNT")
data class AccountEntity(@Id val accountId: UUID, val name: String?, val balance: Double) {

    constructor() : this(UUID.randomUUID(), null, 0.0) {}

    class Builder {
        private lateinit var accountId: UUID
        private var name: String? = null
        private var balance: Double = 0.0

        fun accountId(v: UUID) = apply { this.accountId = v }
        fun name(v: String?) = apply { this.name = v }
        fun balance(v: Double) = apply { this.balance = v }

        fun copyOf(v: AccountEntity) = apply {
            accountId = v.accountId
            name = v.name
            balance = v.balance
        }

        fun build() = AccountEntity(accountId, name, balance)
    }

    companion object {
        fun builder() = Builder()
    }
}
