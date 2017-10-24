package io.sokolvault.wayofturtles.db

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query

@Dao
interface BigGoalAllSubGoalsDAO {
    @Query("SELECT * FROM big_Goals")
    fun loadAllSubGoals(): List<BigGoalAndAllSubGoals>
}