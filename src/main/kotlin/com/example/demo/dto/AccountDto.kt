package com.example.demo.dto

import com.example.demo.entity.AccountRole
import java.time.LocalDateTime

data class RegistrableAccountDto(var email: String = "", var password: String = ""){
    constructor() : this("", "")
}

data class ReadableAccountDto(var id : Long?, var email: String, var roles: MutableSet<AccountRole>,var createDt: LocalDateTime)

data class UpdatableAccountDto(var id : Long?, var email: String, var password: String, var roles: MutableSet<AccountRole>,var createDt: LocalDateTime)