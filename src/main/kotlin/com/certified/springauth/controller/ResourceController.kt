package com.certified.springauth.controller

import com.certified.springauth.service.ResourceService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(path = ["api/v1/resource"])
class ResourceController constructor(@Autowired private val service: ResourceService) {

    @GetMapping
    fun message() = service.message()
}