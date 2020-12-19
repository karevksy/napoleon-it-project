package com.karevsky.napoleonit.feature.favorites.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.karevsky.napoleonit.R
import com.karevsky.napoleonit.databinding.ActivityMainBinding

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

//TODO: Create Favorite fragment
class FavouriteFragment : Fragment(R.layout.fragment_favourite) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    companion object {
        fun newInstance(param1: String, param2: String) =
            FavouriteFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}