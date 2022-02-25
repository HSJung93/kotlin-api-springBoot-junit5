package tv.codealong.tutorials.springboot.thenewboston.controller

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestInstance.Lifecycle
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get

// instead of testing pure POJO by creating objects
// use spring boots testing capabilities ...
// SpringBootTest annotation is integration test: initializes the whole application context, not Mock
@AutoConfigureMockMvc
@SpringBootTest
internal class BankControllerTest{
    // not actual http request
    // lateinit : framework is responsible for injecting this object -> @Autowired
    @Autowired
    lateinit var mockMvc: MockMvc


    val baseUrl = "/api/banks"

    @Nested
    @DisplayName("getBanks()")
    @TestInstance(Lifecycle.PER_CLASS)
    inner class GetBanks {

        @Test
        fun `should return all banks`() {
            // when
            mockMvc.get(baseUrl)
                .andDo{print()}
                //then
                .andExpect {
                    status{ isOk() }
                    content { contentType(MediaType.APPLICATION_JSON) }
                    jsonPath("$[0].accountNumber"){ value("1234") }}

        }
    }

    @Nested
    @DisplayName("getBank()")
    @TestInstance(Lifecycle.PER_CLASS)
    inner class GetBank {

        @Test
        fun `should return the bank with the given account number`() {
            // given
            val accountNumber = 1234

            // when/then
            mockMvc.get("$baseUrl/$accountNumber")
                .andDo { print() }
                .andExpect {
                    status{isOk()}
                    content{ contentType(MediaType.APPLICATION_JSON)}
                    jsonPath("$.trust"){value("3.14")}
                    jsonPath("$.transactionFee"){value("17")}
                }
        }

        @Test
        fun `should return Not FOUND if the account number does not exist`() {
            // given
            val accountNumber =  "does_not_exist"

            // when/then
            mockMvc.get("$baseUrl/$accountNumber")
                .andDo { print() }
                .andExpect { status{isNotFound()} }
        }

    }

}