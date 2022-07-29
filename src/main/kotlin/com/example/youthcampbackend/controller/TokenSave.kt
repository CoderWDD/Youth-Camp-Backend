package com.example.youthcampbackend.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TokenSave {
    @Autowired
    lateinit var redisTemplate: RedisTemplate<String,String>

    @PostMapping("/saveClientSecret/{clientSecret}")
    fun saveClientSecret(@PathVariable clientSecret: String) {
        redisTemplate.opsForValue().set("clientSecret", clientSecret)
    }

    @GetMapping("/getClientSecret")
    fun getClientSecret(): String {
        return redisTemplate.opsForValue().get("clientSecret") ?: "Client Secret not found"
    }

    @PostMapping("/saveAccessToken/{accessToken}/{openId}")
    fun saveAccessToken(@PathVariable(name = "accessToken") accessToken: String, @PathVariable(name = "openId") openId: String) {
        redisTemplate.opsForValue().set("accessToken-$openId", accessToken)
    }

    @GetMapping("/getAccessToken/{openId}")
    fun getAccessToken(@PathVariable openId: String): String {
        return redisTemplate.opsForValue().get("accessToken-$openId") ?: "Access Token not found"
    }

    @PostMapping("/saveRefreshToken/{refreshToken}/{openId}")
    fun saveRefreshToken(@PathVariable(name = "refreshToken") refreshToken: String, @PathVariable(name = "openId") openId: String) {
        redisTemplate.opsForValue().set("refreshToken-$openId", refreshToken)
    }

    @GetMapping("/getRefreshToken/{openId}")
    fun getRefreshToken(@PathVariable openId: String): String {
        return redisTemplate.opsForValue().get("refreshToken-$openId") ?: "Refresh Token not found"
    }

    @PostMapping("/saveValue/{key}/{value}/{openId}")
    fun saveValue(@PathVariable(name = "key") key: String,@PathVariable(name = "value") value: String, @PathVariable(name = "openId") openId: String) {
        redisTemplate.opsForValue().set("$key-$openId", value)
    }

    @GetMapping("/getValue/{key}/{openId}")
    fun getValue(@PathVariable(name = "key") key: String,@PathVariable openId: String): String {
        return redisTemplate.opsForValue().get("$key-$openId") ?: "Value not found"
    }

}