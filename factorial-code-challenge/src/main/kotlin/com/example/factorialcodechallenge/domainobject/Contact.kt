package com.example.factorialcodechallenge.domainobject

import com.example.factorialcodechallenge.datatransferobject.ContactDto
import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import org.springframework.data.domain.Page
import java.time.LocalDateTime

@Entity
data class Contact(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    var firstName: String,
    var lastName: String,
    var email: String,
    var phoneNumber: String,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    @OneToMany(mappedBy = "contact", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    val editHistory: MutableList<ContactHistory> = mutableListOf()
)

fun Page<Contact>.toDto(): Page<ContactDto> {
    return this.map {
        ContactDto(
            id = it.id,
            email = it.email,
            lastName = it.lastName,
            firstName = it.firstName,
            phoneNumber = it.phoneNumber,
            editHistory = it.editHistory.toDto(),
        )
    }
}

fun Contact.toDto(): ContactDto {
    return ContactDto(
        id = this.id,
        email = this.email,
        lastName = this.lastName,
        firstName = this.firstName,
        phoneNumber = this.phoneNumber,
        editHistory = this.editHistory.toDto(),
    )
}