package io.sokolvault.wayofturtles.data.db.model

import android.arch.persistence.room.*
import android.arch.persistence.room.ForeignKey.CASCADE
import io.sokolvault.wayofturtles.model.MonotypeGoal
import io.sokolvault.wayofturtles.utils.AppTypeConverters
import io.sokolvault.wayofturtles.model.xtensions.Internable
import io.sokolvault.wayofturtles.model.xtensions.GoalCategory
import io.sokolvault.wayofturtles.utils.Constants
import io.sokolvault.wayofturtles.utils.Constants.HYBRID_GOAL_ID_COLUMN_NAME
import io.sokolvault.wayofturtles.utils.Constants.FOREIGN_KEY_HYBRID_GOAL_ID_COLUMN
import io.sokolvault.wayofturtles.utils.Constants.TASK_SUBGOALS_TABLE_NAME

@Entity(tableName = TASK_SUBGOALS_TABLE_NAME,
        indices = arrayOf(Index(
                value = FOREIGN_KEY_HYBRID_GOAL_ID_COLUMN,
                unique = true)),
        foreignKeys = arrayOf(ForeignKey(
                entity = HybridGoalRoom::class,
                parentColumns = arrayOf(HYBRID_GOAL_ID_COLUMN_NAME),
                childColumns = arrayOf(FOREIGN_KEY_HYBRID_GOAL_ID_COLUMN),
                onDelete = CASCADE,
                onUpdate = CASCADE,
                deferred = true)))
@TypeConverters(AppTypeConverters::class)
class MonotypeSubGoalRoom(override var title: String,
                          @ColumnInfo(name = FOREIGN_KEY_HYBRID_GOAL_ID_COLUMN)
                      override val hybridGoalId: Int)
    : MonotypeGoal(), Internable<MonotypeGoal> {

    @PrimaryKey(autoGenerate = true)
    override var id: Int = 0
    override var description: String = ""
    @ColumnInfo(name = Constants.COMPLETE_COLUMN_NAME)
    override var isComplete: Boolean = false
    @ColumnInfo(name = Constants.GOAL_CATEGORY_COLUMN_NAME)
    override var goalCategory: Enum<GoalCategory> = GoalCategory.NONE
}