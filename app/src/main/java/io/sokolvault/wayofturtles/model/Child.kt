package io.sokolvault.wayofturtles.model


//abstract class SubGoal<T: SingleGoal> (override var id: Int, override var title: String)
//    :CompositeGoal(id, title){
//    abstract val compositeGoalId: Int
//    abstract fun <T> calculateSumProgress(): Double
//}
interface Internable<in T: SingleGoal>:Goal {
    val compositeGoalId: Int
//    fun isGoalOf(goal: T): Boolean
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
    fun calculateSumProgress(): Double{
        return ((completedJobs*100)/jobsQuantity).toDouble()
    }
}