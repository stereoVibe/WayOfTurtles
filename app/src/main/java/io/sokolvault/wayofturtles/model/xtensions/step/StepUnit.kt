package io.sokolvault.wayofturtles.model.xtensions.step


sealed class StepUnit(val step: Int) {

    abstract val unitType: String

    class Piece(step: Int) : StepUnit(step){
        override val unitType: String = "шт."
    }
    class Time(step: Int): StepUnit(step){
        override val unitType: String = StepType.TIME.res.toString()
    }
    class Times(step: Int) : StepUnit(step){
        override val unitType: String = StepType.TIMES.res.toString()
    }
//    class Session(step: Int) : CountUnit(step)
    class Money(step: Int, currencyChar: Char) : StepUnit(step){
        override val unitType: String = currencyChar.toString()
    }

}