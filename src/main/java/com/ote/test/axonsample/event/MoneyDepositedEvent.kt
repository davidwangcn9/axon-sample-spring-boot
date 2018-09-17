package com.ote.test.axonsample.event

import java.util.*

data class MoneyDepositedEvent(
        val accountId: UUID,
        val amount: Double
)