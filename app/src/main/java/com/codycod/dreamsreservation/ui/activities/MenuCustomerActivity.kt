package com.codycod.dreamsreservation.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.codycod.dreamsreservation.R
import com.codycod.dreamsreservation.ui.fragments.ProfileUserFragment
import com.codycod.dreamsreservation.ui.fragments.RoomsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MenuCustomerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_customer)

        val bottomNavMenu = findViewById<BottomNavigationView>(R.id.bottom_menu_nav_user)
        bottomNavMenu.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.ibn_rooms_user -> {
                    updateFragment(RoomsFragment.newInstance())
                    true
                }

                R.id.ibn_profile_user -> {
                    updateFragment(ProfileUserFragment.newInstance())
                    true
                }

                else -> false
            }
        }
        // we set fragment selected by default
        bottomNavMenu.selectedItemId = R.id.ibn_rooms_user

    }

    //method to update the fragment

    private fun updateFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fl_container_user, fragment)
        transaction.commit()
    }

}