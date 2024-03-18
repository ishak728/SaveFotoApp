package com.ishak.myartbook.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.ishak.myartbook.R
import com.ishak.myartbook.databinding.FragmentArtDetailsBinding

class ArtDetailsFragment:Fragment(R.layout.fragment_art_details) {

    private lateinit var binding:FragmentArtDetailsBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding=FragmentArtDetailsBinding.bind(view)

        binding.artImageView.setOnClickListener(){
           findNavController().navigate(ArtDetailsFragmentDirections.actionArtDetailsFragmentToImageApiFragment())
        }




    }
}