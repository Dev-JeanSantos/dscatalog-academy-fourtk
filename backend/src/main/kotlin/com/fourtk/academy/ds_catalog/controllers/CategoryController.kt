package com.fourtk.academy.ds_catalog.controllers

import com.fourtk.academy.ds_catalog.dtos.requests.CategoryRequestDTO
import com.fourtk.academy.ds_catalog.dtos.responses.CategoryResponseDTO
import com.fourtk.academy.ds_catalog.mappers.CategoryMapper
import com.fourtk.academy.ds_catalog.services.ICategoryService
import jakarta.validation.Valid
import mu.KotlinLogging
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

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


}