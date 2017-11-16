package io.sokolvault.wayofturtles.data.db.model

import android.arch.persistence.room.*
import io.sokolvault.wayofturtles.model.CompositeGoal
import io.sokolvault.wayofturtles.model.Internable
import io.sokolvault.wayofturtles.model.SingleGoal
import io.sokolvault.wayofturtles.model.xtensions.GoalCategory
import io.sokolvault.wayofturtles.utils.AppTypeConverters
import io.sokolvault.wayofturtles.utils.Constants
import io.sokolvault.wayofturtles.utils.Constants.COMPOSITE_GOALS_TABLE_NAME
import io.sokolvault.wayofturtles.utils.Constants.COMPOSITE_GOAL_ID_COLUMN_NAME
import java.util.*

/* CompositeGoalRoom class is responsible for holding all Sub Goals, like a container.
*  Its function to fetching sub goals to appropriate Big Goal and holds overall progress,
*  according to complete/incomplete/inProgress status of sub goals */

@Entity(tableName = COMPOSITE_GOALS_TABLE_NAME,
        indices = arrayOf(Index(value = COMPOSITE_GOAL_ID_COLUMN_NAME,
                                unique = true)))
@TypeConverters(AppTypeConverters::class)
data class CompositeGoalRoom constructor(private var goalTitle: String)
    : CompositeGoal() {

    constructor(title: String, description: String) : this(title) {
        this.description = description
    }

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = COMPOSITE_GOAL_ID_COLUMN_NAME)
    override var id: Int = 0
    override var title: String = goalTitle
    override var description: String = ""
    @ColumnInfo(name = Constants.COMPLETE_COLUMN_NAME)
    override var isComplete: Boolean = false
    @TypeConverters(AppTypeConverters::class)
    @ColumnInfo(name = Constants.GOAL_CATEGORY_COLUMN_NAME)
    override var goalCategory: Enum<GoalCategory> = GoalCategory.NONE

    @Ignore
//    @ColumnInfo(name = SUBGOALS_COLUMN_NAME)
    override var subGoals: ArrayList<Internable<SingleGoal>> = ArrayList()

    override fun addSubGoalToList(internable: Internable<SingleGoal>) { subGoals.add(internable) }
    override fun addAllSubGoals(internables: List<Internable<SingleGoal>>) { subGoals.addAll(internables) }
}