package com.example.demo.entity

import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime
import javax.persistence.*

@Entity
class Account(
        @Id @GeneratedValue
        @Column(updatable = false, insertable = false)
        val id: Long? = null,
        @Column(unique = true)
        var email: String,
        var password: String,

        @Enumerated(EnumType.STRING)
        @ElementCollection(fetch = FetchType.EAGER)
        var roles: MutableSet<AccountRole>,

        @CreationTimestamp
        var createDt: LocalDateTime = LocalDateTime.now()
) {
    constructor() :
            this(null, "", "", mutableSetOf(), LocalDateTime.now())


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Account

        if (email != other.email) return false

        return true
    }

    override fun hashCode(): Int {
        return email.hashCode()
    }
}


enum class AccountRole {
    ADMIN, USER
}