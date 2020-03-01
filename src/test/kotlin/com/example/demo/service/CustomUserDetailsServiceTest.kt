package com.example.demo.service

import com.example.demo.utill.getCustomUserDetails
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.test.context.support.WithAnonymousUser
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.security.test.context.support.WithUserDetails
import org.springframework.test.context.TestConstructor

@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@SpringBootTest
internal class CustomUserDetailsServiceTest(val customUserDetailsService: CustomUserDetailsService) {

    @Test
    @WithMockUser(username = "user_test", password = "1234", roles = ["USER"])
    fun loadUserByUsername() {
        val principal = SecurityContextHolder.getContext().authentication?.principal
        if (principal is UserDetails) {
            val username = principal.username
            val authorities = principal.authorities
        }
    }

    @Test
    @WithAnonymousUser
    fun loginanonymousUser() {
        val customUserDetails = getCustomUserDetails()
        val userName = customUserDetails?.username ?: "anonymous"
        Assertions.assertEquals("anonymous", userName)
    }

    @Test
    @WithUserDetails(value = "user@test.com", userDetailsServiceBeanName = "customUserDetailsService")
    fun name() {
        val customUserDetails = getCustomUserDetails()
        Assertions.assertTrue(customUserDetails?.username == "user@test.com")
    }
}