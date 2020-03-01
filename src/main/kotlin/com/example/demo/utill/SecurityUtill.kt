package com.example.demo.utill

import com.example.demo.entity.CustomUserDetails
import org.springframework.security.core.context.SecurityContextHolder

fun getCustomUserDetails(): CustomUserDetails? {
    val principal = SecurityContextHolder.getContext().authentication?.principal
//    return if (principal is CustomUserDetails) principal else null
    return principal as? CustomUserDetails
}
