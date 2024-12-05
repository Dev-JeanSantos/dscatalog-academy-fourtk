package com.fourtk.academy.ds_catalog.services.impl

import com.fourtk.academy.ds_catalog.domain.Category
import com.fourtk.academy.ds_catalog.dtos.responses.CategoryGetResponseDTO
import com.fourtk.academy.ds_catalog.dtos.responses.CategoryResponseDTO
import com.fourtk.academy.ds_catalog.exceptions.NotFoundException
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
        logger.info { "[CREATE-CATEGORY]-[Service] Starting create category " +
                "Category Name:[${request.name}]"}
        categoryRepository.save(request)
        return CategoryResponseDTO(message = "Category created successfully")
    }

    override fun getAll(name: String?, pagination: Pageable): Page<CategoryGetResponseDTO> {
        logger.info { "[GET - ALL CATEGORY]-[Service] Starting the search for category in the database"}
        val categories = name?.let {
            categoryRepository.findByName(name, pagination)
        } ?: categoryRepository.findAll(pagination)

        return categories.map { t -> categoryMapper.toEntityDTOListResponse(t) }
    }

    override fun findByName(name: String): CategoryGetResponseDTO {
        logger.info { "[GET - CATEGORY BY NAME]-[Service] Starting the search for category in the database"}
        val category: Category = categoryRepository.findByNameCategory(name)
            .orElseThrow { NotFoundException("Category with name $name not found") }
        return categoryMapper.toEntityDTOListResponse(category)
    }

    override fun update(id: Long, name: String): CategoryResponseDTO {
        logger.info { "[GET - UPDATE]-[Service] Starting update in category name"}
        logger.info { "[GET - UPDATE]-[Service] Starting the search for category in the database"}
        val category: Category = categoryRepository.findById(id)
            .orElseThrow { NotFoundException("Category with id $id not found") }
        return if (category.name != name){
            val categorySaved = category.copy(name=name)
            categoryRepository.save(categorySaved)
            logger.info { "[GET - UPDATE]-[Service] Update  new CategoryName: {$name} by categoryId: {$id} in the database"}
            CategoryResponseDTO(message = "Category update successfully")
        }else{
            logger.info { "[GET - UPDATE]-[Service] Not update new CategoryName: {$name} by categoryId: {$id} in the database"}
            CategoryResponseDTO(message = "Category not update successfully")
        }
    }

    override fun delete(id: Long): String {
        logger.info { "[GET - DELETE]-[Service] Starting delete category by categoryId: {$id} in the database"}
        val category: Category = categoryRepository.findById(id).orElse(null)
            ?: throw NotFoundException("Category with id $id not found")

        categoryRepository.delete(category)
        logger.info { "[GET - DELETE]-[Service] CategoryId:{$id} deleted from database successfully"}

        return "Category with CategoryId:$id deleted successfully"
    }
}