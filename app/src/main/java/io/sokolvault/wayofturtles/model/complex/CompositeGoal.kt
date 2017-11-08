package io.sokolvault.wayofturtles.model.complex

import io.sokolvault.wayofturtles.model.base.Goal


abstract class CompositeGoal(override var id: Int, override var title: String) : Goal() {
}