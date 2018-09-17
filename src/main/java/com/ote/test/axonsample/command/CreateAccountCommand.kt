package com.ote.test.axonsample.command

import org.axonframework.commandhandling.TargetAggregateIdentifier
import java.util.*

data class CreateAccountCommand(
        @TargetAggregateIdentifier val accountId: UUID,
        val name: String?
)