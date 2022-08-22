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

interface MangaService {
    @GET("page/{pageNumber}")
    suspend fun getPage(
        @Path("pageNumber") @IntRange(from = 1) pageNumber: Long = INITIAL_PAGE_NUMBER,
        @Query("pageSize") @IntRange(
            from = 1,
            to = MAX_PAGE_SIZE
        ) pageSize: Long = DEFAULT_PAGE_SIZE,
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
        return Response.success(
            MangaPageResponseDto(
                "ok",
                "Manga page successfully created",
                listOf(
                    MangaCardDto(id= 1, title = "Title 1", description = "sss sdfwegh agawpeghawpgw w", rating = 1f),
                    MangaCardDto(id= 2, title = "SFowh wohgowhgw wg", description = "sss sdfwegh sw wa;ghw;ghw; awoow w", rating = 2.8f),
                    MangaCardDto(id= 3, title = "as'f hOSIhfsdhflshfowhegowh ohweofwhfiewhwho owfowfowhfowhfowo wohfwofhwohfwohfow wfowofhwhwfohwo", description = "sss sdfwegh agawpeghawpgw w woefowefowowo owwowowowowowowohog haphwoaheg phawpehgowhgo haeopghwpehgpawhgep hapgawpeghawpehgpawghp awgphwaepghawphgpa wghawepg", rating = 3.4f),
                    MangaCardDto(id= 4, title = "Title  owhweoweh whggowo", description = "sss  owoehgohawoa wwwwgoh hagaw agawpeghawpgw w", rating = 5f),
                )
            )
        )
    }
}