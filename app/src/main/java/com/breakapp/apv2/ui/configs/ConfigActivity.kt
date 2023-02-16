package com.breakapp.apv2.ui.configs

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.breakapp.apv2.R
import com.breakapp.apv2.databinding.ActivityConfigBinding
import com.breakapp.apv2.ui.configs.adapters.ViewPagerAdapter
import com.breakapp.apv2.ui.configs.fragments.*


class ConfigActivity : AppCompatActivity() {
    private lateinit var b: ActivityConfigBinding
    private var viewPagerAdapter: ViewPagerAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityConfigBinding.inflate(layoutInflater)
        setContentView(b.root)

        try {
            supportActionBar!!.hide()
            actionBar!!.hide()
        } catch (e: Exception) {
            Log.d("CONFIGACTIVITY ",e.toString())
        }

        setupViewPager2()

    }

    private fun setupViewPager2() {

        //Init Adapter
        viewPagerAdapter = ViewPagerAdapter(this, supportFragmentManager, lifecycle)
        // add Fragments in your ViewPagerFragmentAdapter class
        viewPagerAdapter!!.addFragment(
            UsersFragment(),
            getString(R.string.f_users),
            R.drawable.svg_user
        )
        viewPagerAdapter!!.addFragment(
            PizzasConfigFragment(),
            getString(R.string.f_pizzas),
            R.drawable.svg_pizza
        )
        viewPagerAdapter!!.addFragment(
            BebidasConfigFragment(),
            getString(R.string.f_bebidas),
            R.drawable.svg_bebidas
        )
        viewPagerAdapter!!.addFragment(
            AdicionalesConfigFragment(),
            getString(R.string.f_adicional),
            R.drawable.svg_adicional
        )
        viewPagerAdapter!!.addFragment(
            PromoConfigFragment(),
            getString(R.string.f_promocion),
            R.drawable.svg_promocion
        )
        // set Orientation in your ViewPager2
        b.viewPager.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL)
        //Adapter show
        b.viewPager.setAdapter(viewPagerAdapter)
        //get Method get Titles
        viewPagerAdapter!!.getTitles(b.tabLayout, b.viewPager)


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.overflow_save, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

//        val sharedPreference =  getSharedPreferences("FRAG_TITLE", Context.MODE_PRIVATE)
//        var title_fg = sharedPreference.getString("fg_position","defaultName")

        //saving for fragment
        if (id == R.id.save_id) {

            when (b.tabLayout.selectedTabPosition) {
                0 -> {
                    ToasTitle(getString(R.string.f_users))
                }
                1 -> {
                    PizzasConfigFragment.GuardarDatos(this)

//                    ToasTitle(getString(R.string.f_pizzas))

                }
                2 -> {
                    ToasTitle(getString(R.string.f_bebidas))
                }
                3 -> {
                    ToasTitle(getString(R.string.f_adicional))
                }
                4 -> {
                    ToasTitle(getString(R.string.f_promocion))
                }
            }

        }
        return super.onOptionsItemSelected(item)
    }

    fun ToasTitle(title: String) {
        Toast.makeText(this, "Guardado en " + title, Toast.LENGTH_SHORT).show()
    }

}