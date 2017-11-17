package io.sokolvault.wayofturtles.model.xtensions

import io.sokolvault.wayofturtles.model.MonotypeGoal

interface Internable<T: MonotypeGoal> {
    val hybridGoalId: Int
}