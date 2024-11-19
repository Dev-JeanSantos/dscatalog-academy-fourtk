package com.fourtk.academy.ds_catalog.services

import com.fourtk.academy.ds_catalog.domain.Category
import com.fourtk.academy.ds_catalog.dtos.responses.CategoryGetResponseDTO
import com.fourtk.academy.ds_catalog.dtos.responses.CategoryResponseDTO
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface ICategoryService {
    fun saved(request: Category): CategoryResponseDTO
    fun getAll(name: String?, pagination: Pageable): Page<CategoryGetResponseDTO>
}
