package com.gm.kotlin.blog.controller

import com.gm.kotlin.blog.domain.Blog
import com.gm.kotlin.blog.repository.BlogRepository
import org.springframework.http.HttpStatus.OK
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.notFound
import org.springframework.http.ResponseEntity.ok
import org.springframework.web.bind.annotation.*
import javax.validation.Valid


@RestController
@RequestMapping("/api")
class BlogController(private val blogRepository: BlogRepository) {

    @GetMapping("/blogs")
    fun getAllBlogs(): List<Blog> = blogRepository.findAll()


    @PostMapping("/blogs")
    fun createNewBlog(@Valid @RequestBody blog: Blog): Blog = blogRepository.save(blog)


    @GetMapping("/blogs/{id}")
    fun getBlogById(@PathVariable(value = "id") blogId: Long): ResponseEntity<Blog> {
        return blogRepository.findById(blogId)
                .map {
                    blog -> ok(blog)
                }.orElse(notFound().build())
    }

    @PutMapping("/blogs/{id}")
    fun updateBlogById(@PathVariable(value = "id") blogId: Long, @Valid @RequestBody newBlog: Blog): ResponseEntity<Blog> {

        return blogRepository.findById(blogId)
                .map {
                    existingBlog -> ok()
                        .body(blogRepository.save(existingBlog.copy(title = newBlog.title, content = newBlog.content)))
                }.orElse(notFound().build())

    }

    @DeleteMapping("/blogs/{id}")
    fun deleteBlogById(@PathVariable(value = "id") blogId: Long): ResponseEntity<Void> {

        return blogRepository.findById(blogId)
                .map {
                    article -> blogRepository.delete(article)
            ResponseEntity<Void>(OK)
        }.orElse(notFound().build())

    }
}