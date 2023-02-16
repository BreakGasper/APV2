package com.breakapp.apv2.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.breakapp.apv2.R
import com.breakapp.apv2.databinding.ActivityMainBinding
import com.breakapp.apv2.ui.configs.ConfigActivity


class MainActivity : AppCompatActivity() {
    private lateinit var b: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityMainBinding.inflate(layoutInflater)
        setContentView(b.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        NavigationUI.setupActionBarWithNavController(this, navController)

    }

    override fun onNavigateUp(): Boolean {
        return navController.navigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.overflow_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
//        Toast.makeText(this, ""+id+"\n"+ Rb.id.setting_id,Toast.LENGTH_SHORT).show()
        if (id == R.id.setting_id) {
            val intent = Intent(this, ConfigActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.left_in, R.anim.left_out)
        }
        return super.onOptionsItemSelected(item)
    }
}