package com.example.demo.utill

import com.example.demo.dto.RegistrableAccountDto
import com.example.demo.entity.Account
import org.mapstruct.InheritInverseConfiguration
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Mappings

@Mapper
interface AccountConverter {
    fun convertToDto(account: Account): RegistrableAccountDto
    fun convertToEntity(registrableAccountDto: RegistrableAccountDto): Account
}