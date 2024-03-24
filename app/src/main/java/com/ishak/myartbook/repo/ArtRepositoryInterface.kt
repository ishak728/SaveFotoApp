package com.ishak.myartbook.repo

import androidx.lifecycle.LiveData
import com.ishak.myartbook.model.ImageResponse
import com.ishak.myartbook.roomdb.Art
import com.ishak.myartbook.util.Resource

interface ArtRepositoryInterface {


    suspend fun insertArt(art : Art)

    suspend fun deleteArt(art: Art)

    fun getArt() : LiveData<List<Art>>

    suspend fun searchImage(imageString : String) : Resource<ImageResponse>
}