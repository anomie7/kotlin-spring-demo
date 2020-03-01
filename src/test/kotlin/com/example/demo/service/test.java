package com.example.demo.service;


import com.example.demo.entity.CustomUserDetails;
import com.example.demo.utill.SecurityUtillKt;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithAnonymousUser;

import java.security.Security;

@SpringBootTest
public class test {
    @Test
    @WithAnonymousUser
    void name() {
        CustomUserDetails customUserDetails = SecurityUtillKt.getCustomUserDetails(new SecurityContextHolder());
        Assertions.assertNull(customUserDetails);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    }
}
