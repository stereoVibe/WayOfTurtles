package io.sokolvault.wayofturtles.model.straight


abstract class TaskGoal(override var id: Int,
                        override var title: String): SingleGoal(id, title) {
}
