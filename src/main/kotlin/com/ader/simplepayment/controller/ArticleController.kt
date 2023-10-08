package com.ader.simplepayment.controller

import com.ader.simplepayment.service.ArticleService
import com.ader.simplepayment.service.dto.ArticleCreateRequest
import com.ader.simplepayment.service.dto.ArticleUpdateRequest
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/articles")
class ArticleController(
    private val articleService: ArticleService
) {
    @GetMapping("/{articleId}")
    suspend fun getArticle(@PathVariable articleId: Long) =
        articleService.get(articleId)

    @GetMapping
    suspend fun getArticles(@RequestParam title: String?) =
        articleService.getAll(title)

    @PostMapping
    suspend fun createArticle(@RequestBody articleCreateRequest: ArticleCreateRequest) =
        articleService.create(articleCreateRequest)

    @PutMapping("/{articleId}")
    suspend fun updateArticle(
        @PathVariable articleId: Long,
        @RequestBody articleUpdateRequest: ArticleUpdateRequest
    ) =
        articleService.update(articleId, articleUpdateRequest)

    @DeleteMapping("/{articleId}")
    suspend fun deleteArticle(@PathVariable articleId: Long) =
        articleService.delete(articleId)
}
