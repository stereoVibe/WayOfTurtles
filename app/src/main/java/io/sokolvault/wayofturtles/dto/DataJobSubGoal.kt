package io.sokolvault.wayofturtles.dto

import io.sokolvault.wayofturtles.model.straight.JobGoal
import io.sokolvault.wayofturtles.model.complex.SubGoal


data class DataJobSubGoal(override var id: Int,
                          override var title: String,
                          override val compositeGoalId: Int) : SubGoal<JobGoal>(id, title) {

}