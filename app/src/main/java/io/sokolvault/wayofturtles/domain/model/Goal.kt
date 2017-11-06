package io.sokolvault.wayofturtles.domain.model

import android.arch.persistence.room.TypeConverters
import io.sokolvault.wayofturtles.utils.AppTypeConverters

/* Core class for all goals in the app */
@TypeConverters(AppTypeConverters::class)
abstract class Goal(var title: String) {

    abstract var id: Int
    var description: String = ""

//  Start values for this fields in any Goal class
    var mProgress = 0.0
    var isComplete = false

    @TypeConverters(AppTypeConverters::class)
    var mGoalCategory: Enum<GoalCategory> = GoalCategory.NONE
}