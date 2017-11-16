package io.sokolvault.wayofturtles.carriers

import io.sokolvault.wayofturtles.model.TaskGoal

data class DataTaskGoal(override var id: Int,
                        override var title: String): TaskGoal() {
    override var description: String = ""
}