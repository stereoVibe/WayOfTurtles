package io.sokolvault.wayofturtles.dto

import io.sokolvault.wayofturtles.model.straight.SingleGoal


data class DataJobGoal(override var id: Int,
                       override var title: String,
                       private val jobsQuantity: Int): SingleGoal(id, title) {

    private var completedJobs: Int = 0

    fun calculateSumProgress(): Double {
        return ((completedJobs*100)/jobsQuantity).toDouble()
    }
}