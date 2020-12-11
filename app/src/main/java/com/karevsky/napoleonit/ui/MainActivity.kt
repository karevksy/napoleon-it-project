package com.karevsky.napoleonit.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.FragmentTransaction

import com.karevsky.napoleonit.R
import com.karevsky.napoleonit.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //set bindings
        val bind = ActivityMainBinding.inflate(layoutInflater)
        val view = bind.root
        setContentView(view)

        //disable dark theme
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        val fragmentManager = supportFragmentManager
        if (savedInstanceState == null){
            fragmentManager.beginTransaction()
                    .add(R.id.container, TopAlbumsFragment())
                    .commit()
        }

        //bottom navigation menu listener
        bind.userNav.setOnNavigationItemSelectedListener { item ->
            when(item.itemId){
                R.id.menuItemTop ->{
                    fragmentManager.beginTransaction()
                            .replace(R.id.container, TopAlbumsFragment())
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                            .commit()
                    true
                }
                R.id.menuItemFav ->{
                    fragmentManager.beginTransaction()
                            .replace(R.id.container, FavouriteFragment())
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                            .commit()
                    true
                }
                R.id.menuItemSearch ->{
                    fragmentManager.beginTransaction()
                            .replace(R.id.container, SearchFragment())
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                            .commit()
                    true
                }
                else -> false
            }
        }

        bind.userNav.setOnNavigationItemReselectedListener { item ->
            when(item.itemId){
                R.id.menuItemTop -> {}
                R.id.menuItemFav -> {}
                R.id.menuItemSearch -> {}
            }
        }


    }
}


