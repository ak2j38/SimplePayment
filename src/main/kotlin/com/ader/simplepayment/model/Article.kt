package com.ader.simplepayment.model

import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime
import javax.annotation.processing.Generated

@Table("ARTICLE")
class Article(
    @Id
    @Generated
    @Column("ID")
    var id: Long = 0,

    @Column("TITLE")
    var title: String? = null,

    @Column("CONTENT")
    var content: String? = null,

    @Column("AUTHOR_ID")
    var authorId: Long? = null,

    @CreatedDate
    @Column("CREATED_AT")
    val createdAt: LocalDateTime = LocalDateTime.now(),


    @LastModifiedDate
    @Column("UPDATED_AT")
    var updatedAt: LocalDateTime = LocalDateTime.now(),

) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Article

        if (id != other.id) return false
        if (title != other.title) return false
        if (content != other.content) return false
        if (authorId != other.authorId) return false
        if (createdAt != other.createdAt) return false
        if (updatedAt != other.updatedAt) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + (title?.hashCode() ?: 0)
        result = 31 * result + (content?.hashCode() ?: 0)
        result = 31 * result + (authorId?.hashCode() ?: 0)
        result = 31 * result + createdAt.hashCode()
        result = 31 * result + updatedAt.hashCode()
        return result
    }
}
