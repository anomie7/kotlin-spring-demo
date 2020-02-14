package com.example.demo

import com.example.demo.entity.Account
import com.example.demo.entity.AccountRole
import com.example.demo.repository.AccountRepository
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager

@DataJpaTest
class RepositoriesTests @Autowired constructor(
        val entityManager: TestEntityManager,
        val accountRepository: AccountRepository) {

    @Test
    fun `When findByLogin then return User`() {
        val account = Account(0, "adsfasdf", "asfd", mutableSetOf(AccountRole.USER))
        accountRepository.save(account)
    }
}