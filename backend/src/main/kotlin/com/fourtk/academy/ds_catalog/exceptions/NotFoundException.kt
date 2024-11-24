package com.fourtk.academy.ds_catalog.exceptions

data class NotFoundException(override val message: String) : RuntimeException(message)
