package com.example.youthcampbackend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication()
class YouthCampBackendApplication

fun main(args: Array<String>) {
    runApplication<YouthCampBackendApplication>(*args)
}
