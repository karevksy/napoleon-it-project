package com.karevsky.napoleonit.ui

import android.os.Bundle
import android.transition.Transition
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.karevsky.napoleonit.R
import com.karevsky.napoleonit.databinding.ActivityMainBinding
import com.karevsky.napoleonit.databinding.FragmentTopAlbumsBinding

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

//TODO: Create Top albums fragment
class TopAlbumsFragment : Fragment(R.layout.fragment_top_albums) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //TODO: smth
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    companion object {
        fun newInstance(param1: String, param2: String) =
                TopAlbumsFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}