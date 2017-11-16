package io.sokolvault.wayofturtles.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import io.sokolvault.wayofturtles.data.db.GoalsDatabase
import io.sokolvault.wayofturtles.repositories.presence.PresenceRepositoryData
import javax.inject.Singleton

@Module(includes = arrayOf(ContextModule::class))
class ApplicationModule {

    @Singleton
    @Provides
    fun provideDbInstance(context: Context): GoalsDatabase =
            GoalsDatabase.getDbInstance(context)
}