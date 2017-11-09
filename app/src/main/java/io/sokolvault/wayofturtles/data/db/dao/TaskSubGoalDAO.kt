package io.sokolvault.wayofturtles.data.db.dao

import android.arch.persistence.room.*
import io.sokolvault.wayofturtles.utils.AppTypeConverters
import io.sokolvault.wayofturtles.data.db.model.TaskSubGoalRoom

@Dao
@TypeConverters(AppTypeConverters::class)
interface TaskSubGoalDAO {
    @Query("SELECT * FROM task_subgoals")
    fun getAll(): List<TaskSubGoalRoom>

    @Insert
    fun insertTaskSubGoal(taskSubGoalRoom: TaskSubGoalRoom)

    //    @Query("SELECT * FROM sub_Goals WHERE id = :id")
//    fun getBigGoalById(id: Int): SubGoal

    @Update
    fun updateTaskSubGoal(taskSubGoalRoom: TaskSubGoalRoom)

    @Delete
    fun delete(taskSubGoalRoom: TaskSubGoalRoom)
}
