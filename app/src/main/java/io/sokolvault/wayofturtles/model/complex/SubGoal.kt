package io.sokolvault.wayofturtles.model.complex

import io.sokolvault.wayofturtles.model.straight.SingleGoal

abstract class SubGoal<T: SingleGoal> (
        override var id: Int,
        override var title: String):
        CompositeGoal(id, title){

    abstract val compositeGoalId: Int
}