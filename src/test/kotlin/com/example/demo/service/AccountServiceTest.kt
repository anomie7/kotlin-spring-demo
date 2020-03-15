package com.example.demo.service

import com.example.demo.dto.RegistrableAccountDto
import com.example.demo.entity.Account
import com.example.demo.entity.AccountRole
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatIllegalStateException
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestConstructor

@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@SpringBootTest
internal class AccountServiceTest(val accountService: AccountService) {

    @Test
    fun saveAccount() {
        val accountDto = RegistrableAccountDto(email = "asb@gmail.com", password = "asdf")

        val saveAccount = accountService.saveAccount(accountDto)
    }
}