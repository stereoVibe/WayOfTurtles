package io.sokolvault.wayofturtles.db

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Relation
import io.sokolvault.wayofturtles.model.BigGoal
import io.sokolvault.wayofturtles.model.SubGoal

class BigGoalAndAllSubGoals {
    @Embedded
    lateinit var bigGoal: BigGoal
    @Relation(parentColumn = "id", entityColumn = "composite_goal_ID", entity = SubGoal::class)
    lateinit var subGoals: List<SubGoal<BigGoal>>
}