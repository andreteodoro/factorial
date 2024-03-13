package com.example.factorialcodechallenge.controller

import com.fasterxml.jackson.databind.ObjectMapper
import io.github.serpro69.kfaker.Faker
import jakarta.servlet.ServletException
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@ExtendWith(SpringExtension::class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class ContactControllerIntegrationTests {

    @Autowired
    private lateinit var mockMvc: MockMvc

    val faker = Faker()

    @Test
    fun `test create contact`() {
        val email = faker.internet.safeEmail()
        val requestJson = """
            {
                "firstName": "John",
                "lastName": "Doe",
                "email": "$email",
                "phoneNumber": "1234567890"
            }
        """.trimIndent()

        mockMvc.perform(
            post("/contacts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson)
        )
            .andExpect(status().isCreated)
            .andExpect(jsonPath("$.firstName").value("John"))
            .andExpect(jsonPath("$.email").value(email))
    }

    @Test
    fun `test create contact duplicated`() {
        val requestJson = """
            {
                "firstName": "John",
                "lastName": "Doe",
                "email": "john.doe@example.com",
                "phoneNumber": "1234567890"
            }
        """.trimIndent()

        assertThrows(
            ServletException::class.java,
            {
                mockMvc.perform(
                post("/contacts")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(requestJson)
                )
            },
            "duplicated email should throw an error"
        )
    }



    @Test
    fun `test get contact by id`() {
        mockMvc.perform(
            get("/contacts/{id}", 1)
        )
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.id").value(1))
    }

    @Test
    fun `test update contact`() {
        val requestJson = """
            {
                "firstName": "Jane",
                "lastName": "Doe",
                "email": "jane.doe99@example.com",
                "phoneNumber": "0987654321"
            }
        """.trimIndent()

        mockMvc.perform(
            put("/contacts/{id}", 2)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson)
        )
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.firstName").value("Jane"))
            .andExpect(jsonPath("$.email").value("jane.doe99@example.com"))
    }

    @Test
    fun `test delete contact`() {
        val email = faker.internet.safeEmail()
        val requestJson = """
            {
                "firstName": "John",
                "lastName": "Doe",
                "email": "$email",
                "phoneNumber": "1234567890"
            }
        """.trimIndent()

        val result = mockMvc.perform(
            post("/contacts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson)
        )
            .andExpect(jsonPath("$.id").exists())
            .andReturn()
            .response
            .contentAsString

        val contactId = ObjectMapper().readTree(result).get("id").asLong()

        mockMvc.perform(
            delete("/contacts/{id}", contactId)
        )
            .andExpect(status().isNoContent)
    }

    @Test
    fun `test get all contacts`() {
        mockMvc.perform(
            get("/contacts")
        )
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.content").isNotEmpty)
    }
}