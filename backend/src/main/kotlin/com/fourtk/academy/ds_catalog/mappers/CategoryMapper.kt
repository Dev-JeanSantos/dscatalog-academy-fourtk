package com.fourtk.academy.ds_catalog.mappers

import com.fourtk.academy.ds_catalog.domain.Category
import com.fourtk.academy.ds_catalog.dtos.requests.CategoryRequestDTO
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface CategoryMapper {
    fun toRequestDTOEntity(request: CategoryRequestDTO): Category

}
