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

abstract class BaseGoal: Goal{
    abstract var id: Int
    abstract override var title: String
    abstract override var description: String
    override var progress: Double = 0.0
    override var isComplete: Boolean = false
    override var goalCategory: Enum<GoalCategory> = GoalCategory.NONE
}

abstract class SingleGoal
    :BaseGoal()

abstract class CompositeGoal
    :BaseGoal(), Compoundable<SingleGoal>{
    abstract var subGoals: ArrayList<Internable<SingleGoal>>

    fun calculateSumProgress(): Int {
        return if (subGoals.isNotEmpty()) {
            for (subProgress in subGoals) {
                progress += subProgress.progress.toInt()
            }
            progress.toInt()
        } else 0
    }
}
