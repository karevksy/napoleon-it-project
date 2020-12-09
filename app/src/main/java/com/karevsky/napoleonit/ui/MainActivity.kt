package com.karevsky.napoleonit.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.karevsky.napoleonit.R


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentManager = supportFragmentManager
        fragmentManager.beginTransaction()
                .add(R.id.container, TopAlbumsFragment())
                .commit()
    }
}

