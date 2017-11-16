package io.sokolvault.wayofturtles.model

import io.sokolvault.wayofturtles.model.xtensions.CountUnit

interface Internable<in T: SingleGoal>:Goal {
    val compositeGoalId: Int
}

abstract class TaskGoal
    :SingleGoal()

abstract class JobGoal
    :SingleGoal(){
    abstract val jobsQuantity: Int
    abstract val counts: CountUnit
    var completedJobs: Int = 0

    fun completeOneJob(){
        completedJobs += 1
        if (completedJobs == jobsQuantity) this.isComplete = true
    }
    fun setCompletedJob(completedJobs: Int){
        if (completedJobs >= 0) this.completedJobs = completedJobs
        if (completedJobs == jobsQuantity) this.isComplete = true
    }
    fun calculateSumProgress(): Double = ((completedJobs*100)/jobsQuantity).toDouble()
}