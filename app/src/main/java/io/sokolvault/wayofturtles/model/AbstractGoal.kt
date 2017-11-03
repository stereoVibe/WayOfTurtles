package io.sokolvault.wayofturtles.model

import android.arch.persistence.room.TypeConverters
import io.sokolvault.wayofturtles.AppTypeConverters
import io.sokolvault.wayofturtles.GoalCategory

/* Core class for all goals in the app */
@TypeConverters(AppTypeConverters::class)
abstract class AbstractGoal(var title: String) {
    abstract val id: Int
    var description: String = ""
//  Start values for this fields in any Goal class
    var mProgress = 0.0
    var isComplete = false
    @TypeConverters(AppTypeConverters::class)
    var mGoalCategory: Enum<GoalCategory> = GoalCategory.NONE

//    fun setId(id: Int){
//        this.id = id
//    }
}