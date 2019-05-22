/*
 *  Copyright (C) 2019 Eton Otieno
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package io.devbits.newsfeed.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import io.devbits.newsfeed.R
import io.devbits.newsfeed.ui.fragments.BookmarkedFragment
import io.devbits.newsfeed.ui.fragments.CategoryFragment
import io.devbits.newsfeed.ui.fragments.HomeFragment
import io.devbits.newsfeed.utils.bindView

class MainActivity : AppCompatActivity() {

    private lateinit var currentFragment: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val mainNavView by bindView<BottomNavigationView>(R.id.main_nav_view)

        mainNavView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.menu_home -> replaceFragment(HomeFragment())
                R.id.menu_category -> replaceFragment(CategoryFragment())
                R.id.menu_bookmarked -> replaceFragment(BookmarkedFragment())
                else -> false
            }
        }

        // Add a listener to prevent reselects from being treated as selects.
        mainNavView.setOnNavigationItemReselectedListener {}

        if (savedInstanceState == null) {
            // Show the Home page when the screen is opened
            mainNavView.selectedItemId = R.id.menu_home
        } else {
            // Find the current fragment
            currentFragment =
                    supportFragmentManager.findFragmentById(FRAGMENT_ID)
                            ?: throw IllegalStateException("Activity recreated, but no fragment found!")
        }
    }

    // Replace Fragment and return true after transaction
    private fun replaceFragment(fragment: Fragment): Boolean {
        currentFragment = fragment
        supportFragmentManager.beginTransaction()
                .replace(FRAGMENT_ID, fragment)
                .commit()
        return true
    }

    companion object {
        private const val FRAGMENT_ID = R.id.fragment_container
    }
}