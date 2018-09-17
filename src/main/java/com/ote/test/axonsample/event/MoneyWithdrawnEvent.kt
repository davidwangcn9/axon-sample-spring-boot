package com.ote.test.axonsample.event

import java.util.*

data class MoneyWithdrawnEvent(
        val accountId: UUID,
        val amount: Double
)