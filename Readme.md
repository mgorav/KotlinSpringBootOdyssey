# Spring Boot Odyssey With Kotlin + JPA (Hibernate) + Derby

This service exposes a Blog APIs build using Spring Boot + Kotlin + JPA (Hibernate) + Derby

## Requirements

1. Java - >= 1.8

2. Derby - 10.14.2.0

3. Spring Boot 2.0.2.RELEASE

4. Kotlin - 1.2.41

## Instruction to setup

#####1. Clone the application

```bash
git clone https://github.com/mgorav/KotlinSpringBootOdyssey.git
```


#####2. Running the App

Use following command to run the application -

```bash
mvn spring-boot:run
```

All the APIs exposed can be viewed using swagger UI

```
http://localhost:9999/swagger-ui.html
```

##### Glossary of all the APIs exposed



    GET /api/blogs
    
    POST /api/blogs
    
    GET /api/blogs/{id}
    
    PUT /api/blogs/{id}
    
    DELETE /api/blogs/{id}

