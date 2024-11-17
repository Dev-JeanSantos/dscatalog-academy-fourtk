package com.fourtk.academy.ds_catalog.repositories

import com.fourtk.academy.ds_catalog.domain.Category
import org.springframework.data.jpa.repository.JpaRepository

interface CategoryRepository: JpaRepository<Category, Long> {

}
