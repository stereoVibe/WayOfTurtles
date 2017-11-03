package io.sokolvault.wayofturtles.db

import android.arch.persistence.room.*
import io.sokolvault.wayofturtles.AppTypeConverters
import io.sokolvault.wayofturtles.db.model.JobEntity
import io.sokolvault.wayofturtles.db.model.TaskEntity

@Dao
@TypeConverters(AppTypeConverters::class)
interface TaskDAO {
    @Query("SELECT * FROM tasks_sub_goals")
    fun getAll(): List<TaskEntity>

    @Insert
    fun insertTaskSubGoal(taskEntity: TaskEntity)

    //    @Query("SELECT * FROM sub_Goals WHERE id = :id")
//    fun getBigGoalById(id: Int): SubGoal

    @Update
    fun updateTaskSubGoal(taskEntity: TaskEntity)

    @Delete
    fun delete(taskEntity: TaskEntity)
}
