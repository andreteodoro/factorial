package com.example.factorialcodechallenge.datatransferobject

import java.time.LocalDateTime

data class ContactHistoryDto(
    val oldValue: String,
    val newValue: String,
    val editedAt: LocalDateTime
)