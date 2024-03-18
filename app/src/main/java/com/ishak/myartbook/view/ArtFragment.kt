package com.ishak.myartbook.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.ishak.myartbook.R
import com.ishak.myartbook.databinding.FragmentArtsBinding

class ArtFragment: Fragment(R.layout.fragment_arts) {

    private lateinit var binding:FragmentArtsBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding=FragmentArtsBinding.bind(view)
        binding.fab.setOnClickListener{
            findNavController().navigate(ArtFragmentDirections.actionArtFragmentToArtDetailsFragment())
        }

    }
}