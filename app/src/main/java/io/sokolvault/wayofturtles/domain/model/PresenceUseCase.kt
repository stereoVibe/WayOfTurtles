package io.sokolvault.wayofturtles.domain.model

import android.arch.lifecycle.LiveData
import io.sokolvault.wayofturtles.model.BaseGoal
import io.sokolvault.wayofturtles.model.Goal

interface PresenceUseCase<G: Goal> {
    fun createGoal(goal: G)
    fun deleteGoal(goal: G)
}