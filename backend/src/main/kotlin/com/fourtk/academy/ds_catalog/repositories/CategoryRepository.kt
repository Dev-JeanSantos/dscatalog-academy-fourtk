package com.fourtk.academy.ds_catalog.repositories

import com.fourtk.academy.ds_catalog.domain.Category
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.util.*

interface CategoryRepository: JpaRepository<Category, Long> {
    fun findByName(name: String, pagination: Pageable): Page<Category>

    @Query("SELECT c FROM Category c WHERE c.name = :name")
    fun findByNameCategory(@Param("name") name: String): Optional<Category>
}
