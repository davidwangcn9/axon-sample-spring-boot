package com.ote.test.axonsample.command

import org.axonframework.commandhandling.TargetAggregateIdentifier
import java.util.*

data class WithdrawMoneyCommand(
        @TargetAggregateIdentifier val accountId: UUID,
        val amount: Double
)