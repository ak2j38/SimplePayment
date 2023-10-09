package com.ader.simplepayment.service

import com.ader.simplepayment.repository.ArticleRepository
import com.ader.simplepayment.service.dto.ArticleCreateRequest
import com.ader.simplepayment.service.dto.ArticleUpdateRequest
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.ints.shouldBeGreaterThan
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.flow.toList
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.reactive.TransactionalOperator
import org.springframework.transaction.reactive.executeAndAwait

@SpringBootTest
class ArticleServiceTest(
    @Autowired private val articleService: ArticleService,
    @Autowired private val articleRepository: ArticleRepository,
    @Autowired private val transactionalOperator: TransactionalOperator
) : StringSpec({

    "create and get" {
        transactionalOperator.executeAndAwait {
            val previous = articleRepository.count()
            val article = articleService.create(
                ArticleCreateRequest(
                    title = "test title",
                    content = "test content",
                    authorId = 1,
                )
            )
            val current = articleRepository.count()
            previous + 1 shouldBe current
            article.id shouldBe articleService.get(article.id).id
        }
    }

    "getAll" {
        transactionalOperator.executeAndAwait {
            val created = articleService.create(
                ArticleCreateRequest(
                    title = "test title",
                    content = "test content",
                    authorId = 1,
                )
            )
            articleService.getAll(null).toList().size shouldBeGreaterThan 0
        }
    }

    "update" {
        transactionalOperator.executeAndAwait {
            val article = articleService.create(
                ArticleCreateRequest(
                    title = "test title",
                    content = "test content",
                    authorId = 1,
                )
            )

            val updateRequest = ArticleUpdateRequest(
                title = "update title",
                content = "update content",
                authorId = 2,
            )
            articleService.update(article.id, updateRequest)

            articleService.get(article.id).let {
                it.title shouldBe "update title"
                it.content shouldBe "update content"
                it.authorId shouldBe 2
            }
        }
    }

    "delete" {
        transactionalOperator.executeAndAwait {
            val article = articleService.create(
                ArticleCreateRequest(
                    title = "test title",
                    content = "test content",
                    authorId = 1,
                )
            )

            val previous = articleRepository.count()
            articleService.delete(article.id)
            val current = articleRepository.count()
            previous - 1 shouldBe current
        }
    }
})
