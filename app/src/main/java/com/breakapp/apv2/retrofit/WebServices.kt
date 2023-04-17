package com.breakapp.apv2.retrofit

import com.breakapp.apv2.retrofit.pojos.UserList
import com.breakapp.apv2.retrofit.pojos.addUser
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*


interface WebServices {
    @GET("wsJSONConsultarLista_agenda.php")
    suspend fun getAllUsers(): UserList

    @FormUrlEncoded
    @POST("insert_user.php")
    suspend fun addUser(@FieldMap params: HashMap<String?, String?>): Response<ResponseBody>//addUser//


}