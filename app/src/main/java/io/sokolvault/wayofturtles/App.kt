package io.sokolvault.wayofturtles

import android.app.Application
import android.content.Context
import io.sokolvault.wayofturtles.di.ApplicationComponent
import io.sokolvault.wayofturtles.di.ApplicationModule
import io.sokolvault.wayofturtles.di.ContextModule
import io.sokolvault.wayofturtles.di.DaggerApplicationComponent

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        appComponent = buildComponent()
    }

    companion object {
        private lateinit var appComponent : ApplicationComponent
        fun getComponent(): ApplicationComponent = appComponent
        fun get(context: Context): App = context.applicationContext as App
    }

    private fun buildComponent() : ApplicationComponent{
        return DaggerApplicationComponent
                .builder()
                .contextModule(ContextModule(this))
                .applicationModule(ApplicationModule())
                .build()
    }
}