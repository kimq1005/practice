package com.llama.data.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.llama.data.retrofit.UserService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit

val Llama_Host = "http://192.168.0.12:8080"

//192.168.0.116 -> 투썸
//192.168.0.12 -> 컴포즈


@Module
@InstallIn(SingletonComponent::class)
class RetrofitModule {

    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient
            .Builder()
            .build()
    }

    @Provides
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        val converterFactory = Json {
            ignoreUnknownKeys = true
        }.asConverterFactory("application/json; charset=UTF8".toMediaType())

        return Retrofit.Builder()
            .baseUrl("${Llama_Host}/api/")
            .addConverterFactory(converterFactory)
            .client(client)
            .build()
    }

    @Provides
    fun provideUserService(retrofit: Retrofit): UserService {
        return retrofit.create(UserService::class.java)
    }
}