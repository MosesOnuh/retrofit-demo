package com.example.ichadomosesdisepexam.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.ichadomosesdisepexam.R
import com.example.ichadomosesdisepexam.db.UserDatabase
import com.example.ichadomosesdisepexam.repository.UserRepository


class MainActivity : AppCompatActivity() {

    lateinit var viewModel: ProgramViewModel
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.navHostFragment) as NavHostFragment
        navController = navHostFragment.navController
        setupActionBarWithNavController(navController)

        val userRepository = UserRepository(UserDatabase(this))
        val viewModelProviderFactory = UserViewModelProviderFactory( userRepository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(ProgramViewModel::class.java)
        }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

}


