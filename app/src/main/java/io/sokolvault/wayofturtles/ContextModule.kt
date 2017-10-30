package io.sokolvault.wayofturtles

import android.content.Context
import dagger.Module
import dagger.Provides
import io.sokolvault.wayofturtles.db.BigGoalDAO
import io.sokolvault.wayofturtles.model.BigGoal
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

@Module
class ContextModule(val context: Context) {

    @Provides
    fun context(): Context {
        return context
    }
}