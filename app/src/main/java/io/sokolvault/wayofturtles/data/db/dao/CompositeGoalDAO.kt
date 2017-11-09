package io.sokolvault.wayofturtles.data.db.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import android.arch.persistence.room.TypeConverters
import android.arch.persistence.room.Update

import io.sokolvault.wayofturtles.data.db.model.CompositeGoalRoom
import io.sokolvault.wayofturtles.utils.AppTypeConverters

@Dao
@TypeConverters(AppTypeConverters::class)
interface CompositeGoalDAO {

    @get:Query("SELECT * FROM composite_goals")
    val all: List<CompositeGoalRoom>

    @Query("SELECT * FROM composite_goals WHERE id = :id")
    fun getBigGoalById(id: Int): LiveData<CompositeGoalRoom>

    @Insert(onConflict = REPLACE)
    fun insertBigGoal(bigGoal: CompositeGoalRoom)

    @Update
    fun updateBigGoal(bigGoalEntity: CompositeGoalRoom)

    @Delete
    fun delete(bigGoal: CompositeGoalRoom)

}

