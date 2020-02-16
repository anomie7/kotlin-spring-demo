package com.example.demo.controller

import com.example.demo.entity.CustomUserDetails
import com.example.demo.repository.AccountRepository
import com.example.demo.service.CustomUserDetailsService
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import java.time.LocalDateTime

@Controller
class TestController(val accountRepository: AccountRepository,
                     val customUserDetailsService: CustomUserDetailsService) {

    @GetMapping("/test")
    fun getH2(model: Model): String {
        val principal: CustomUserDetails = SecurityContextHolder.getContext()?.authentication?.principal as CustomUserDetails
        val visitCount = principal.visitCount
        val username = principal.username
        val createDt = principal.createDt
        return "test"
    }

    @GetMapping("/change")
    fun changeAccountInfo(model: Model): String {
        val principal: Any? = SecurityContextHolder.getContext()?.authentication?.principal
        if (principal is UserDetails) {
            val username = principal.username
            accountRepository.findByEmail(username)
                    ?.apply { createDt = LocalDateTime.now().minusYears(5) }
                    ?.also { accountRepository.save(it) }
            val context = SecurityContextHolder.getContext()
            val userDetails = customUserDetailsService.loadUserByUsername(username)
            context.authentication = UsernamePasswordAuthenticationToken(userDetails, userDetails.password, userDetails.authorities)
        }
        return "test"
    }
}
