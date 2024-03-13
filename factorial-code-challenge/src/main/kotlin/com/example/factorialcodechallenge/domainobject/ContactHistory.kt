package com.example.factorialcodechallenge.domainobject

import com.example.factorialcodechallenge.datatransferobject.ContactHistoryDto
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
data class ContactHistory(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val oldValue: String,
    val newValue: String,
    val editedAt: LocalDateTime = LocalDateTime.now(),
    @ManyToOne(fetch = FetchType.LAZY)
    val contact: Contact
)

fun List<ContactHistory>.toDto(): List<ContactHistoryDto> {
    return this.map {
        ContactHistoryDto(
            oldValue = it.oldValue,
            newValue = it.newValue,
            editedAt = it.editedAt
        )
    }
}

fun ContactHistory.toDto(): ContactHistoryDto {
    return ContactHistoryDto(
        oldValue = this.oldValue,
        newValue = this.newValue,
        editedAt = this.editedAt
    )
}