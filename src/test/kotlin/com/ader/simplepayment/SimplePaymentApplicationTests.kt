package com.ader.simplepayment

import com.ader.simplepayment.model.Article
import com.ader.simplepayment.repository.ArticleRepository
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class SimplePaymentApplicationTests(
	@Autowired private val articleRepository: ArticleRepository
): StringSpec({
	"context loads" {
		val previous = articleRepository.count()
		articleRepository.save(
			Article(
				title = "test",
				content = "test",
				authorId = 1,
			)
		)
		val current = articleRepository.count()
		previous + 1 shouldBe current
	}
})
