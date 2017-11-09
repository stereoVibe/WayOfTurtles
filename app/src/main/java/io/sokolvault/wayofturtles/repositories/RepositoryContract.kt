package io.sokolvault.wayofturtles.repositories

import android.arch.lifecycle.LiveData
import io.sokolvault.wayofturtles.model.Goal
import io.sokolvault.wayofturtles.model.xtensions.GoalCategory


interface RepositoryContract <in I: Goal, O: Goal> {

    fun createNewGoal(goal: I)

    fun updateGoal(goal: I): LiveData<O>

    fun deleteGoal(goal: I): LiveData<O>

    fun getGoalById(id: Int): LiveData<O>

//    fun getAllGoalsAsSet(): Set<O>
//
//    fun getAllGoalsAsList(): List<O>

    fun getGoalsFilteredByCategoryTag(category: GoalCategory): Set<LiveData<O>>

    fun addCategoryTagToGoal(category: GoalCategory)

    fun setCompleteStatus(status: Boolean)

    fun setProgress(progress: Int)

}