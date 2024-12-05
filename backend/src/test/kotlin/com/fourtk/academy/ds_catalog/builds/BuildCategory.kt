package com.fourtk.academy.ds_catalog.builds

import com.fourtk.academy.ds_catalog.domain.Category

object BuildCategory {
    fun buildCategory(
        id: Long = 1L,
        name: String = "JAVA"
    ) = Category(
        id = id,
        name = name
    )
}