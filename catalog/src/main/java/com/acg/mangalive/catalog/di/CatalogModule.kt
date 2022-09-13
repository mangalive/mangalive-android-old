package com.acg.mangalive.catalog.di

import dagger.Module

@Module(includes = [DataModule::class, DomainModule::class])
interface CatalogModule