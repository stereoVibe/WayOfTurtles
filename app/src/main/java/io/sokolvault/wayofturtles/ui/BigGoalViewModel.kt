package io.sokolvault.wayofturtles.ui

import android.app.Application
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.content.Context
import io.sokolvault.wayofturtles.AbsentLiveData
import io.sokolvault.wayofturtles.data.Resource
import io.sokolvault.wayofturtles.domain.interactors.CreateNewBigGoalUseCase
import io.sokolvault.wayofturtles.domain.model.BigGoal
import io.sokolvault.wayofturtles.domain.model.GoalCategory
import io.sokolvault.wayofturtles.domain.model.SubGoal
import io.sokolvault.wayofturtles.domain.repository.BigGoalRepository
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.runBlocking
import nl.komponents.kovenant.Kovenant.context
import org.jetbrains.anko.coroutines.experimental.asReference
import org.jetbrains.anko.coroutines.experimental.bg
import org.jetbrains.anko.doAsyncResult
import java.io.IOException
import java.util.*
import javax.inject.Inject

class BigGoalViewModel: GoalViewModel<BigGoal>(), BigGoalRepository {

    var singleGoal: LiveData<BigGoal> = MutableLiveData<BigGoal>()
    override lateinit var goalsList: LiveData<Resource<List<BigGoal>>>

    override fun createNewGoal(goal: BigGoal) {
        CreateNewBigGoalUseCase().execute(goal, singleGoal)
    }

    override fun updateGoal(goal: BigGoal): LiveData<BigGoal> {
        return this.updateGoal(goal)
    }

    override fun deleteGoal(goal: BigGoal): LiveData<BigGoal> {
        return this.deleteGoal(goal)
    }

    override fun getGoalById(id: Int): LiveData<BigGoal> {
        return this.getGoalById(id)
    }

    override fun getGoalsFilteredByCategoryTag(category: GoalCategory): Set<LiveData<BigGoal>> {
        return this.getGoalsFilteredByCategoryTag(category)
    }

    override fun addCategoryTagToGoal(category: GoalCategory) {
        return this.addCategoryTagToGoal(category)
    }

    override fun setCompleteStatus(status: Boolean) {
        return this.setCompleteStatus(status)
    }

    override fun setProgress(progress: Int) {
        return this.setProgress(progress)
    }

    override fun <T : SubGoal> getSubGoals(): List<T> {
        return this.getSubGoals()
    }
}