package io.sokolvault.wayofturtles.model

interface Internable<in T: SingleGoal>:Goal {
    val compositeGoalId: Int
}

abstract class TaskGoal
    :SingleGoal()

abstract class JobGoal
    :SingleGoal(){
    abstract val jobsQuantity: Int
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