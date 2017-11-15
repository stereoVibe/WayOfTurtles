package io.sokolvault.wayofturtles.di

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ContextModule(context: Context) {
    private val appContext: Context = context

    @Singleton
    @Provides
    fun provideContext(): Context = appContext
}