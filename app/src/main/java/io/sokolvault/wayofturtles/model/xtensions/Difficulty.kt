package io.sokolvault.wayofturtles.model.xtensions

interface Difficulty {
    val grade: Grade
    val modifier: Double

    val modProgress: (Double) -> Double
//    sealed class Grade(val modifier: Double) {
//        object EASY : Grade(0.8)
//        object NORMAL : Grade(1.0)
//        object HARD : Grade(1.2)
//    }

    enum class Grade(val gradeModifier: Double){
        EASY(0.8),
        NORMAL(1.0),
        HARD(1.2)
    }
}

//val Difficulty.mod: (Difficulty.Grade) -> Double
//    get() = { it.modifier }