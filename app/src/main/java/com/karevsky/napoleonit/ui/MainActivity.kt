package com.karevsky.napoleonit.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import com.karevsky.napoleonit.R
import com.karevsky.napoleonit.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
                            .commit()
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.menuItemFav ->{
                    fragmentManager.beginTransaction()
                            .replace(R.id.container, FavouriteFragment())
                            .commit()
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.menuItemSearch ->{
                    fragmentManager.beginTransaction()
                            .replace(R.id.container, SearchFragment())
                            .commit()
                    return@setOnNavigationItemSelectedListener true
                }
                else -> false
            }
        }
    }

}


