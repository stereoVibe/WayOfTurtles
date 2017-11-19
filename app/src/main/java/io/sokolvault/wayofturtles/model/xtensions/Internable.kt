package io.sokolvault.wayofturtles.model.xtensions

import io.sokolvault.wayofturtles.model.Goal
import io.sokolvault.wayofturtles.model.MonotypeGoal

interface Internable<T: MonotypeGoal> : Goal, Difficulty {
    val hybridGoalId: Int
}