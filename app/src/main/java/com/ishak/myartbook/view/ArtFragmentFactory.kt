package com.ishak.myartbook.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.bumptech.glide.RequestManager
import com.ishak.myartbook.adapter.ArtRecyclerAdapter
import com.ishak.myartbook.adapter.ImageRecyclerAdapter
import javax.inject.Inject

class ArtFragmentFactory @Inject constructor(
    private val artRecyclerAdapter: ArtRecyclerAdapter,
    private val imageRecyclerAdapter: ImageRecyclerAdapter,
    private val glide: RequestManager
): FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {

        return when(className){
            ArtFragment::class.java.name->ArtFragment(artRecyclerAdapter)
            ArtDetailsFragment::class.java.name->ArtDetailsFragment(glide)
            ImageApiFragment::class.java.name->ImageApiFragment(imageRecyclerAdapter)
            else ->super.instantiate(classLoader, className)
        }


    }
}