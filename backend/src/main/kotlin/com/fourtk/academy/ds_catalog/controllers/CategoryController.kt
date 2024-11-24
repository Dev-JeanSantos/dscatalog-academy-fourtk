package com.fourtk.academy.ds_catalog.controllers

import com.fourtk.academy.ds_catalog.dtos.requests.CategoryRequestDTO
import com.fourtk.academy.ds_catalog.dtos.responses.CategoryGetResponseDTO
import com.fourtk.academy.ds_catalog.dtos.responses.CategoryResponseDTO
import com.fourtk.academy.ds_catalog.mappers.CategoryMapper
import com.fourtk.academy.ds_catalog.services.ICategoryService
import jakarta.validation.Valid
import mu.KotlinLogging
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/categories")
class CategoryController(
    val categoryService: ICategoryService,
    val categoryMapper: CategoryMapper
) {
    private val logger = KotlinLogging.logger {}
    @PostMapping
    fun saved(
        @Valid @RequestBody request: CategoryRequestDTO
    ): ResponseEntity<CategoryResponseDTO>{
        return runCatching {
            logger.info {
                "[CREATE-PRODUCT]-[Controller] Starting the saved Category name:[${request.name}]"
            }
            ResponseEntity(
                categoryService.saved(categoryMapper.toRequestDTOEntity(request)),
                HttpStatus.CREATED
            )
        }.onFailure {
            logger.error {
                "[CREATE-PRODUCT]-[Controller] failed"
            }
        }.onSuccess {
            logger.info {
                "[CREATE-PRODUCT]-[Controller] The hiring process was successfully completed"
            }
        }.getOrThrow()
    }

    @GetMapping
    fun getAll(
        @RequestParam(required = false) name: String?,
        @PageableDefault(size = 6, sort = ["id"], direction = Sort.Direction.DESC) pagination: Pageable
    ): ResponseEntity<Page<CategoryGetResponseDTO>> {
        return runCatching {
            logger.info { "[GET-CATEGORIES]-[Controller] Fetching all categories" }
            ResponseEntity(
                 categoryService.getAll(name, pagination),
                HttpStatus.OK
            )
        }.onFailure {
            logger.error { "[GET-CATEGORIES]-[Controller] Failed to fetch categories" }
        }.onSuccess {
            logger.info { "[GET-CATEGORIES]-[Controller] Successfully fetched categories" }
        }.getOrThrow()
    }
    @GetMapping("/{name}")
    fun update(@PathVariable name: String) : ResponseEntity<CategoryGetResponseDTO> {
        return runCatching {
            logger.info { "[GET-CATEGORY BY NAME]-[Controller] Fetching category" }
            ResponseEntity(
                categoryService.findByName(name), HttpStatus.OK
            )
        }.onFailure {
            logger.error { "Category not found" }
        }.onSuccess {
            logger.info { "[GET-CATEGORY BY NAME]-[Controller] Successfully category" }
        }.getOrThrow()
    }
}