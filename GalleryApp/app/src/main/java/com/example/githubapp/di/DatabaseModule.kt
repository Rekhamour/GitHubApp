package com.example.githubapp.di

import android.app.Application
import androidx.room.Room
import com.example.githubapp.data.db.UserDB
import com.example.githubapp.data.db.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

//this class is modules provies database object and data access object
@Module
@InstallIn(ApplicationComponent::class)
object DataBaseModule {

    //this function provides database instance
    @Provides
    @Singleton
    fun provideUserDB(application: Application?): UserDB {
        return Room.databaseBuilder(application!!, UserDB::class.java, "User Database")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build()
    }

    //this function provide UserDao data access instance.Which fetches modifies the
    //data inside database
    @Provides
    @Singleton
    fun provideUserDao(userDB: UserDB): UserDao {
        return userDB.userDao()!!
    }
}