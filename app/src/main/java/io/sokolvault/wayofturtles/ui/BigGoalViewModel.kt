package io.sokolvault.wayofturtles.ui

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import io.sokolvault.wayofturtles.data.Resource
import io.sokolvault.wayofturtles.domain.presence.CreateNewBigGoalUseCase
import io.sokolvault.wayofturtles.domain.output.GetBigGoalByIdUseCase
import io.sokolvault.wayofturtles.domain.presence.PresenceInteractor
import io.sokolvault.wayofturtles.model.CompositeGoal
import io.sokolvault.wayofturtles.model.xtensions.GoalCategory
import io.sokolvault.wayofturtles.model.Internable
import io.sokolvault.wayofturtles.model.SingleGoal
import io.sokolvault.wayofturtles.repositories.BigGoalRepository
import io.sokolvault.wayofturtles.repositories.presence.PresenceRepositoryImpl

class BigGoalViewModel: GoalViewModel<CompositeGoal>(), BigGoalRepository {

    var singleGoal: LiveData<CompositeGoal> = MutableLiveData<CompositeGoal>()
    override lateinit var goalsList: LiveData<Resource<List<CompositeGoal>>>

    override fun createNewGoal(goal: CompositeGoal) {
//        CreateNewBigGoalUseCase().execute(goal, singleGoal)
        PresenceInteractor
                .UseCaseForCompositeGoal(PresenceRepositoryImpl())
                .createGoal(goal)
    }

    override fun updateGoal(goal: CompositeGoal): LiveData<CompositeGoal> {
        return this.updateGoal(goal)
    }

    override fun deleteGoal(goal: CompositeGoal): LiveData<CompositeGoal> {
        return this.deleteGoal(goal)
    }

    override fun getGoalById(id: Int): LiveData<CompositeGoal> {
        singleGoal = GetBigGoalByIdUseCase().execute(id)
        return singleGoal
    }

    override fun getGoalsFilteredByCategoryTag(category: GoalCategory): Set<LiveData<CompositeGoal>> {
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

    fun <T : Internable<SingleGoal>> getSubGoals(): List<T> {
        return this.getSubGoals()
    }
}