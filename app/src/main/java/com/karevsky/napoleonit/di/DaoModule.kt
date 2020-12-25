package com.karevsky.napoleonit.di

import android.content.Context
import com.karevsky.napoleonit.data.FavoriteDao
import com.karevsky.napoleonit.data.FavoriteDaoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ApplicationComponent::class)
class DaoModule {

    @Provides
    fun provideFavoriteDao(@ApplicationContext context: Context): FavoriteDao = FavoriteDaoImpl(
        context.getSharedPreferences("data", Context.MODE_PRIVATE)
    )

}