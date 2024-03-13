package com.example.factorialcodechallenge.service

import com.example.factorialcodechallenge.dataaccessobject.ContactRepository
import com.example.factorialcodechallenge.domainobject.Contact
import com.example.factorialcodechallenge.domainobject.ContactHistory
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.Optional

@Service
@Transactional
class ContactService(
    private val contactRepository: ContactRepository
) {

    fun createContact(firstName: String, lastName: String, email: String, phoneNumber: String): Contact {
        require(contactRepository.findByEmail(email) == null) { "Contact with this email already exists" }
        val contact = Contact(
            firstName = firstName,
            lastName = lastName,
            email = email,
            phoneNumber = phoneNumber
        )

        return contactRepository.save(contact)
    }

    fun getContact(id: Long): Optional<Contact> {
        return contactRepository.findById(id)
    }

    fun updateContact(id: Long, newFirstName: String, newLastName: String, newEmail: String, newPhoneNumber: String): Contact {
        val contact = contactRepository.findById(id)
            .orElseThrow { IllegalArgumentException("Contact with id $id not found") }
        require(!(newEmail != contact.email && contactRepository.findByEmail(newEmail) != null)) { "Another contact with this email already exists" }
        val oldValues = mapOf(
            "firstName" to contact.firstName,
            "lastName" to contact.lastName,
            "email" to contact.email,
            "phoneNumber" to contact.phoneNumber
        )
        contact.firstName = newFirstName
        contact.lastName = newLastName
        contact.email = newEmail
        contact.phoneNumber = newPhoneNumber
        contact.editHistory.add(ContactHistory(
            oldValue = oldValues.entries.joinToString { "${it.key}: ${it.value}" },
            newValue = "firstName: $newFirstName, lastName: $newLastName, email: $newEmail, phoneNumber: $newPhoneNumber",
            contact = contact
        ))
        return contactRepository.save(contact)
    }

    fun deleteContact(id: Long) {
        contactRepository.deleteById(id)
    }

    fun getAllContacts(pageable: Pageable): Page<Contact> {
        return contactRepository.findAll(pageable)
    }
}