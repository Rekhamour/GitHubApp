package com.example.githubapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
//data class that stores user data from github
@Entity(tableName = "user_table")
data class UserData (@PrimaryKey(autoGenerate = true) var id: Int, var avatar:String, var author:String, var name:String, var stars:String):Serializable