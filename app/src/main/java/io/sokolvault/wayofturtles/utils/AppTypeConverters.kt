package io.sokolvault.wayofturtles.utils

import android.arch.persistence.room.TypeConverter

import io.sokolvault.wayofturtles.model.xtensions.GoalCategory


open class AppTypeConverters {

    @TypeConverter
    fun fromEnumToString(enum: Enum<GoalCategory>?): String? = when {
        enum != null -> enum.name
                else -> GoalCategory.NONE.name
    }

    @TypeConverter
    fun fromStringToEnum(string: String?): Enum<GoalCategory>? =
            string?.let { GoalCategory.valueOf(it) }

//    fun fromBigGoalToEntity(bigGoal: BigGoal): BigGoalEntity{
//        var bigGoalEntity: BigGoalEntity
//
//        return BigGoalEntity(bigGoal.id, bigGoal.title, bigGoal.description)
//    }
}