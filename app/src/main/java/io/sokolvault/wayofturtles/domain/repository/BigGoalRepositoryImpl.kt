package io.sokolvault.wayofturtles.domain.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import io.sokolvault.wayofturtles.AbsentLiveData
import io.sokolvault.wayofturtles.data.db.GoalsDatabase
import io.sokolvault.wayofturtles.data.db.model.BigGoalEntity
import io.sokolvault.wayofturtles.domain.model.BigGoal
import io.sokolvault.wayofturtles.domain.model.GoalCategory
import io.sokolvault.wayofturtles.domain.model.SubGoal
import io.sokolvault.wayofturtles.utils.DbOps
import javax.inject.Inject
import io.sokolvault.wayofturtles.utils.DbOps.Companion.asyncInsert
import io.sokolvault.wayofturtles.utils.DbOps.Companion.status
import io.sokolvault.wayofturtles.utils.Status

class BigGoalRepositoryImpl : BigGoalRepository {

    @Inject
    lateinit var goalsDb: GoalsDatabase

    val singleLiveData: LiveData<BigGoal> = AbsentLiveData.create()

    override fun createNewGoal(goal: BigGoal): LiveData<BigGoal> {
        val goalId =
                goalsDb.bigGoalDAO().asyncInsert(goal as BigGoalEntity)
        do {
            val status = DbOps.Companion.checkStatus()
        } while (status == Status.SUCCESS)

        val bigGoal = goalsDb.bigGoalDAO().getBigGoalById(goalId) as BigGoal
        singleLiveData as MutableLiveData<BigGoal>
        singleLiveData.postValue(bigGoal)

        return singleLiveData
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