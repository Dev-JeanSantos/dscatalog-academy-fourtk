package com.fourtk.academy.ds_catalog.services.impl

import com.fourtk.academy.ds_catalog.builds.BuildCategory
import com.fourtk.academy.ds_catalog.mappers.CategoryMapper
import com.fourtk.academy.ds_catalog.repositories.CategoryRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.util.*
import kotlin.test.assertEquals


@ExtendWith(SpringExtension::class)
@DisplayName("Tests of services")
class CategoryServiceTest {

    private val category = BuildCategory.buildCategory()
    private val categories = PageImpl(listOf(BuildCategory.buildCategory()))

    private val pageable: Pageable = mockk()
    private val categoryMapper: CategoryMapper = mockk()


    private val categoryRepository: CategoryRepository = mockk {
        every { save(any()) } returns category
        every { findByName(any(), any()) } returns categories
        every { findByNameCategory(any()) } returns Optional.of(category)
        every { findAll(pageable) } returns categories
        every { delete(any()) }
    }

    val service = CategoryService(
        categoryRepository,
        categoryMapper
    )

    @Test
    fun saved() {

        val categorySaved = service.saved(category)

        verify(exactly = 1) { categoryRepository.save(any()) }
        verify(exactly = 0) { categoryRepository.findByName(any(), any()) }
        assertEquals(categorySaved.message, "Category created successfully")
    }

    @Test
    fun getAll() {
    }

    @Test
    fun findByName() {
    }

    @Test
    fun update() {
    }

    @Test
    fun delete() {
    }

    @Test
    fun getCategoryRepository() {
    }

    @Test
    fun getCategoryMapper() {
    }
}