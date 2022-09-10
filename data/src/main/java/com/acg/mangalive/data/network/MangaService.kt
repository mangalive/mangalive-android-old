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
            if (i % 3 == 0.toLong()) {
                cards.add(
                    i.toInt(),
                    MangaCardDto(
                        id = i + 1,
                        title = "Берсерк",
                        description = "Гатс – Черный Мечник, за которым охотятся демоны. Мечник стремится найти все убежища демонов и отомстить человеку, сделавшего их него «жертвенную овечку». Черный Мечник противостоит жестокой судьбе благодаря непоколебимой воле, невероятной силе, и, конечно же, меча. Постоянные сражения со злом приближает Гатса к параллельному миру тьмы, лишая его человечности. Берсерк – сложная, но сильная история о сражениях и злой судьбе.",
                        rating = Random().nextFloat() * 5
                    )
                )
            }

            if (i % 3 == 1.toLong()) {
                cards.add(
                    i.toInt(),
                    MangaCardDto(
                        id = i + 1,
                        title = "Всеведущий читатель",
                        description = "«Я знаю то, что сейчас будет».\n" +
                                "В тот момент, когда он подумал об этом, мир был уже разрушен, и вдруг открылась новая вселенная. Новая жизнь обычного читателя начинается в мире романа, романа, который смог прочесть лишь он.",
                        rating = Random().nextFloat() * 5
                    )
                )
            }

            if (i % 3 == 2.toLong()) {
                cards.add(
                    i.toInt(),
                    MangaCardDto(
                        id = i + 1,
                        title = "Поднятие уровня в одиночку",
                        description = "10 лет назад, после того, как открылись «Врата», соединившие реальный мир с параллельным, некоторые из людей получили силу охотиться на монстров внутри «Врат». Они известны как «Охотники». Однако не все Охотники сильные. Меня зовут Сун Джин-Ву, охотник E-ранга. Я тот, кто рискует своей жизнью в самых низких уровнях подземелья. Не имея никаких сверхсильных навыков, я едва зарабатывал необходимые деньги, сражаясь в низкоуровневых подземельях... по крайней мере, пока я не нашел скрытое подземелье с самыми трудными проблемами в подземельях D-ранга! В конце концов, когда я чуть не умер, я внезапно получил странную силу, журнал квестов, который мог видеть только я, секрет для поднятия уровня, о котором знаю только я! Если я тренировался в соответствии с моими квестами и охотился на монстров, то мой уровень повышался. Переход от самого слабого Охотника к самому сильному, Охотнику S-ранга!",
                        rating = Random().nextFloat() * 5
                    )
                )
            }
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