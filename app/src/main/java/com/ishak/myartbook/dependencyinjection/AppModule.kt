package com.ishak.myartbook.dependencyinjection

import android.content.Context
import androidx.room.Room
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.ishak.myartbook.R
import com.ishak.myartbook.api.RetrofitApi
import com.ishak.myartbook.repo.ArtRepository
import com.ishak.myartbook.repo.ArtRepositoryInterface
import com.ishak.myartbook.roomdb.ArtDao
import com.ishak.myartbook.roomdb.ArtDatabase
import com.ishak.myartbook.util.Util.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun injectRoomDatabase(@ApplicationContext context:Context)= Room.databaseBuilder(
        context,ArtDatabase::class.java,"ArtBookDatabase"
    ).build()

    @Singleton
    @Provides
    fun injectDao(database:ArtDatabase)=database.dao()

    @Singleton
    @Provides
    fun injectRetrofitAPI():RetrofitApi{
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(RetrofitApi::class.java)
    }

    @Singleton
    @Provides
    fun injectNormalRepo(dao : ArtDao, api: RetrofitApi) = ArtRepository(dao,api) as ArtRepositoryInterface

    @Singleton
    @Provides
    fun injectGlide(@ApplicationContext context: Context)=Glide.with(context)
        .setDefaultRequestOptions(
            RequestOptions().placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground)
        )

}