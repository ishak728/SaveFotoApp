package com.ishak.myartbook.repo

import androidx.lifecycle.LiveData
import com.ishak.myartbook.api.RetrofitApi
import com.ishak.myartbook.model.ImageResponse
import com.ishak.myartbook.roomdb.Art
import com.ishak.myartbook.roomdb.ArtDao
import com.ishak.myartbook.util.Resource
import javax.inject.Inject

class ArtRepository @Inject constructor(
    private val artDao:ArtDao,
    private val retrofitApi: RetrofitApi
) :ArtRepositoryInterface {
    override suspend fun insertArt(art: Art) {
        artDao.insertArt(art)
    }

    override suspend fun deleteArt(art: Art) {
        artDao.deleteArt(art)
    }

    override fun getArt(): LiveData<List<Art>> {
        return artDao.observeArts()
    }

    override suspend fun searchImage(imageString: String): Resource<ImageResponse> {
        return  try {
             val response=retrofitApi.imageSearch(imageString)
             if (response.isSuccessful){
                 response.body()?.let {
                     return@let Resource.success(it)
                 }?: Resource.error("error!no data",null)
             }else{
                 Resource.error("error!no data",null)
             }

         }catch(e:Exception) {
             Resource.error("error exception!no data",null)
         }
    }
}