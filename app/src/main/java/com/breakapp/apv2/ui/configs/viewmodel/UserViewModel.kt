package com.breakapp.apv2.ui.configs.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.breakapp.apv2.retrofit.pojos.Users
import com.breakapp.apv2.retrofit.vo.Resource
import com.breakapp.apv2.retrofit.vo.RetrofitClien
import com.breakapp.apv2.ui.configs.viewmodel.repository.UserRepo
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val userRepo: UserRepo) : ViewModel() {

    private val pokeData = MutableLiveData<String>()
    private var bool :Boolean = false

    fun setPokeData(pokeDatas: String) {
        pokeData.value = pokeDatas
    }

    init {
        setPokeData("user")
    }

    val fetchUser = pokeData.distinctUntilChanged().switchMap { pk ->
        liveData(Dispatchers.IO) {
            emit(Resource.Loading())
            try {
                emit(userRepo.getAllUsers())
            } catch (e: Exception) {
                emit(Resource.Failure(e))
                println("Error: " + e)
            }
        }
    }

    fun adduser(params: HashMap<String?, String?>){
        CoroutineScope(Dispatchers.IO).launch {
            // Do the POST request and get response
            val response =userRepo.addUser(params)//userRepo.addUser(params)

            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    // Convert raw JSON to pretty JSON using GSON library
                    val gson = GsonBuilder().setPrettyPrinting().create()
                    val prettyJson = gson.toJson(
                        JsonParser.parseString(
                            response.body()
                                ?.string() // About this thread blocking annotation : https://github.com/square/retrofit/issues/3255
                        )
                    )
                    Log.d("Pretty Printed JSON :", prettyJson)
                } else {
                    Log.e("RETROFIT_ERROR", response.code().toString())
                }
            }
        }
    }



}