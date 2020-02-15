package com.example.demo.controller

import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class TestController {

    @GetMapping("/test")
    fun getH2(model: Model): String {
        val principal : UserDetails = SecurityContextHolder.getContext()?.authentication?.principal as UserDetails
        val username = principal.username
        return "test"
    }
}
