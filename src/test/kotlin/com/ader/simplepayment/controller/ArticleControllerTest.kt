package com.ader.simplepayment.controller

import com.ader.simplepayment.model.Article
import com.ader.simplepayment.repository.ArticleRepository
import com.ader.simplepayment.service.ArticleService
import com.ader.simplepayment.service.dto.ArticleCreateRequest
import io.kotest.core.spec.style.StringSpec
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.ApplicationContext
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient

@SpringBootTest
class ArticleControllerTest(
    @Autowired private val articleRepository: ArticleRepository,
    @Autowired private val articleService: ArticleService,
    @Autowired private val context: ApplicationContext
) : StringSpec({

    val client = WebTestClient.bindToApplicationContext(context).build()

    beforeTest {
        articleRepository.deleteAll()
    }

    "getArticle" {
        val created = client.post()
            .uri("/v1/articles")
            .bodyValue(
                ArticleCreateRequest(
                    title = "test title",
                    content = "test content",
                    authorId = 1,
                )
            )
            .exchange()
            .expectStatus().isEqualTo(HttpStatus.CREATED)
            .expectBody(Article::class.java)
            .returnResult()
            .responseBody!!
        client.get().uri("/v1/articles/${created.id}").accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isOk
            .expectBody()
            .jsonPath("id").isEqualTo(created.id)
    }

    "getArticles" {
        articleService.create(
            ArticleCreateRequest(
                title = "test title1",
                content = "test content1",
                authorId = 1,
            )
        )
        articleService.create(
            ArticleCreateRequest(
                title = "test title2",
                content = "test content2",
                authorId = 2,
            )
        )
        articleService.create(
            ArticleCreateRequest(
                title = "test title3",
                content = "test content3",
                authorId = 3,
            )
        )

        client.get().uri("/v1/articles").accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isOk
            .expectBody()
            .jsonPath("$[0].title").isEqualTo("test title1")
            .jsonPath("$[1].title").isEqualTo("test title2")
            .jsonPath("$[2].title").isEqualTo("test title3")
    }

    "createArticle" {
        val createRequest = ArticleCreateRequest(
            title = "test title",
            content = "test content",
            authorId = 1,
        )

        client.post().uri("/v1/articles").accept(MediaType.APPLICATION_JSON)
            .bodyValue(createRequest)
            .exchange()
            .expectStatus().isEqualTo(HttpStatus.CREATED)
            .expectBody()
            .jsonPath("title").isEqualTo(createRequest.title)
            .jsonPath("content").isEqualTo(createRequest.content)
            .jsonPath("authorId").isEqualTo(createRequest.authorId)
    }

    "updateArticle" {
        val created = articleService.create(
            ArticleCreateRequest(
                title = "test title",
                content = "test content",
                authorId = 1,
            )
        )
        client.post().uri("/v1/articles").accept(MediaType.APPLICATION_JSON)
            .bodyValue(created)
            .exchange()
            .expectStatus().isEqualTo(HttpStatus.CREATED)
            .expectBody(Article::class.java)
            .returnResult()
            .responseBody!!

        val updateRequest = ArticleCreateRequest(
            title = "update title",
            content = "update content",
            authorId = 2,
        )

        client.put().uri("/v1/articles/${created.id}").accept(MediaType.APPLICATION_JSON)
            .bodyValue(updateRequest)
            .exchange()
            .expectStatus().isOk
            .expectBody()
            .jsonPath("title").isEqualTo(updateRequest.title)
            .jsonPath("content").isEqualTo(updateRequest.content)
            .jsonPath("authorId").isEqualTo(updateRequest.authorId)
    }

    "deleteArticle" {
        val created = articleService.create(
            ArticleCreateRequest(
                title = "test title",
                content = "test content",
                authorId = 1,
            )
        )
        client.post().uri("/v1/articles").accept(MediaType.APPLICATION_JSON)
            .bodyValue(created)
            .exchange()
            .expectStatus().isEqualTo(HttpStatus.CREATED)
            .expectBody(Article::class.java)
            .returnResult()
            .responseBody!!

        client.delete().uri("/v1/articles/${created.id}").accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isOk
    }
})
