package com.example.demo.service

import com.example.demo.entity.Account
import com.example.demo.entity.AccountRole
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestConstructor

@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@SpringBootTest
internal class AccountServiceTest(val accountService: AccountService) {

    @Test
    fun loadUserByUsername() {
        val email = "adsfasdf"
        val account = Account(0, email, "asfd", mutableSetOf(AccountRole.USER))
        accountService.saveAccount(account)

        val userDetails = accountService?.loadUserByUsername(email)
        assertThat(userDetails.username).isEqualTo(email)
    }
}