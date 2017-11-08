package io.sokolvault.wayofturtles.dto

import io.sokolvault.wayofturtles.model.complex.SubGoal
import io.sokolvault.wayofturtles.model.straight.TaskGoal


data class DataTaskSubGoal(override var id: Int,
                           override var title: String,
                           override val compositeGoalId: Int): SubGoal<TaskGoal>(id, title) {

}