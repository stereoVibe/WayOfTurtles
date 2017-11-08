package io.sokolvault.wayofturtles.dto

import io.sokolvault.wayofturtles.model.straight.TaskGoal

data class DataTaskGoal(override var id: Int,
                        override var title: String): TaskGoal(id, title) {
}