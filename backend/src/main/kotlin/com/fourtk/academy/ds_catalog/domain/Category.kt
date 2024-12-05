package com.fourtk.academy.ds_catalog.domain

import jakarta.persistence.*

@Entity
@Table(name = "tb_category")
data class Category(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    var name: String
)
