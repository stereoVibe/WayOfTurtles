package io.sokolvault.wayofturtles.utils

import android.arch.persistence.room.TypeConverter

import io.sokolvault.wayofturtles.model.xtensions.GoalCategory
import io.sokolvault.wayofturtles.model.xtensions.StepUnit


open class AppTypeConverters {

    @TypeConverter
    fun fromEnumToString(enum: Enum<GoalCategory>?): String? = when {
        enum != null -> enum.name
        else -> GoalCategory.NONE.name
    }

    @TypeConverter
    fun fromStringToEnum(string: String?): Enum<GoalCategory>? =
            string?.let { GoalCategory.valueOf(it) }

    @TypeConverter
    fun fromStepUnitToString(stepUnit: StepUnit): String {
        val units: (StepUnit) -> String = { u -> "${u.step} ${u.unitType}"}
        return when (stepUnit) {
            is StepUnit.Money -> units(stepUnit)
            is StepUnit.Piece -> units(stepUnit)
            is StepUnit.Time -> units(stepUnit)
            is StepUnit.Times -> units(stepUnit)
        }
    }
}
