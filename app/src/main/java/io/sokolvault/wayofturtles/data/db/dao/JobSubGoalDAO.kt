package io.sokolvault.wayofturtles.data.db.dao

import android.arch.persistence.room.*
import io.sokolvault.wayofturtles.utils.AppTypeConverters
import io.sokolvault.wayofturtles.data.db.model.JobSubGoalRoom

@Dao
@TypeConverters(AppTypeConverters::class)
interface JobSubGoalDAO {

    @Query("SELECT * FROM job_subgoals")
    fun getAll(): List<JobSubGoalRoom>

    @Insert
    fun insertJobSubGoal(jobGoalRoomSubSubGoal: JobSubGoalRoom)

    //    @Query("SELECT * FROM sub_Goals WHERE id = :id")
//    fun getBigGoalById(id: Int): SubGoal

    @Update
    fun updateJobSubGoal(jobGoalRoomSubSubGoal: JobSubGoalRoom)

    @Delete
    fun delete(jobGoalRoomSubSubGoal: JobSubGoalRoom)
}