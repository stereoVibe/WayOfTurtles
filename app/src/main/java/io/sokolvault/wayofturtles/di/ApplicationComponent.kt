package io.sokolvault.wayofturtles.di

import android.content.Context
import dagger.Component
import io.sokolvault.wayofturtles.App
import io.sokolvault.wayofturtles.MainActivity
import io.sokolvault.wayofturtles.data.db.GoalsDatabase
import javax.inject.Singleton


@Singleton
@Component(modules = arrayOf(ApplicationModule::class))
interface ApplicationComponent {

    fun injectApp(app: App)
    fun injectMainActivity(activity: MainActivity)

    fun getContext(): Context
    fun getDbInstance(): GoalsDatabase
//    fun getPresenceRepositoryData(): PresenceRepositoryData

//    @ApplicationContext


//    fun getApplication(): Application


}