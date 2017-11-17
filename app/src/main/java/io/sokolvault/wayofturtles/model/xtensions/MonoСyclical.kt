package io.sokolvault.wayofturtles.model.xtensions

interface MonoСyclical {
    val cycleQuantity: Int
    var completedCycles: Int

    fun completeSingleCycle() = { completed: Int, quantity: Int ->
        if (completed < quantity) completed + 1 }(completedCycles, cycleQuantity)

}