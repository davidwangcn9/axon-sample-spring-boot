package com.ote.test.axonsample.command

import org.axonframework.commandhandling.TargetAggregateIdentifier
import java.util.*

data class DepositMoneyCommand(
        @TargetAggregateIdentifier val accountId: UUID,
        val amount: Double
)