package io.sokolvault.wayofturtles

import android.arch.persistence.room.TypeConverter


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
}