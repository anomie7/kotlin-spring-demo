package com.example.demo.service

import com.example.demo.entity.Account
import com.example.demo.entity.AccountRole
import org.junit.jupiter.api.Test


class test2 {
    @Test
    internal fun name() {
        val account1 = Account(0, "asb@gmail.com", "asfd", mutableSetOf(AccountRole.USER))
        val account2 = Account(1, "asb2@gmail.com", "asfd", mutableSetOf(AccountRole.USER))
        val account3 = Account(2, "asb3@gmail.com", "asfd", mutableSetOf(AccountRole.ADMIN))


        val mutableList = mutableListOf<Account>(account1, account2, account3)
        val first = mutableList.asSequence().map(Account::email).toList()
        val s = mutableList.filter { it.email == "asb@gmail.com" }.map { it.password }
    }
}