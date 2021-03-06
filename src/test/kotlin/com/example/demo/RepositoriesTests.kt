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
    fun `account를 hashSet에 넣고 영속화하면 hashSet에서 조회 불가능`() {
        val account = Account(email = "asdf@test.com",
                password = "asdf",
                roles = mutableSetOf(AccountRole.USER))
        accountRepository.save(account)
        val hashSet = hashSetOf(account)
        Assertions.assertTrue(hashSet.contains(account))
    }

}