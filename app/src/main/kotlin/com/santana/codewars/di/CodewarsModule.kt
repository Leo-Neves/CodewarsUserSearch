package com.santana.codewars.di

import android.content.Context
import androidx.room.Room
import com.santana.codewars.data.CodewarsApi
import com.santana.codewars.data.dao.UserDao
import com.santana.codewars.data.database.CodewarsDatabase
import com.santana.codewars.data.repository.CodewarsRepositoryImpl
import com.santana.codewars.domain.repository.CodewarsRepository
import com.santana.codewars.domain.usecase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object CodewarsModule {

    @Singleton
    @Provides
    @Named("url")
    fun providesBaseURL() = "https://www.codewars.com/api/v1/"

    @Singleton
    @Provides
    @Named("token")
    fun providesToken() = "z_Z8moFuyN1LnbQnqSt2"

    @Singleton
    @Provides
    fun providesDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, CodewarsDatabase::class.java, "codewars").build()

    @Singleton
    @Provides
    fun providesUserDao(database: CodewarsDatabase) = database.userDao()

    @Provides
    @Singleton
    fun provideOkHttpClient(@Named("token")token: String): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val tokenInterceptor = Interceptor { chain ->
            val requestBuilder = chain.request().newBuilder().header("Authorization", token)
            chain.proceed(requestBuilder.build())
        }
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(tokenInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun providesRetrofit(okHttpClient: OkHttpClient, @Named("url") baseURL: String) =
        Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
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

    @Provides
    @Singleton
    fun providesFetchUsersUseCase(repository: CodewarsRepository, listUsersUseCase: ListUsersUseCase) =
        FetchUsersUseCase(repository, listUsersUseCase)

    @Provides
    @Singleton
    fun providesFetchChallengesCompletedUseCase(repository: CodewarsRepository) =
        FetchChallengesCompletedUseCase(repository)

    @Provides
    @Singleton
    fun providesFetchChallengesAuthoredUseCase(repository: CodewarsRepository) =
        FetchChallengesAuthoredUseCase(repository)

    @Provides
    @Singleton
    fun providesFetchChallengeDetailsUseCase(repository: CodewarsRepository) =
        FetchChallengeDetailsUseCase(repository)
}