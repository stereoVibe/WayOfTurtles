package io.sokolvault.wayofturtles.carriers

import io.sokolvault.wayofturtles.model.JobGoal


data class DataJobGoal(override var id: Int,
                       override var title: String,
                       override val jobsQuantity: Int)
    :JobGoal() {
    override var description: String = ""
}