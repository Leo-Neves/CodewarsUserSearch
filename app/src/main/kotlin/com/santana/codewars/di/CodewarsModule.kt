package com.santana.codewars.di

import android.content.Context
import androidx.room.Room
import com.santana.codewars.MyApplication
import com.santana.codewars.data.CodewarsApi
import com.santana.codewars.data.dao.UserDao
import com.santana.codewars.data.database.CodewarsDatabase
import com.santana.codewars.data.repository.CodewarsRepositoryImpl
import com.santana.codewars.domain.repository.CodewarsRepository
import com.santana.codewars.domain.usecase.ListUsersUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object CodewarsModule {

    @Singleton
    @Provides
    fun providesDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, CodewarsDatabase::class.java, "codewars").build()

    @Singleton
    @Provides
    fun providesUserDao(database: CodewarsDatabase) = database.userDao()

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
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
    fun providesApi(retrofit: Retrofit): CodewarsApi = retrofit.create(CodewarsApi::class.java)

    @Provides
    @Singleton
    fun providesCodewarsRepository(
        api: CodewarsApi,
        userDao: UserDao
    ): CodewarsRepository =
        CodewarsRepositoryImpl(api, userDao)

    @Provides
    @Singleton
    fun providesListUserUseCase(repository: CodewarsRepository) =
        ListUsersUseCase(repository)
}