package com.breakapp.apv2.ui.configs.viewmodel

import android.R.attr.x
import androidx.lifecycle.ViewModel
import com.google.android.material.textfield.TextInputEditText


class PizzasViewModel (): ViewModel(){


    private var list_ingredient = ArrayList<String>()


    fun AddIngredient(ingredients:String): String {

        list_ingredient.add(ingredients)
        var datosArray=""
        for (elemento in list_ingredient) {
            datosArray += "$elemento, \n "
        }
        return datosArray
    }

    fun validationTIETBox(tilArray: ArrayList<TextInputEditText>): Boolean {
        var bol:Boolean = true

        //validar si esta vacio
        try {

            for (i in 0..tilArray.size - 1) {
                var tie = tilArray.get(i)
                if (tie.text!!.isEmpty()) {
                    tie.setError("Debes llenar el campo")
                    bol = false
                }
            }
        } catch (e: Exception) {
            println("Error Array datos" + e)
            bol = false
//            Toast.makeText(context, "Error: " + e, Toast.LENGTH_LONG).show()
        }
        return bol

    }

}