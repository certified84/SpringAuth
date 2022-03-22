package com.certified.springauth.service

import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UserDetailService : UserDetailsService {
    override fun loadUserByUsername(username: String?): UserDetails {
        return User(username, "Kt", mutableListOf())
    }
}