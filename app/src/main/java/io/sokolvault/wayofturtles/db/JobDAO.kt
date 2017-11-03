package io.sokolvault.wayofturtles.db

import android.arch.persistence.room.*
import io.sokolvault.wayofturtles.db.model.JobEntity

@Dao
interface JobDAO {
    @Query("SELECT * FROM sub_goals")
    fun getAll(): List<JobEntity>

    @Insert
    fun insertJobSubGoal(jobEntitySubGoal: JobEntity)

    //    @Query("SELECT * FROM sub_Goals WHERE id = :id")
//    fun getBigGoalById(id: Int): SubGoal

    @Update
    fun updateJob(jobEntitySubGoal: JobEntity)

    @Delete
    fun delete(jobEntitySubGoal: JobEntity)
}