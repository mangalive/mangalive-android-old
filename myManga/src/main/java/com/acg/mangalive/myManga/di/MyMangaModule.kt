package com.acg.mangalive.myManga.di

import dagger.Module

@Module(includes = [DataModule::class, DomainModule::class])
interface MyMangaModule