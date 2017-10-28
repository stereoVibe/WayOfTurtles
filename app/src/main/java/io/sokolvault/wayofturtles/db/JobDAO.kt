package io.sokolvault.wayofturtles.db

import android.arch.persistence.room.*
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import io.sokolvault.wayofturtles.model.BigGoal
import io.sokolvault.wayofturtles.model.Job
import io.sokolvault.wayofturtles.model.SubGoal

@Dao
interface JobDAO {
    @Query("SELECT * FROM sub_goals")
    fun getAll(): List<Job>

    @Insert(onConflict = REPLACE)
    fun insertSubGoal(jobSubGoal: Job)

    //    @Query("SELECT * FROM sub_Goals WHERE id = :id")
//    fun getBigGoalById(id: Int): SubGoal

    @Delete
    fun delete(jobSubGoal: Job)
}