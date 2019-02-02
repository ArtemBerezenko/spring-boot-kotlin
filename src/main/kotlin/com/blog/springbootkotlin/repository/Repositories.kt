package com.blog.springbootkotlin.repository

import com.blog.springbootkotlin.entities.Article
import com.blog.springbootkotlin.entities.User
import org.springframework.data.repository.CrudRepository

interface ArticleRepository : CrudRepository<Article, Long> {
    fun findAllByOrderByAddedAtDesc(): Iterable<Article>
}

interface UserRepository : CrudRepository<User, String>