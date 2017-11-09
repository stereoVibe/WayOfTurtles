package io.sokolvault.wayofturtles.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.TypeConverters
import io.sokolvault.wayofturtles.model.xtensions.Compoundable
import io.sokolvault.wayofturtles.model.xtensions.GoalCategory
import io.sokolvault.wayofturtles.utils.AppTypeConverters
import io.sokolvault.wayofturtles.utils.Constants

/* Core class for all goals in the app */

interface Goal{
    var title: String
    var description: String
    var progress: Double
    var isComplete: Boolean
    var goalCategory: Enum<GoalCategory>
}

/* Wrapper */
abstract class BaseGoal: Goal{
    abstract var id: Int
    abstract override var title: String
    abstract override var description: String
    override var progress: Double = 0.0
    @ColumnInfo(name = Constants.COMPLETE_COLUMN_NAME)
    override var isComplete: Boolean = false
    @TypeConverters(AppTypeConverters::class)
    @ColumnInfo(name = Constants.GOAL_CATEGORY_COLUMN_NAME)
    override var goalCategory: Enum<GoalCategory> = GoalCategory.NONE
}

abstract class SingleGoal
    :BaseGoal(){
}

abstract class CompositeGoal
    :BaseGoal(), Compoundable<SingleGoal>{

    abstract var subGoals: ArrayList<Internable<SingleGoal>>

    fun calculateSumProgress(): Int {
        return if (subGoals.isNotEmpty()) {
            progress.toInt()
            for (subProgress in subGoals) {
                progress += subProgress.progress.toInt()
            }
            progress.toInt()
        } else 0
    }
}

//@TypeConverters(AppTypeConverters::class)
//abstract class Goal {
//    abstract var id: Int
//    abstract var title: String
//    var description: String = ""
//    //  Start values for this fields in any Goal class
//    var progress = 0.0
//    var isComplete = false
//    @TypeConverters(AppTypeConverters::class) var goalCategory: Enum<GoalCategory> = GoalCategory.NONE
//}
