package io.sokolvault.wayofturtles.carriers

import io.sokolvault.wayofturtles.model.xtensions.Internable
import io.sokolvault.wayofturtles.model.TaskGoal


data class DataTaskSubGoal(override var id: Int,
                           override var title: String,
                           override val hybridGoalId: Int):TaskGoal(), Internable<TaskGoal> {
    override var description: String = ""

}