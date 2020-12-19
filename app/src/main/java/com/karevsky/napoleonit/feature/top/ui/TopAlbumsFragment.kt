package com.karevsky.napoleonit.feature.top.ui

import android.os.Bundle
import android.transition.Transition
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.karevsky.napoleonit.R
import com.karevsky.napoleonit.databinding.ActivityMainBinding
import com.karevsky.napoleonit.databinding.FragmentSearchBinding
import com.karevsky.napoleonit.databinding.FragmentTopAlbumsBinding

class TopAlbumsFragment : Fragment() {

    private lateinit var bind : FragmentTopAlbumsBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?)
            : View {
        bind = FragmentTopAlbumsBinding.inflate(inflater)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}