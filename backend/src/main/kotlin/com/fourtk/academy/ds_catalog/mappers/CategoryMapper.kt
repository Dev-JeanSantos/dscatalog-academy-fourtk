package com.fourtk.academy.ds_catalog.mappers

import com.fourtk.academy.ds_catalog.domain.Category
import com.fourtk.academy.ds_catalog.dtos.requests.CategoryRequestDTO
import com.fourtk.academy.ds_catalog.dtos.responses.CategoryGetResponseDTO
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper(componentModel = "spring")
interface CategoryMapper {
    @Mapping(target = "id", ignore = true)
    fun toRequestDTOEntity(request: CategoryRequestDTO): Category
    fun toEntityDTOListResponse(request: Category): CategoryGetResponseDTO

}
