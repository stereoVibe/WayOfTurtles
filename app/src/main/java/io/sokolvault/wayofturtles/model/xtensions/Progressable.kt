package io.sokolvault.wayofturtles.model.xtensions

import io.sokolvault.wayofturtles.data.db.model.JobSubGoalRoom

interface Progressable {
    var progress: Double
    fun calculateProgress(): Double
}