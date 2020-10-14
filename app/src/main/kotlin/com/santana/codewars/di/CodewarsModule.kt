package com.santana.codewars.di

import android.content.Context
import androidx.room.Room
import com.santana.codewars.MyApplication
import com.santana.codewars.data.CodewarsApi
import com.santana.codewars.data.database.CodewarsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(MyApplication::class)
class CodewarsModule(
    private val context: Context
) {

    @Singleton
    @Provides
    fun providesDatabase() = Room.databaseBuilder(context, CodewarsDatabase::class.java, "").build()

    @Singleton
    @Provides
    fun providesUserDao(database: CodewarsDatabase) = database.userDao()

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient{
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun providesRetrofit(okHttpClient: OkHttpClient) =
        Retrofit.Builder()
            .baseUrl("")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

    @Provides
    @Singleton
    fun providesApi(retrofit: Retrofit):CodewarsApi = retrofit.create(CodewarsApi::class.java)
}