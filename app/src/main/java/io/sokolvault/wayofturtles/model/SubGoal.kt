package io.sokolvault.wayofturtles.model

interface SubGoal<in CompositeGoal> {
    fun fetch(goal: CompositeGoal)
}