package com.breakapp.apv2.ui.configs.viewmodel.repository

import com.breakapp.apv2.retrofit.pojos.Users
import com.breakapp.apv2.retrofit.vo.Resource
import com.breakapp.apv2.ui.configs.viewmodel.model.DataSource
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject


class UserRepoImpl @Inject constructor(private val dataSource: DataSource): UserRepo {
    override suspend fun getAllUsers(): Resource<List<Users>> {
        return  dataSource.getAllUsers()
    }
    override suspend fun addUser(params: HashMap<String?, String?>): Response<ResponseBody> {
        return  dataSource.addUser(params)
    }


}