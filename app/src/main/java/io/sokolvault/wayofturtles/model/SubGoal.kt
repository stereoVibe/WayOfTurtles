package io.sokolvault.wayofturtles.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey

//@ForeignKey(entity = BigGoal::class, parentColumns = "id", childColumns = "composite_goal_ID")

@Entity(tableName = "sub_Goals", foreignKeys = arrayOf(ForeignKey(
        entity = BigGoal::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("composite_goal_ID"))))
interface SubGoal<in CompositeGoal> {
    fun fetch(goal: CompositeGoal)
}