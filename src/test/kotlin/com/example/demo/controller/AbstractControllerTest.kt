package com.example.demo.controller

import org.hamcrest.core.StringContains.containsString
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.TestConstructor
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import java.time.LocalDate
import java.time.LocalDateTime

@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@SpringBootTest
@AutoConfigureMockMvc
class AbstractControllerTest {
    @Autowired
    protected lateinit var mockMvc: MockMvc
}

class sampleControllerTest : AbstractControllerTest(){
    @Test
    fun sampleNormal() {
        mockMvc?.perform(get("/"))
                ?.andDo { println(it) }
                ?.andExpect(MockMvcResultMatchers.status().isOk)
                ?.andReturn()
    }

    @Test
    fun samepleDsl() {
        mockMvc?.get("/") {
            contentType = MediaType.TEXT_HTML
            accept = MediaType.TEXT_HTML
        }?.andExpect {
            status { isOk }
            content { contentType("text/html;charset=UTF-8") }
            content { encoding("UTF-8")}
            content { string(containsString(LocalDate.now().toString())) }
        }?.andReturn()
    }
}