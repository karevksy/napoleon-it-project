package com.karevsky.napoleonit.di

import android.content.Context
import com.karevsky.napoleonit.data.dao.favorite.FavoriteDao
import com.karevsky.napoleonit.data.dao.favorite.FavoriteDaoImpl
import com.karevsky.napoleonit.data.dao.genres.GenreDao
import com.karevsky.napoleonit.data.dao.genres.GenresDaoImpl
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

    @Provides
    fun provideGenreDao(@ApplicationContext context: Context): GenreDao = GenresDaoImpl(
        context.getSharedPreferences("cache", Context.MODE_PRIVATE)
    )

}