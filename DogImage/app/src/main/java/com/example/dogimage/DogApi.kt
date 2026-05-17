package com.example.dogimage

import retrofit2.http.GET
import retrofit2.http.Path

interface DogApi {
    @GET("breed/{bread}/image/random")
    suspend fun getRandomDogImage(@Path("breed") breed: String  ): DogResponse
}