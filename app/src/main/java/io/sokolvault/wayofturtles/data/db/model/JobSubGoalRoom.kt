package io.sokolvault.wayofturtles.data.db.model

import android.arch.persistence.room.*
import android.arch.persistence.room.ForeignKey.CASCADE
import io.sokolvault.wayofturtles.model.JobGoal
import io.sokolvault.wayofturtles.model.xtensions.GoalCategory
import io.sokolvault.wayofturtles.model.xtensions.Internable
import io.sokolvault.wayofturtles.model.xtensions.StepUnit
import io.sokolvault.wayofturtles.utils.AppTypeConverters
import io.sokolvault.wayofturtles.utils.Constants
import io.sokolvault.wayofturtles.utils.Constants.HYBRID_GOAL_ID_COLUMN_NAME
import io.sokolvault.wayofturtles.utils.Constants.CYCLES_QUANTITY_COLUMN_NAME
import io.sokolvault.wayofturtles.utils.Constants.FOREIGN_KEY_HYBRID_GOAL_ID_COLUMN
import io.sokolvault.wayofturtles.utils.Constants.JOB_SUBGOALS_TABLE_NAME

@Entity(tableName = JOB_SUBGOALS_TABLE_NAME,
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
class JobSubGoalRoom(override var title: String,
                     @ColumnInfo(name = CYCLES_QUANTITY_COLUMN_NAME)
                     override var cycleQuantity: Int,
                     @ColumnInfo(name = FOREIGN_KEY_HYBRID_GOAL_ID_COLUMN)
                     override var hybridGoalId: Int)
    : JobGoal(), Internable<JobGoal> {

    @PrimaryKey(autoGenerate = true)
    override var id: Int = 0
    override var description: String = ""
    override var progress: Double = calculateProgress()
    @ColumnInfo(name = Constants.COMPLETED_CYCLES_COLUMN_NAME)
    override var completedCycles: Int = 0
    @ColumnInfo(name = Constants.COMPLETE_COLUMN_NAME)
    override var isComplete: Boolean = false
    @ColumnInfo(name = Constants.GOAL_CATEGORY_COLUMN_NAME)
    override var goalCategory: Enum<GoalCategory> = GoalCategory.NONE
    @Ignore
    override val counts: StepUnit = StepUnit.Piece(0)

    override fun calculateProgress(): Double = ((completedCycles * 100)/cycleQuantity).toDouble()

}