package com.gm.kotlin.blog.controller

import com.gm.kotlin.blog.domain.Blog
import com.gm.kotlin.blog.repository.BlogRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid


@RestController
@RequestMapping("/api")
class BlogController(private val blogRepository: BlogRepository) {

    @GetMapping("/blogs")
    fun getAllBlogs(): List<Blog> =
            blogRepository.findAll()


    @PostMapping("/blogs")
    fun createNewBlog(@Valid @RequestBody blog: Blog): Blog =
            blogRepository.save(blog)


    @GetMapping("/blogs/{id}")
    fun getBlogById(@PathVariable(value = "id") articleId: Long): ResponseEntity<Blog> {
        return blogRepository.findById(articleId).map { article ->
            ResponseEntity.ok(article)
        }.orElse(ResponseEntity.notFound().build())
    }

    @PutMapping("/blogs/{id}")
    fun updateBlogById(@PathVariable(value = "id") articleId: Long,
                       @Valid @RequestBody newBlog: Blog): ResponseEntity<Blog> {

        return blogRepository.findById(articleId).map { existingArticle ->
            val updatedBlog: Blog = existingArticle
                    .copy(title = newBlog.title, content = newBlog.content)

            ResponseEntity.ok().body(blogRepository.save(updatedBlog))
        }.orElse(ResponseEntity.notFound().build())

    }

    @DeleteMapping("/blogs/{id}")
    fun deleteBlogById(@PathVariable(value = "id") articleId: Long): ResponseEntity<Void> {

        return blogRepository.findById(articleId).map { article ->
            blogRepository.delete(article)
            ResponseEntity<Void>(HttpStatus.OK)
        }.orElse(ResponseEntity.notFound().build())

    }
}