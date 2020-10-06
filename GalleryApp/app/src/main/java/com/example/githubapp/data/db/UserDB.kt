package com.example.githubapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.githubapp.data.model.UserData

@Database(entities = [UserData::class], version = 3, exportSchema = false)
abstract class UserDB : RoomDatabase() {
    abstract fun userDao(): UserDao
}
