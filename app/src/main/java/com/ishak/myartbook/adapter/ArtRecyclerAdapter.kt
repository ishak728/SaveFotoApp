package com.ishak.myartbook.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.ishak.myartbook.databinding.ArtRowBinding
import com.ishak.myartbook.roomdb.Art
import javax.inject.Inject

class ArtRecyclerAdapter @Inject constructor(
    val glide:RequestManager
): RecyclerView.Adapter<ArtRecyclerAdapter.ArtViewHolder>() {

    class ArtViewHolder(val binding:ArtRowBinding):RecyclerView.ViewHolder(binding.root)

    private val diffUtil=object :DiffUtil.ItemCallback<Art>(){
        override fun areItemsTheSame(oldItem: Art, newItem: Art): Boolean {
            return oldItem==newItem
        }

        override fun areContentsTheSame(oldItem: Art, newItem: Art): Boolean {
            return oldItem==newItem
        }

    }

    private val recyclerListDiffer=AsyncListDiffer(this,diffUtil)

    var arts:List<Art>
        get() = recyclerListDiffer.currentList
        set(value) = recyclerListDiffer.submitList(value)




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtViewHolder {
        val inflater=LayoutInflater.from(parent.context)
        val binding=ArtRowBinding.inflate(inflater,parent,false)
        return ArtViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return arts.size
    }

    override fun onBindViewHolder(holder: ArtViewHolder, position: Int) {

        val art=arts.get(position)
        holder.binding.apply {
            artRowArtNameText.text=art.name
            artRowArtistNameText.text=art.artistName
            artRowYearText.text=art.year.toString()
            glide.load(art.imageUrl).into(artRowImageView)
        }
    }
}