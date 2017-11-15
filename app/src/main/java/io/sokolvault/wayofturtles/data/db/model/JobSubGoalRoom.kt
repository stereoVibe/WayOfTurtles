package io.sokolvault.wayofturtles.data.db.model

import android.arch.persistence.room.*
import android.arch.persistence.room.ForeignKey.CASCADE
import io.sokolvault.wayofturtles.model.Internable
import io.sokolvault.wayofturtles.model.JobGoal
import io.sokolvault.wayofturtles.model.xtensions.GoalCategory
import io.sokolvault.wayofturtles.utils.AppTypeConverters
import io.sokolvault.wayofturtles.utils.Constants
import io.sokolvault.wayofturtles.utils.Constants.Companion.COMPOSITE_GOAL_ID_COLUMN_NAME
import io.sokolvault.wayofturtles.utils.Constants.Companion.FOREIGN_KEY_COMPOSITE_GOAL_ID_COLUMN
import io.sokolvault.wayofturtles.utils.Constants.Companion.JOBS_QUANTITY_COLUMN_NAME
import io.sokolvault.wayofturtles.utils.Constants.Companion.JOB_SUBGOALS_TABLE_NAME

@Entity(tableName = JOB_SUBGOALS_TABLE_NAME,
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
class JobSubGoalRoom(@PrimaryKey(autoGenerate = true)
                     override var id: Int,
                     override var title: String,
                     @ColumnInfo(name = JOBS_QUANTITY_COLUMN_NAME)
                     override var jobsQuantity: Int,
                     @ColumnInfo(name = FOREIGN_KEY_COMPOSITE_GOAL_ID_COLUMN)
                     override var compositeGoalId: Int)
    :JobGoal(), Internable<JobGoal> {

    override var description: String = ""

}