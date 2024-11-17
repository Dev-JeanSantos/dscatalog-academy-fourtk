package com.fourtk.academy.ds_catalog.services

import com.fourtk.academy.ds_catalog.domain.Category
import com.fourtk.academy.ds_catalog.dtos.responses.CategoryResponseDTO

interface ICategoryService {
    fun saved(request: Category): CategoryResponseDTO
}
