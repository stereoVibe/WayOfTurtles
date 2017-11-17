package io.sokolvault.wayofturtles.model.xtensions

import io.sokolvault.wayofturtles.R

sealed class StepType(val res: Int) {
    object PIECE : StepType(R.string.step_type_times)
    object TIME : StepType(R.string.step_type_times)
    object TIMES : StepType(R.string.step_type_times_adds)
    object SESSION : StepType(R.string.step_type_times)
}