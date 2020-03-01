package com.example.demo.config

import com.example.demo.entity.Account
import com.example.demo.entity.AccountRole
import com.example.demo.service.AccountService
import com.example.demo.service.CustomUserDetailsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
@EnableWebSecurity
class SecurityConfig(private val customUserDetailsService: CustomUserDetailsService,
                     private val passwordEncoder: PasswordEncoder) : WebSecurityConfigurerAdapter() {

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(customUserDetailsService)
                .passwordEncoder(passwordEncoder)
    }

    override fun configure(http: HttpSecurity) {
        http.authorizeRequests()
                .antMatchers("/resources/**").permitAll()
                .antMatchers("/test").hasRole("ADMIN")
                .antMatchers("/").permitAll()
                .and().formLogin().and().httpBasic().and().logout().and().csrf().disable()
    }

}

@Configuration
class BeanConfig {
    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder()
    }

    @Bean
    fun applicationRunner(): ApplicationRunner {
        return object : ApplicationRunner {

            @Autowired
            lateinit var accountService: AccountService

            @Throws(Exception::class)
            override fun run(args: ApplicationArguments) {
                val admin = Account(null,
                        "admin@test.com",
                        "password",
                        mutableSetOf(AccountRole.ADMIN))
                val user = Account(null, "user@test.com", "password", mutableSetOf(AccountRole.USER))
                accountService.saveAccountAll(listOf(admin, user))

            }
        }
    }
}
