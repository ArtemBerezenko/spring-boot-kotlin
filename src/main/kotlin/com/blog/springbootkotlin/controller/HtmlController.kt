package com.blog.springbootkotlin.controller

import com.blog.springbootkotlin.entities.Article
import com.blog.springbootkotlin.entities.User
import com.blog.springbootkotlin.format
import com.blog.springbootkotlin.repository.ArticleRepository
import com.blog.springbootkotlin.service.MarkdownConverter
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@Controller
class HtmlController(private val repository: ArticleRepository,
                     private val markdownConverter: MarkdownConverter) {
    @GetMapping("/")
    fun blog(model: Model): String {
        model["title"] = "Blog"
        model["articles"] = repository.findAllByOrderByAddedAtDesc().map { it.render() }
        return "blog"
    }

    @GetMapping("/article/{id}")
    fun article(@PathVariable id: Long, model: Model): String {
        val article = repository
                .findById(id)
                .orElseThrow { IllegalAccessException("Wrong article id provided")}
                .render()
        model["title"] = article.title
        model["article"] = article
        return "article"
    }

    fun Article.render() = RenderedArticle(
            title,
            markdownConverter(headline),
            markdownConverter(content),
            author,
            id,
            addedAt.format()
    )

    data class RenderedArticle(
            val title: String,
            val headline: String,
            val content: String,
            val author: User,
            val id: Long?,
            val addedAt: String)
}