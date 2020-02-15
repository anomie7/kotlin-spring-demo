package com.example.demo.controller

import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping

@Controller
class HtmlController{

    @GetMapping("/")
    fun greet(model: Model): String {
        val principal : Any? = SecurityContextHolder.getContext()?.authentication?.principal
        if (principal is UserDetails) {
            val username = principal.username
        }
        model["title"] = "Blog"
        return "blog"
    }
}