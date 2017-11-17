package io.sokolvault.wayofturtles.data.db.dao

import android.arch.persistence.room.*
import io.sokolvault.wayofturtles.utils.AppTypeConverters
import io.sokolvault.wayofturtles.data.db.model.MonotypeSubGoalRoom

@Dao
@TypeConverters(AppTypeConverters::class)
interface TaskSubGoalDAO {
    @Query("SELECT * FROM task_subgoals")
    fun getAll(): List<MonotypeSubGoalRoom>

    @Insert
    fun insertTaskSubGoal(monotypeSubGoalRoom: MonotypeSubGoalRoom)

    //    @Query("SELECT * FROM sub_Goals WHERE id = :id")
//    fun getBigGoalById(id: Int): SubGoal

    @Update
    fun updateTaskSubGoal(monotypeSubGoalRoom: MonotypeSubGoalRoom)

    @Delete
    fun delete(monotypeSubGoalRoom: MonotypeSubGoalRoom)
}
