package com.example.demo

import com.example.demo.entity.Account
import com.example.demo.entity.AccountRole
import com.example.demo.repository.AccountRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.security.core.userdetails.User

@DataJpaTest
class RepositoriesTests @Autowired constructor(
        val entityManager: TestEntityManager,
        val accountRepository: AccountRepository) {

    val email = "user@test.com"

    @BeforeEach
    internal fun setUp() {
        val account = Account(0, email, "asfd", mutableSetOf(AccountRole.USER))
        accountRepository.save(account)
    }

    @Test
    fun `When findByLogin then return User`() {
        val account = Account(0, "adsfasdf", "asfd", mutableSetOf(AccountRole.USER))
        accountRepository.save(account)
    }

    @Test
    fun `When findByEmail apply password`() {
        val account = accountRepository.findByEmail(email)?.apply { password = "a123" }

        Assertions.assertEquals("a123", account?.password)
    }

    @Test
    fun `When findByEmail let`() {
        val let = accountRepository.findByEmail(email)?.let { it.getAuthorities() }
        val also = accountRepository.findByEmail(email)?.also { it.email = "22" }
        Assertions.assertTrue(let is User)
    }
}