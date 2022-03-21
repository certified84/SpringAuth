package com.certified.springauth.repository

import org.springframework.stereotype.Repository

@Repository
interface ResourceRepository {
    fun message() = "Hello World"
}