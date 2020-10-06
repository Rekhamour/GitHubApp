package com.example.githubapp.viewmodel

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.githubapp.Utils.ViewState
import com.example.githubapp.data.model.UserData
import com.example.githubapp.network.service.Repository
import kotlinx.coroutines.async
import kotlinx.coroutines.launch


class UsersViewModel @ViewModelInject constructor(var repository: Repository): ViewModel() {
    private val TAG = "UserViewModel"

    private val users = MutableLiveData<ViewState<List<UserData>>>()

    init {
        fetchUsers()
    }

    private fun fetchUsers() {
        viewModelScope.launch {
            users.postValue(ViewState.loading())
            try {
                val usersFromDb = async {repository.getUsersFromDb()}
                usersFromDb.await();
                if (usersFromDb.await().isEmpty()) {
                    val usersFromApi = async{repository.getUserfromApi()}
                    usersFromApi.await();
                    val usersToInsertInDB = mutableListOf<UserData>()

                    for (apiUser in usersFromApi.await()) {
                        val user = UserData(
                                apiUser.id,
                                apiUser.avatar,
                                apiUser.author,
                                apiUser.name,
                                apiUser.stars
                        )
                        usersToInsertInDB.add(user)
                    }
                    repository.deleteUsersfromDB();
                    repository.insertUsersToDB(usersToInsertInDB)

                    users.postValue(ViewState.success(usersToInsertInDB))

                } else {
                    users.postValue(ViewState.success(usersFromDb.await()))
                }


            } catch (e: Exception) {
                users.postValue(ViewState.error(error("Something Went Wrong")))
            }
        }
    }

    fun getUsers(): LiveData<ViewState<List<UserData>>> {
        return users
    }

}