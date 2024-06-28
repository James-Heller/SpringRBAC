package pers.jamestang.springrbac.system.util

data class Page<T>(
    val content: List<T>,
    val totalElements: Long,
    val totalPages: Int,
    val size: Int
)