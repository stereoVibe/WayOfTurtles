package io.sokolvault.wayofturtles.data.db.model

import android.arch.persistence.room.*
import io.sokolvault.wayofturtles.model.HybridGoal
import io.sokolvault.wayofturtles.model.MonotypeGoal
import io.sokolvault.wayofturtles.model.xtensions.GoalCategory
import io.sokolvault.wayofturtles.model.xtensions.Internable
import io.sokolvault.wayofturtles.utils.AppTypeConverters
import io.sokolvault.wayofturtles.utils.Constants
import io.sokolvault.wayofturtles.utils.Constants.HYBRID_GOALS_TABLE_NAME
import io.sokolvault.wayofturtles.utils.Constants.HYBRID_GOAL_ID_COLUMN_NAME
import kotlin.collections.ArrayList

/* CompositeGoalRoom class is responsible for holding all Sub Goals, like a container.
*  Its function to fetching sub goals to appropriate Big Goal and holds overall progress,
*  according to complete/incomplete/inProgress status of sub goals */

@Entity(tableName = HYBRID_GOALS_TABLE_NAME,
        indices = arrayOf(Index(value = HYBRID_GOAL_ID_COLUMN_NAME,
                                unique = true)))
@TypeConverters(AppTypeConverters::class)
data class HybridGoalRoom constructor(private var goalTitle: String,
                                      override @Ignore var subGoals: ArrayList<Internable<MonotypeGoal>> =
                                      ArrayList() ) : HybridGoal() {

    @Ignore
    constructor(title: String, description: String) : this(title) {
        this.description = description
    }

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = HYBRID_GOAL_ID_COLUMN_NAME)
    override var id: Int = 0
    override var title: String = goalTitle
    override var description: String = ""
    @ColumnInfo(name = Constants.COMPLETE_COLUMN_NAME)
    override var isComplete: Boolean = false
    @TypeConverters(AppTypeConverters::class)
    @ColumnInfo(name = Constants.GOAL_CATEGORY_COLUMN_NAME)
    override var goalCategory: Enum<GoalCategory> = GoalCategory.NONE
    override var progress: Double = calculateProgress()
    @ColumnInfo(name = Constants.ALL_SUBGOALS_QUANTITY)
    override var allSubGoalsQuantity: Int = countAllSubGoals(this.subGoals)
    @ColumnInfo(name = Constants.COMPLETD_SUBGOALS)
    override var completedSubGoalsQuantity: Int = countCompletedSubGoals(this.subGoals)

    override fun addSubGoalToList(internable: Internable<MonotypeGoal>) { subGoals.add(internable) }
    override fun addAllSubGoals(internables: List<Internable<MonotypeGoal>>) { subGoals.addAll(internables) }

    override fun calculateProgress(): Double = 0.0

}