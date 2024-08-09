package com.lennrbriones.smashcharacters.di

import androidx.compose.ui.tooling.preview.Preview
import com.lennrbriones.smashcharacters.data.ApiSmash
import com.lennrbriones.smashcharacters.utils.Constants.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providesRetrofit(): Retrofit{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun providesAPISmash(retrofit: Retrofit) : ApiSmash {
        return retrofit.create(ApiSmash::class.java)
    }


}