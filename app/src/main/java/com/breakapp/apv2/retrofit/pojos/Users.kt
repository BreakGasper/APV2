package com.breakapp.apv2.retrofit.pojos

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import okhttp3.ResponseBody
import retrofit2.Response

@Parcelize
data class Users(
    @SerializedName("id_usuario")
    val idUser: String ="nada",
    @SerializedName("nombre")
    val name: String = "nada",
    @SerializedName("celular")
    val phone: String = "nada",
    @SerializedName("contra")
    val password: String = "nada",
    @SerializedName("terminos_condiciones")
    val t_c: String = "nada",
    @SerializedName("sexo")
    val gender: String = "nada",
    @SerializedName("tipo_user")
    val type_user: String = "nada",

    ) : Parcelable

data class UserList(
    @SerializedName("usuarios")
    val userlist: List<Users>
)
data class addUser(
    @SerializedName("usuarios")
    val addUser: Response<ResponseBody> 

)
