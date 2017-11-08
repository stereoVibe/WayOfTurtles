package io.sokolvault.wayofturtles.data.db.model

import android.arch.persistence.room.*
import android.arch.persistence.room.ForeignKey.CASCADE
import io.sokolvault.wayofturtles.utils.AppTypeConverters
import io.sokolvault.wayofturtles.model.xtensions.GoalCategory
import io.sokolvault.wayofturtles.model.base.Goal
import io.sokolvault.wayofturtles.model.complex.SubGoal

@Entity(tableName = "tasks_sub_goals",
        indices = arrayOf(Index(value = "composite_goal_id", unique = true)),
        foreignKeys = arrayOf(ForeignKey(entity = BigGoalEntity::class,
                parentColumns = arrayOf("big_goal_id"),
                childColumns = arrayOf("composite_goal_id"),
                onDelete = CASCADE,
                onUpdate = CASCADE, deferred = true)))
@TypeConverters(AppTypeConverters::class)
class TaskEntity(title: String, compositeGoalID: Int) : Goal(title), SubGoal {

    @PrimaryKey(autoGenerate = true)
    override var id: Int = -1

    @ColumnInfo(name = "goal_category")
    var categoryEnum: Enum<GoalCategory> = GoalCategory.NONE

    @ColumnInfo(name = "composite_goal_id")
    override var compositeGoalId: Int = compositeGoalID

}