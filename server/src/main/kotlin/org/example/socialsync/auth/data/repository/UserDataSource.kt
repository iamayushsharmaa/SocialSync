package org.example.socialsync.auth.data.repository

import org.example.socialsync.auth.data.models.User

interface UserDataSource{
    suspend fun getUserByUsername(username : String) : User?
    suspend fun insertUser(user : User) : Boolean
}