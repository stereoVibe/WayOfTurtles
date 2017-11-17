package io.sokolvault.wayofturtles.model.xtensions

interface Progressable {
    var progress: Double
    fun calculateProgress(): Double
}