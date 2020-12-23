package com.karevsky.napoleonit

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.karevsky.napoleonit.databinding.ActivityMainBinding
import com.karevsky.napoleonit.feature.favorites.ui.FavoriteFragment
import com.karevsky.napoleonit.feature.search.ui.SearchFragment
import com.karevsky.napoleonit.feature.topAlbums.ui.TopAlbumsFragment

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
        if (savedInstanceState == null) {
            fragmentManager.beginTransaction()
                .add(R.id.container, TopAlbumsFragment())
                .commit()
        }

        //bottom navigation menu listener
        bind.userNav.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menuItemTop -> {
                    fragmentManager.beginTransaction()
                        .replace(R.id.container, TopAlbumsFragment(), "TopAlbums")
                        .commit()
                    true
                }
                R.id.menuItemFav -> {
                    fragmentManager.beginTransaction()
                        .replace(R.id.container, FavoriteFragment())
                        .commit()
                    true
                }
                R.id.menuItemSearch -> {
                    fragmentManager.beginTransaction()
                        .replace(R.id.container, SearchFragment())
                        .commit()
                    true
                }
                else -> false
            }
        }

        bind.userNav.setOnNavigationItemReselectedListener { item ->
            when (item.itemId) {
                R.id.menuItemTop -> {
                    val fragment = fragmentManager.findFragmentByTag("TopAlbums")
                    if(fragment == null || !fragment.isVisible){
                        fragmentManager.beginTransaction()
                            .replace(R.id.container, TopAlbumsFragment(), "TopAlbums")
                            .commit()
                    }
                }
                R.id.menuItemFav -> {
                }
                R.id.menuItemSearch -> {
                }
            }
        }
    }
}


