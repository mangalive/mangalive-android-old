package com.acg.mangalive.data.network

import androidx.annotation.IntRange
import com.acg.mangalive.data.network.model.MangaCardDto
import com.acg.mangalive.data.network.model.MangaPageResponseDto
import com.acg.mangalive.domain.model.MangaCard
import com.acg.mangalive.domain.model.SortingCriterion
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.*

interface MangaService {
    @GET("page/{pageNumber}")
    suspend fun getPage(
        @[Path("pageNumber") IntRange(from = 1)] pageNumber: Long = INITIAL_PAGE_NUMBER,
        @[Query("pageSize") IntRange(
            from = 1,
            to = MAX_PAGE_SIZE
        )] pageSize: Long = DEFAULT_PAGE_SIZE,
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
        sortingCriterion: SortingCriterion
    ): Response<MangaPageResponseDto> {
        val cards = mutableListOf<MangaCardDto>()

        for (i in 0..pageSize) {
            cards.add(
                i.toInt(),
                MangaCardDto(
                    id = i + 1,
                    title = "Title $pageNumber:${i + 1}",
                    description = "sadfsd fsdafasdf wegawgw",
                    rating = Random().nextFloat() * 5
                )
            )
        }

        return Response.success(
            MangaPageResponseDto(
                status = "ok",
                message = "Manga page successfully created",
                cards = cards
            )
        )
    }
}