package com.example.factorialcodechallenge.controller

import com.example.factorialcodechallenge.datatransferobject.ContactDto
import com.example.factorialcodechallenge.domainobject.toDto
import com.example.factorialcodechallenge.service.ContactService
import jakarta.validation.Valid
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import org.springdoc.core.annotations.ParameterObject
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@RestController
@CrossOrigin(origins = ["http://localhost:3000"])
@RequestMapping("/contacts")
class ContactController(
    private val contactService: ContactService
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createContact(@Valid @RequestBody request: CreateContactRequest): ContactDto {
        val contact = contactService.createContact(request.firstName, request.lastName, request.email, request.phoneNumber)
        return contact.toDto()
    }

    @GetMapping("/{id}")
    fun getContact(@PathVariable id: Long): ResponseEntity<ContactDto> {
        val contact = contactService.getContact(id)
        return if (contact.isPresent) {
            ResponseEntity.ok(contact.get().toDto())
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @PutMapping("/{id}")
    fun updateContact(@PathVariable id: Long, @Valid @RequestBody request: UpdateContactRequest): ResponseEntity<ContactDto> {
        val contact = contactService.updateContact(id, request.firstName, request.lastName, request.email, request.phoneNumber)
        return ResponseEntity.ok(contact.toDto())
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteContact(@PathVariable id: Long) {
        contactService.deleteContact(id)
    }

    @GetMapping
    fun getAllContacts(@ParameterObject pageable: Pageable): Page<ContactDto> {
        return contactService.getAllContacts(pageable).toDto()
    }
}

data class CreateContactRequest(
    @NotBlank
    val firstName: String,
    @NotBlank
    val lastName: String,
    @Email
    val email: String,
    @NotBlank
    val phoneNumber: String
)

data class UpdateContactRequest(
    @NotBlank
    val firstName: String,
    @NotBlank
    val lastName: String,
    @Email
    val email: String,
    @NotBlank
    val phoneNumber: String
)