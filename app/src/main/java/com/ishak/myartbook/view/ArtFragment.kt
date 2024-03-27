package com.ishak.myartbook.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ishak.myartbook.R
import com.ishak.myartbook.adapter.ArtRecyclerAdapter
import com.ishak.myartbook.databinding.FragmentArtsBinding
import com.ishak.myartbook.viewmodel.ArtViewModel
import javax.inject.Inject

class ArtFragment @Inject constructor(
    val artRecyclerAdapter:ArtRecyclerAdapter
) : Fragment(R.layout.fragment_arts) {

    private lateinit var binding:FragmentArtsBinding
    lateinit var viewModel:ArtViewModel


    private val swipeCallBack=object :ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT){
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            return true
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

            val layoutPosition=viewHolder.layoutPosition
            val selectedArt=artRecyclerAdapter.arts.get(layoutPosition)

            viewModel.deleteArt(selectedArt)
        }

    }





    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel=ViewModelProvider(requireActivity()).get(ArtViewModel::class.java)

        binding=FragmentArtsBinding.bind(view)
        subscribeToObservers()
        binding.recyclerViewArt.adapter=artRecyclerAdapter
        binding.recyclerViewArt.layoutManager=LinearLayoutManager(requireContext())
        ItemTouchHelper(swipeCallBack).attachToRecyclerView(binding.recyclerViewArt)


        binding.fab.setOnClickListener{
            findNavController().navigate(ArtFragmentDirections.actionArtFragmentToArtDetailsFragment())
        }


    }


    private fun subscribeToObservers(){
        viewModel.artList.observe(viewLifecycleOwner, Observer {
            artRecyclerAdapter.arts=it
        })
    }


}