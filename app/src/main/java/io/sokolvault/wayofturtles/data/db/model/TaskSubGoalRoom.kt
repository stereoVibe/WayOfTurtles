package io.sokolvault.wayofturtles.data.db.model

import android.arch.persistence.room.*
import android.arch.persistence.room.ForeignKey.CASCADE
import io.sokolvault.wayofturtles.utils.AppTypeConverters
import io.sokolvault.wayofturtles.model.Internable
import io.sokolvault.wayofturtles.model.TaskGoal
import io.sokolvault.wayofturtles.model.xtensions.GoalCategory
import io.sokolvault.wayofturtles.utils.Constants
import io.sokolvault.wayofturtles.utils.Constants.Companion.COMPOSITE_GOAL_ID_COLUMN_NAME
import io.sokolvault.wayofturtles.utils.Constants.Companion.FOREIGN_KEY_COMPOSITE_GOAL_ID_COLUMN
import io.sokolvault.wayofturtles.utils.Constants.Companion.TASK_SUBGOALS_TABLE_NAME

@Entity(tableName = TASK_SUBGOALS_TABLE_NAME,
        indices = arrayOf(Index(
                value = FOREIGN_KEY_COMPOSITE_GOAL_ID_COLUMN,
                unique = true)),
        foreignKeys = arrayOf(ForeignKey(
                entity = CompositeGoalRoom::class,
                parentColumns = arrayOf(COMPOSITE_GOAL_ID_COLUMN_NAME),
                childColumns = arrayOf(FOREIGN_KEY_COMPOSITE_GOAL_ID_COLUMN),
                onDelete = CASCADE,
                onUpdate = CASCADE,
                deferred = true)))
@TypeConverters(AppTypeConverters::class)
class TaskSubGoalRoom(@PrimaryKey(autoGenerate = true)
                      override var id: Int,
                      override var title: String,
                      @ColumnInfo(name = FOREIGN_KEY_COMPOSITE_GOAL_ID_COLUMN)
                      override val compositeGoalId: Int)
    :TaskGoal(), Internable<TaskGoal> {

    override var description: String = ""
}