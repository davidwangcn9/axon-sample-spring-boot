package com.ote.test.axonsample.repository

import com.ote.test.axonsample.datamodel.AccountEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface AccountRepository : JpaRepository<AccountEntity, UUID>