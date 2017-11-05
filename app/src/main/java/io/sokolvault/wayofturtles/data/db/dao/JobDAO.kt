package io.sokolvault.wayofturtles.data.db.dao

import android.arch.persistence.room.*
import io.sokolvault.wayofturtles.utils.AppTypeConverters
import io.sokolvault.wayofturtles.data.db.model.JobEntity

@Dao
@TypeConverters(AppTypeConverters::class)
interface JobDAO {
    @Query("SELECT * FROM jobs_sub_goals")
    fun getAll(): List<JobEntity>

    @Insert
    fun insertJobSubGoal(jobEntitySubGoal: JobEntity)

    //    @Query("SELECT * FROM sub_Goals WHERE id = :id")
//    fun getBigGoalById(id: Int): SubGoal

    @Update
    fun updateJobSubGoal(jobEntitySubGoal: JobEntity)

    @Delete
    fun delete(jobEntitySubGoal: JobEntity)
}