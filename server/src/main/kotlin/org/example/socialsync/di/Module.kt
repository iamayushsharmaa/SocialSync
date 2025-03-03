package org.example.socialsync.di


import com.mongodb.ConnectionString
import com.mongodb.MongoClientSettings
import com.mongodb.ServerApi
import com.mongodb.ServerApiVersion
import com.mongodb.kotlin.client.coroutine.MongoClient
import org.example.socialsync.auth.jwt.repository.UserDataSource
import org.example.socialsync.auth.jwt.repository.UserDataSourceImpl
import org.example.socialsync.auth.jwt.security.hashing.HashingService
import org.example.socialsync.auth.jwt.security.hashing.SHA256HashingServiceImpl
import org.example.socialsync.auth.jwt.security.token.JwtTokenService
import org.example.socialsync.auth.jwt.security.token.TokenService
import org.example.socialsync.auth.oauth.repository.GoogleOAuthSession
import org.example.socialsync.auth.oauth.repository.GoogleOAuthSessionImpl
import org.koin.dsl.module

val mainModule = module {
    single<MongoClient> {
        val connectionString = System.getenv("MONGO_URL")
        val serverApi = ServerApi.builder()
            .version(ServerApiVersion.V1)
            .build()
        val mongoClientSettings = MongoClientSettings.builder()
            .applyConnectionString(ConnectionString(connectionString))
            .serverApi(serverApi)
            .build()

        MongoClient.create(mongoClientSettings)
    }
    single {
        val mongoClient: MongoClient = get()
        mongoClient.getDatabase("SocialSync")
    }
    single<UserDataSource> {
        UserDataSourceImpl(get())
    }
    single<TokenService> {
        JwtTokenService()
    }
    single<HashingService> {
        SHA256HashingServiceImpl()
    }
    single <GoogleOAuthSession>{
        GoogleOAuthSessionImpl()
    }
}