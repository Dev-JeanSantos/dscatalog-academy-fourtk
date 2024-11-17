package com.fourtk.academy.ds_catalog.services.impl

import com.fourtk.academy.ds_catalog.domain.Category
import com.fourtk.academy.ds_catalog.dtos.responses.CategoryResponseDTO
import com.fourtk.academy.ds_catalog.repositories.CategoryRepository
import com.fourtk.academy.ds_catalog.services.ICategoryService
import mu.KotlinLogging
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class CategoryService(
    val categoryRepository: CategoryRepository
) : ICategoryService {

    private val logger = KotlinLogging.logger {}
    override fun saved(request: Category): CategoryResponseDTO {
        "[CREATE-PRODUCT]-[Service] Starting create product " +
                "Category Name:[${request.name}]"
        categoryRepository.save(request)
        println(request)
        return CategoryResponseDTO(message = "categoria criada com Sucesso")
    }
}