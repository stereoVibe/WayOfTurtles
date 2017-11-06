package io.sokolvault.wayofturtles.utils

import android.arch.persistence.room.TypeConverter
import io.sokolvault.wayofturtles.data.db.model.BigGoalEntity
import io.sokolvault.wayofturtles.domain.model.BigGoal

import io.sokolvault.wayofturtles.domain.model.Goal
import io.sokolvault.wayofturtles.domain.model.GoalCategory


open class AppTypeConverters {

    @TypeConverter
    fun fromEnumToString(enum: Enum<GoalCategory>?): String? {
        if (enum != null) {
            return enum.name
        }
        return GoalCategory.NONE.name
    }

    @TypeConverter
    fun fromStringToEnum(string: String?): Enum<GoalCategory>?{
        return string?.let { GoalCategory.valueOf(it) }
    }

//    fun fromBigGoalToEntity(bigGoal: BigGoal): BigGoalEntity{
//        var bigGoalEntity: BigGoalEntity
//
//        return BigGoalEntity(bigGoal.id, bigGoal.title, bigGoal.description)
//    }
}