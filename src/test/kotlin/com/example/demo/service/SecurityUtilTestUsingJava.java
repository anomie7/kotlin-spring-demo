package com.example.demo.service;


import com.example.demo.entity.CustomUserDetails;
import com.example.demo.utill.SecurityUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithUserDetails;

import java.time.LocalDateTime;

@SpringBootTest
public class SecurityUtilTestUsingJava {
    @Test
    @WithAnonymousUser
    void 로그인하지_않은_사용자의_정보를_조회() {
        CustomUserDetails customUserDetails = SecurityUtil.getCustomUserDetails();

        Assertions.assertNull(customUserDetails);
    }

    @Test
    @WithUserDetails(value = "user@test.com", userDetailsServiceBeanName = "customUserDetailsService")
    void 로그인한_사용자의_정보를_조회() {
        CustomUserDetails customUserDetails = SecurityUtil.getCustomUserDetails();

        Assertions.assertEquals("user@test.com", customUserDetails.getUsername());
    }
}
