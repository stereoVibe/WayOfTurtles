package io.sokolvault.wayofturtles.db

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import io.sokolvault.wayofturtles.model.BigGoal
import io.sokolvault.wayofturtles.model.SubGoal

@Dao
interface SubGoalOfBigGoalDAO {
    @Query("SELECT * FROM sub_Goals")
    fun getAll(): List<SubGoal<BigGoal>>

//    @Query("SELECT * FROM sub_Goals WHERE id = :id")
//    fun getBigGoalById(id: Int): SubGoal

    @Insert
    fun insertBigGoal(subGoal: SubGoal<BigGoal>)

    @Delete
    fun delete(subGoal: SubGoal<BigGoal>)
}