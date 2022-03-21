package com.certified.springauth.service

import com.certified.springauth.repository.ResourceRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ResourceService constructor(@Autowired private val repository: ResourceRepository) {

    fun message() = repository.message()
}