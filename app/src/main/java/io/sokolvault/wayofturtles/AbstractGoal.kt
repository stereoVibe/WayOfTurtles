package io.sokolvault.wayofturtles

abstract class AbstractGoal(argTitle: String) {
    abstract var id: Int?
    var title = argTitle
    var description: String? = null
    var mProgress = 0.0
    var isComplete = false


//    open fun getTitle(): String {
//        return title
//    }
//    abstract fun getDescription(): String

//    abstract fun getProgress(): Double
//
//    abstract fun isComplete(): Boolean
//
//    abstract fun setProgress(progress: Double)

}