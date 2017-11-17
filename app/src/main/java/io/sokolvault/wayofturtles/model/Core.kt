package io.sokolvault.wayofturtles.model

import io.sokolvault.wayofturtles.model.xtensions.*

/* Core class for all goals in the app */
abstract class Goal {
    abstract var id: Int
    abstract var title: String
    abstract var description: String
    abstract var isComplete: Boolean
    abstract var goalCategory: Enum<GoalCategory>
}

abstract class MonotypeGoal
    :Goal()

abstract class HybridGoal
    :Goal(), Compoundable<MonotypeGoal>, Progressable

abstract class TaskGoal
    : MonotypeGoal()

abstract class JobGoal
    : MonotypeGoal(), Progressable, MonoСyclical {
    abstract val counts: StepUnit
}