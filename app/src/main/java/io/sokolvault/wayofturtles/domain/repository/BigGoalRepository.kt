package io.sokolvault.wayofturtles.domain.repository

import android.arch.lifecycle.LiveData
import android.content.Context
import io.sokolvault.wayofturtles.domain.model.BigGoal
import io.sokolvault.wayofturtles.domain.model.SubGoal

interface BigGoalRepository : RepositoryContract<BigGoal, BigGoal> {

    fun <T:SubGoal> getSubGoals(): List<T>

}
