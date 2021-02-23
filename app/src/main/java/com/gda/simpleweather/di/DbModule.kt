package com.gda.simpleweather.di

import com.gda.simpleweather.db.DbRepo
import dagger.Module
import dagger.Provides

@Module
class DbModule {

    @Provides
    fun getDbRepo() = DbRepo()

}