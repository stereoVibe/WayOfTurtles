package io.sokolvault.wayofturtles.model.xtensions

import io.sokolvault.wayofturtles.model.MonotypeGoal

interface MonoСyclical {
    val cycleQuantity: Int
    var completedCycles: Int

    fun completeSingleCycle() = { completed: Int, quantity: Int ->
        if (completed < quantity) completed + 1 }(completedCycles, cycleQuantity)

}

class MonoСyclicalDelig(private val monoСyclical: MonoСyclical) : MonoСyclical by monoСyclical