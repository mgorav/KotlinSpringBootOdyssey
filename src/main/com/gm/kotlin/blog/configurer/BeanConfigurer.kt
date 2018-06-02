package com.gm.kotlin.blog.configurer

import com.gm.kotlin.blog.controller.BlogController
import com.gm.kotlin.blog.domain.Blog
import com.gm.kotlin.blog.repository.BlogRepository
import com.google.common.base.Predicates.not
import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.transaction.annotation.EnableTransactionManagement
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors.regex
import springfox.documentation.builders.RequestHandlerSelectors.basePackage
import springfox.documentation.service.ApiInfo
import springfox.documentation.spi.DocumentationType.SWAGGER_2
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

@Configuration
@EnableSwagger2
@EnableTransactionManagement(proxyTargetClass = true)
class BeanConfigurer {

    @Bean
    fun applicationRunner(blogRepository: BlogRepository): ApplicationRunner {


        return ApplicationRunner {
            arrayOf(Blog(title = "Sample title 1", content = "Sample content 1"),
                    Blog(title = "Sample title 2", content = "Sample content 1"))
                    .forEach { blogRepository.save(it) }
        }

    }

    @Bean
    fun docklet(): Docket {
        return Docket(SWAGGER_2)
                .select()
                .apis(basePackage(BlogController::class.java!!.getPackage().getName()))
                .paths(not(regex("/error")))
                .build()
                .pathMapping("/")
                .apiInfo(apiInfo())
    }

    private fun apiInfo(): ApiInfo {

        val description = "Spring BOOT + Kotlin + JPA"
        return ApiInfoBuilder()
                .title(description)
                .description(description)
                .license("GM")
                .licenseUrl("")
                .version("1.0")
                .build()

    }
}