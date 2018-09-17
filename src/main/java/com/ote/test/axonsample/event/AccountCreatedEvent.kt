package com.ote.test.axonsample.event

import java.util.*

data class AccountCreatedEvent(
        val accountId: UUID,
        val name: String?
)