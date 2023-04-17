package com.breakapp.apv2.ui.configs.viewmodel.repository

import com.breakapp.apv2.retrofit.pojos.Users
import com.breakapp.apv2.retrofit.vo.Resource
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response

interface UserRepo {
    suspend fun getAllUsers(): Resource<List<Users>>
    suspend fun addUser(params: HashMap<String?, String?>): Response<ResponseBody>

}