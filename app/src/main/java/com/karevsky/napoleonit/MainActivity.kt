package com.karevsky.napoleonit

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.karevsky.napoleonit.databinding.ActivityMainBinding
import com.karevsky.napoleonit.feature.favorites.ui.FavoriteFragment
import com.karevsky.napoleonit.feature.search.ui.SearchFragment
import com.karevsky.napoleonit.feature.topAlbums.ui.TopAlbumsFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //set bindings
        val bind = ActivityMainBinding.inflate(layoutInflater)
        val view = bind.root
        setContentView(view)

        //disable dark theme
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.findNavController()
        userNav.setupWithNavController(navController)
    }
}


