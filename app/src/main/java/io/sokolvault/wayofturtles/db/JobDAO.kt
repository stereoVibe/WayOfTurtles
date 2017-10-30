package io.sokolvault.wayofturtles.db

import android.arch.persistence.room.*
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import io.sokolvault.wayofturtles.model.Job

@Dao
interface JobDAO {
    @Query("SELECT * FROM sub_goals")
    fun getAll(): List<Job>

    @Insert(onConflict = REPLACE)
    fun insertJobSubGoal(jobSubGoal: Job)

    //    @Query("SELECT * FROM sub_Goals WHERE id = :id")
//    fun getBigGoalById(id: Int): SubGoal

    @Delete
    fun delete(jobSubGoal: Job)
}