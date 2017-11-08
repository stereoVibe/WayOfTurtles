package io.sokolvault.wayofturtles.model.base

import android.arch.persistence.room.TypeConverters
import io.sokolvault.wayofturtles.model.xtensions.GoalCategory
import io.sokolvault.wayofturtles.utils.AppTypeConverters

/* Core class for all goals in the app */
@TypeConverters(AppTypeConverters::class)
abstract class Goal {

    abstract var id: Int
    abstract var title: String
    var description: String = ""

//  Start values for this fields in any Goal class
    var progress = 0.0
    var isComplete = false

    @TypeConverters(AppTypeConverters::class)
    var goalCategory: Enum<GoalCategory> = GoalCategory.NONE
}