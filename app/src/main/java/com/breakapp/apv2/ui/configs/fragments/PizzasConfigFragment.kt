package com.breakapp.apv2.ui.configs.fragments

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.breakapp.apv2.databinding.FragmentPizzasConfigBinding
import com.breakapp.apv2.ui.configs.viewmodel.PizzasViewModel
import com.google.android.material.textfield.TextInputEditText


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PizzasConfigFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PizzasConfigFragment : Fragment() {

    private var _binding: FragmentPizzasConfigBinding? = null
    private val b get() = _binding!!
    lateinit var editText: TextInputEditText

    var list_EditText = ArrayList<TextInputEditText>()

    private val vm_pizza by viewModels<PizzasViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentPizzasConfigBinding.inflate(inflater, container, false)

        return b.getRoot();
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        b.ibAddPc.setOnClickListener({
            b.tvIngredientsPc.setText("" + vm_pizza.AddIngredient(b.tilIngredientPc.text.toString()))
        })
        b.ivSavePizza.setOnClickListener({
            GuardarDatos()
        })
        setupRecyclerView()

    }

    fun setupRecyclerView(){
        b.rvListaPizzas.layoutManager = LinearLayoutManager(requireContext())
//        b.rvListaPizzas.adapter = AdapterListConfig()
    }


    fun validarVacios(): Boolean {
        //Add components to list
        list_EditText.add(b.tilNombrePc)
        list_EditText.add(b.tilPrecioPc)
        list_EditText.add(b.tilDescPc)
        var bol = vm_pizza.validationTIETBox(list_EditText)
        list_EditText.clear()

        return bol
    }


    fun GuardarDatos() {

        try {
           if (validarVacios()){
               Toast.makeText(context, "Guardado en Pizzas", Toast.LENGTH_SHORT).show()
           }

        } catch (e: Exception) {
            println("ErrorConfig: " + e)
            Toast.makeText(context, "" + e, Toast.LENGTH_SHORT).show()
        }

    }


}