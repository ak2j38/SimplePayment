package com.ader.simplepayment.service.dto

data class ArticleCreateRequest(
    val title: String,
    val content: String,
    val authorId: Long
)
