package com.breakapp.apv2.ui.configs.viewmodel.model

import com.breakapp.apv2.retrofit.pojos.Users
import com.breakapp.apv2.retrofit.vo.Resource
import com.breakapp.apv2.retrofit.vo.RetrofitClien
import okhttp3.ResponseBody

import retrofit2.Response
import javax.inject.Inject

class DataSource@Inject constructor(){
    suspend fun getAllUsers():Resource<List<Users>>{
        return Resource.Success(RetrofitClien.APServices.getAllUsers().userlist)
    }
    suspend fun addUser(params: HashMap<String?, String?>):Response<ResponseBody> {
        return  RetrofitClien.APServices.addUser(params)
    }


}