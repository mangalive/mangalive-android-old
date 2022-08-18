package com.acg.mangalive.data.network

import androidx.annotation.IntRange
import com.acg.mangalive.data.network.model.MangaPageResponseDto
import com.acg.mangalive.domain.model.MangaCard
import com.acg.mangalive.domain.model.SortingCriterion
import com.acg.mangalive.domain.model.SortingDirection
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MangaService {
    @GET("page/{pageNumber}")
    suspend fun getPage(
        @Path("pageNumber") @IntRange(from = 1) pageNumber: Long = INITIAL_PAGE_NUMBER,
        @Query("pageSize") @IntRange(
            from = 1,
            to = MAX_PAGE_SIZE
        ) pageSize: Long = DEFAULT_PAGE_SIZE,
        @Query("sortingDirection") sortingDirection: SortingDirection = SortingDirection.byAscending,
        @Query("sortingCriterion") sortingCriterion: SortingCriterion = SortingCriterion.popularity,
    ): Response<MangaPageResponseDto>

    companion object {
        const val INITIAL_PAGE_NUMBER: Long = 1
        const val MAX_PAGE_SIZE: Long = 20
        const val DEFAULT_PAGE_SIZE: Long = MAX_PAGE_SIZE
    }
}

class FakeMangaService : MangaService {
    override suspend fun getPage(
        pageNumber: Long,
        pageSize: Long,
        sortingDirection: SortingDirection,
        sortingCriterion: SortingCriterion
    ): Response<MangaPageResponseDto> {
        return Response.success(
            MangaPageResponseDto(
                "ok",
                "Manga page successfully created",
                listOf()
            )
        )
    }
}