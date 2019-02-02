package com.blog.springbootkotlin.entities

import java.time.LocalDateTime
import javax.persistence.*

@Entity
data class Article(
        val title: String,
        val headline: String,
        val content: String,
        @ManyToOne @JoinColumn
        val author: User,
        @Id @GeneratedValue
        val id: Long? = null,
        val addedAt: LocalDateTime = LocalDateTime.now()
)