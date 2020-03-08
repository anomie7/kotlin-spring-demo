package com.example.demo.util

import com.example.demo.dto.RegistrableAccountDto
import com.example.demo.entity.Account
import com.example.demo.entity.AccountRole
import com.example.demo.utill.AccountConverter
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mapstruct.factory.Mappers

//mapStruct 테스트
class MapperTest {

    @Test
    internal fun testConvertToDto() {
        val converter = Mappers.getMapper(AccountConverter::class.java)
        val account1 = Account(0, "asb@gmail.com", "asfd", mutableSetOf(AccountRole.USER))
        val accountDto = converter.convertToDto(account1)

        Assertions.assertEquals(account1.email, accountDto.email)
        Assertions.assertEquals(account1.password, accountDto.password)
    }

    @Test
    internal fun testConvertEntity() {
        val converter = Mappers.getMapper(AccountConverter::class.java)

        val accountDto = RegistrableAccountDto(email = "asb@gmail.com", password = "asdf")
        val account = converter.convertToEntity(accountDto)

        Assertions.assertEquals(account.email, accountDto.email)
        Assertions.assertEquals(account.password, accountDto.password)
    }
}