package com.example.factorialcodechallenge.datatransferobject

data class ContactDto(
    val id: Long,
    val firstName: String,
    val lastName: String,
    val email: String,
    val phoneNumber: String,
    val editHistory: List<ContactHistoryDto>
)


