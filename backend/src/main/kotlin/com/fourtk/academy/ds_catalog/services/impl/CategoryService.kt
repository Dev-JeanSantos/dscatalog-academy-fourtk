package com.fourtk.academy.ds_catalog.services.impl

import com.fourtk.academy.ds_catalog.domain.Category
import com.fourtk.academy.ds_catalog.dtos.responses.CategoryGetResponseDTO
import com.fourtk.academy.ds_catalog.dtos.responses.CategoryResponseDTO
import com.fourtk.academy.ds_catalog.mappers.CategoryMapper
import com.fourtk.academy.ds_catalog.repositories.CategoryRepository
import com.fourtk.academy.ds_catalog.services.ICategoryService
import mu.KotlinLogging
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class CategoryService(
    val categoryRepository: CategoryRepository,
    val categoryMapper: CategoryMapper
) : ICategoryService {

    private val logger = KotlinLogging.logger {}
    override fun saved(request: Category): CategoryResponseDTO {
        logger.info { "[CREATE-CATEGORIE]-[Service] Starting create category " +
                "Category Name:[${request.name}]"}
        categoryRepository.save(request)
        return CategoryResponseDTO(message = "Category created successfully")
    }

    override fun getAll(name: String?, pagination: Pageable): Page<CategoryGetResponseDTO> {
        logger.info { "[GET ALL - CATEGORIES]-[Service] Starting the search for categories in the database"}
        val categories = name?.let {
            categoryRepository.findByName(name, pagination)
        } ?: categoryRepository.findAll(pagination)

        return categories.map { t -> categoryMapper.toEntityDTOListResponse(t) }
    }
}