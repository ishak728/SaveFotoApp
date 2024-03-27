package com.ishak.myartbook.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.ishak.myartbook.databinding.ArtRowBinding
import com.ishak.myartbook.databinding.ImageRowBinding
import com.ishak.myartbook.roomdb.Art
import javax.inject.Inject


class ImageRecyclerAdapter @Inject constructor(
    val glide: RequestManager
): RecyclerView.Adapter<ImageRecyclerAdapter.ImageViewHolder>() {

    private var onItemClickListener : ((String) -> Unit)? = null

    class ImageViewHolder(val binding: ImageRowBinding): RecyclerView.ViewHolder(binding.root)

    private val diffUtil=object : DiffUtil.ItemCallback<String>(){
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem==newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem==newItem
        }


    }

    private val recyclerListDiffer= AsyncListDiffer(this,diffUtil)

    var images:List<String>
        get() = recyclerListDiffer.currentList
        set(value) = recyclerListDiffer.submitList(value)




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val inflater= LayoutInflater.from(parent.context)
        val binding= ImageRowBinding.inflate(inflater,parent,false)
        return ImageViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return images.size
    }

    fun setOnItemClickListener(listener : (String) -> Unit) {
        onItemClickListener = listener
    }


    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {


        val url = images[position]
        holder.itemView.apply {
            glide.load(url).into(holder.binding.imageView)
            setOnClickListener {
                onItemClickListener?.let {
                    it(url)
                }
            }
        }
    }
}