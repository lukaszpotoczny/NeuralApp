package com.example.neuralapp.di

import android.content.Context
import androidx.room.Room
import com.example.neuralapp.data.db.UserDao
import com.example.neuralapp.data.db.UserDatabase
import com.example.neuralapp.data.rest.ApiService
import com.example.neuralapp.repository.HouseRepository
import com.example.neuralapp.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideApi(@Named("retrofit") retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun providesCarRepository(api: ApiService): HouseRepository {
        return HouseRepository(api)
    }

    @Singleton
    @Provides
    fun providesUserRepository(userDao: UserDao): UserRepository {
        return UserRepository(userDao)
    }

    @Singleton
    @Provides
    fun provideUserDao(appDatabase: UserDatabase): UserDao {
        return appDatabase.userDao()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): UserDatabase {
        return Room.databaseBuilder(
            appContext,
            UserDatabase::class.java,
            "RssReader"
        ).build()
    }
}