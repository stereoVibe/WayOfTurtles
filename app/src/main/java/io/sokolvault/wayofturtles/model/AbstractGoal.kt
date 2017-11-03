package io.sokolvault.wayofturtles.model

/* Core class for all goals in the app */

abstract class AbstractGoal(argTitle: String) {
    abstract var id: Int
    var title = argTitle
    var description: String? = null

//  Start values for this fields in any Goal class
    var mProgress = 0.0
    var isComplete = false

//    fun setId(id: Int){
//        this.id = id
//    }
}