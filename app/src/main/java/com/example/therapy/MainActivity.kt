package com.example.therapy

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var fragmentManager: FragmentManager
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fragmentManager = supportFragmentManager
        bottomNavigationView = findViewById(R.id.bottomNavigationView)
        bottomNavigationView.background = null

        bottomNavigationView.setOnItemSelectedListener { item ->
            val fragment: Fragment? = when (item.itemId) {
                R.id.fragment_home -> HomeFragment()
                R.id.fragment_explore -> ExploreFragment()
                R.id.fragment_bookmark -> BookmarkFragment()
                R.id.fragment_profile -> ProfileFragment()
                else -> null
            }

            fragment?.let {
                replaceFragment(it)
                true
            } ?: false
        }

        // Set default fragment
        replaceFragment(HomeFragment())
    }

    private fun replaceFragment(fragment: Fragment) {
        fragmentManager.beginTransaction().apply {
            replace(R.id.frame_layout, fragment)
            addToBackStack(null)
            commit()
        }
    }

    override fun onBackPressed() {
        val currentFragment = supportFragmentManager.findFragmentById(R.id.frame_layout)
        if (currentFragment is HomeFragment) {
            moveTaskToBack(true) // Move the app to the background if on HomeFragment
        } else {
            bottomNavigationView.selectedItemId = R.id.fragment_home
            replaceFragment(HomeFragment())
        }
    }

    fun setOnAddOrgListener(function: () -> Unit) {

    }
}