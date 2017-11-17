package io.sokolvault.wayofturtles.model

import io.sokolvault.wayofturtles.model.xtensions.*

/* Core class for all goals in the app */
interface Goal {
     var id: Int
     var title: String
     var description: String
     var isComplete: Boolean
     var goalCategory: Enum<GoalCategory>
}

abstract class MonotypeGoal
    :Goal

abstract class HybridGoal
    :Goal, Compoundable<MonotypeGoal>, Progressable

abstract class JobGoal
    : MonotypeGoal(), Progressable, MonoСyclical {
    abstract val counts: StepUnit
}