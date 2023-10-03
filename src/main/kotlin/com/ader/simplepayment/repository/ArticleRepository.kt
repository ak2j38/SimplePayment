package com.ader.simplepayment.repository

import com.ader.simplepayment.model.Article
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ArticleRepository: CoroutineCrudRepository<Article, Long> {
}
