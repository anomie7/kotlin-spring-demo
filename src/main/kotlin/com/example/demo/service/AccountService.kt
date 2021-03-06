package com.example.demo.service

import com.example.demo.dto.RegistrableAccountDto
import com.example.demo.entity.Account
import com.example.demo.entity.CustomUserDetails
import com.example.demo.repository.AccountRepository
import com.example.demo.utill.AccountConverter
import org.mapstruct.factory.Mappers
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AccountService(private val accountRepository: AccountRepository,
                     private val passwordEncoder: PasswordEncoder) {
    val converter = Mappers.getMapper(AccountConverter::class.java)

    fun saveAccount(account: Account): Account {
        account.password = this.passwordEncoder.encode(account.password)
        return accountRepository.save(account)
    }

    fun saveAccount(accountDto: RegistrableAccountDto): Long {
        val account = converter.convertToEntity(accountDto)
        return accountRepository.save(account).let { it.id!! }
    }

    fun saveAccountAll(accounts: List<Account>): MutableList<Account> {
        accounts.forEach { it.password = this.passwordEncoder.encode(it.password) }
        return accountRepository.saveAll(accounts)
    }

}

@Service
class CustomUserDetailsService(private val accountRepository: AccountRepository,
                               private val passwordEncoder: PasswordEncoder) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        return accountRepository.findByEmail(username)?.let { CustomUserDetails.from(it) }
                ?: throw UsernameNotFoundException("$username Can Not Found")
    }
}