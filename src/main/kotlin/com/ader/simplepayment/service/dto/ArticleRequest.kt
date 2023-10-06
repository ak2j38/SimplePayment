package com.ader.simplepayment.service.dto

data class ArticleCreateRequest(
    val title: String,
    val content: String,
    val authorId: Long
)

data class ArticleUpdateRequest(
    val title: String?,
    val content: String?,
    val authorId: Long?
)
