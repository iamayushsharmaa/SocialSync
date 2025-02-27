package org.example.socialsync.di


import com.mongodb.ConnectionString
import com.mongodb.MongoClientSettings
import com.mongodb.ServerApi
import com.mongodb.ServerApiVersion
import com.mongodb.kotlin.client.coroutine.MongoClient
import org.example.socialsync.auth.data.repository.UserDataSource
import org.example.socialsync.auth.data.repository.UserDataSourceImpl
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
}