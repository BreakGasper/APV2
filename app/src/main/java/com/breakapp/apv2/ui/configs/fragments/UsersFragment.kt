package com.breakapp.apv2.ui.configs.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.breakapp.apv2.databinding.FragmentUsersBinding
import com.breakapp.apv2.retrofit.WebServices
import com.breakapp.apv2.retrofit.pojos.Users
import com.breakapp.apv2.retrofit.vo.Resource
import com.breakapp.apv2.ui.configs.ConfigActivity
import com.breakapp.apv2.ui.configs.adapters.AdapterListUsersConfig
import com.breakapp.apv2.ui.configs.viewmodel.UserViewModel
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.WithFragmentBindings
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@AndroidEntryPoint
@WithFragmentBindings
class UsersFragment : Fragment(), AdapterListUsersConfig.OnUserClickListener {

    private var _binding: FragmentUsersBinding? = null
    private val b get() = _binding!!
    lateinit var adapterPokemon: AdapterListUsersConfig
    private val vm by viewModels<UserViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentUsersBinding.inflate(inflater, container, false)

        return b.getRoot();
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpPokemon()
    }

    fun showForm(context: Context,bol:Boolean) {
        try {

            b.lFormSingin.isVisible = bol
            Toast.makeText(context, "Usuario: "
                    + bol, Toast.LENGTH_SHORT).show()
        }catch (e:Exception){
            Toast.makeText(context, "E: "
                    + e, Toast.LENGTH_SHORT).show()
        }


    }

    fun setUpPokemon() {

        try {
            vm.fetchUser.observe(requireActivity(), { res ->
                println("Pokemon: " + res)
                when (res) {
                    is Resource.Loading -> {

                        Log.d("POKELIST", "${res}")
                    }
                    is Resource.Success -> {

                        Log.d("POKELIST", "${res.data}")
                        setUpUser(res.data)
                    }
                    is Resource.Failure -> {

                        Log.d("POKELIST", "${res.exception}")
                    }
                    else -> {
                        Log.d("POKELIST", "${res}")
                    }
                }
            })
        } catch (e: Exception) {
            println("ErrrorFra: " + e)
            Toast.makeText(requireActivity(), "Error: " + e, Toast.LENGTH_SHORT).show()
        }
    }

    fun setUpUser(pk: List<Users>) {
        adapterPokemon = AdapterListUsersConfig(requireActivity(), pk, this)
        b.rvUsers.setHasFixedSize(true)
        b.rvUsers.layoutManager = LinearLayoutManager(requireActivity())
        b.rvUsers.adapter = adapterPokemon
    }

    override fun onUserClick(user: Users, position: Int) {
         Toast.makeText(requireActivity(), "Usuario: " + user.name, Toast.LENGTH_SHORT).show()
    }


    fun insertUser() {
        // Create HashMap with fields
        val params = HashMap<String?, String?>()
        params["nombre"] = "Jack"
        params["celular"] = "8054"
        params["contra"] = "45"
        params["terminos_condiciones"] = "Jack"
        params["sexo"] = "8054"
        params["tipo_user"] = "45"
        vm.adduser(params)
    }

    fun urlEncoded() {
        // Create Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl("https://anonimuspizza.ga/Anonimus_pizza/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        // Create Service
        val service = retrofit.create(WebServices::class.java)

        // Create HashMap with fields
        val params = HashMap<String?, String?>()
        params["nombre"] = "Jack"
        params["celular"] = "8054"
        params["contra"] = "45"
        params["terminos_condiciones"] = "Jack"
        params["sexo"] = "8054"
        params["tipo_user"] = "45"

        CoroutineScope(Dispatchers.IO).launch {

            // Do the POST request and get response
            val response = service.addUser(params)

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