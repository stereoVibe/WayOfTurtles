package io.sokolvault.wayofturtles

interface SubGoal<in CompositeGoal> {
    fun fetch(goal: CompositeGoal)

}