package org.example.socialsync.auth.jwt.repository

import org.example.socialsync.auth.jwt.models.User

interface UserDataSource{
    suspend fun getUserByUsername(username : String) : User?
    suspend fun insertUser(user : User) : Boolean
}