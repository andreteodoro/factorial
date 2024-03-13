package com.example.factorialcodechallenge.dataaccessobject

import com.example.factorialcodechallenge.domainobject.Contact
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface ContactRepository : JpaRepository<Contact, Long> {
    fun findByEmail(email: String): Contact?

    override fun findAll(pageable: Pageable): Page<Contact>
}