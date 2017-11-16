package io.sokolvault.wayofturtles.domain.presence

import io.sokolvault.wayofturtles.model.Goal

interface PresenceUseCase<G: Goal> {
    fun createGoal(goal: G)
    fun deleteGoal(goal: G)
}