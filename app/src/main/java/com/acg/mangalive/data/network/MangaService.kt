package com.acg.mangalive.data.network

import retrofit2.http.GET

interface MangaService {
    @GET("v2/everything")
    fun mangaList()
}