package com.gm.kotlin.blog.repository

import com.gm.kotlin.blog.domain.Blog
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BlogRepository : JpaRepository<Blog, Long>