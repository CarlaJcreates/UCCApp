package com.example.uccapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Set up the toolbar
        val toolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "UCC App"

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_directory -> {
                    loadFragment(DirectoryFragment(), "Staff Directory")
                    true
                }
                R.id.nav_courses -> {
                    loadFragment(CoursesFragment(), "IT Department Courses")
                    true
                }
                R.id.nav_admissions -> {
                    loadFragment(AdmissionsFragment(), "UCC Admissions")
                    true
                }
                R.id.nav_social_media -> {
                    loadFragment(SocialMediaFragment(), "Connect with Us - UCC Social Media")
                    true
                }
                else -> false
            }
        }

        // Load the default fragment
        bottomNavigationView.selectedItemId = R.id.nav_directory
    }

    private fun loadFragment(fragment: Fragment, title: String) {
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()

        supportActionBar?.title = title
    }
}
