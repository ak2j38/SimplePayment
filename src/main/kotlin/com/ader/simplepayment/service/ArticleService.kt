package com.ader.simplepayment.service

import com.ader.simplepayment.model.Article
import com.ader.simplepayment.repository.ArticleRepository
import com.ader.simplepayment.service.dto.ArticleCreateRequest
import com.ader.simplepayment.service.dto.ArticleUpdateRequest
import com.ader.simplepayment.service.exception.NotFoundArticleException
import org.springframework.stereotype.Service

@Service
class ArticleService(
    private val articleRepository: ArticleRepository
) {
    suspend fun create(request: ArticleCreateRequest): Article {
        return articleRepository.save(
            Article(
                title = request.title,
                content = request.content,
                authorId = request.authorId,
            )
        )
    }

    suspend fun get(id: Long): Article  =
        articleRepository.findById(id) ?: throw NotFoundArticleException("Article not found")

    suspend fun getAll(title: String?) =
        if (title.isNullOrBlank()) {
            articleRepository.findAll()
        } else {
            articleRepository.findAllByTitleContains(title)
        }

    suspend fun update(id: Long, request: ArticleUpdateRequest): Article {
        val article = articleRepository.findById(id) ?: throw NotFoundArticleException("Article not found")
        request.title?.let { article.title = it }
        request.content?.let { article.content = it }
        request.authorId?.let { article.authorId = it }
    }

}
