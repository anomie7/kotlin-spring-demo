package com.example.demo.entity

import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime
import javax.persistence.*

@Entity
data class Account(
        @Id @GeneratedValue
        @Column(updatable = false, insertable = false)
        var id: Long? = null,
        var email: String,
        var password: String,

        @Enumerated(EnumType.STRING)
        @ElementCollection(fetch = FetchType.EAGER)
        var roles: MutableSet<AccountRole>,

        @CreationTimestamp
        var createDt: LocalDateTime = LocalDateTime.now()
)

enum class AccountRole {
    ADMIN, USER
}