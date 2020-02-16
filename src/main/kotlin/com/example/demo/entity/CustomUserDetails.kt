package com.example.demo.entity

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.time.LocalDateTime
import java.util.stream.Collectors

class CustomUserDetails(private val account: Account) : UserDetails {
    val visitCount: Int = 5
    val createDt: LocalDateTime = account.createDt

    override fun getUsername(): String {
        return account.email
    }

    override fun getPassword(): String {
        return account.password
    }

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return account.roles.stream().map { role -> SimpleGrantedAuthority("ROLE_$role") }.collect(Collectors.toSet())
    }

    override fun isEnabled(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }
}

