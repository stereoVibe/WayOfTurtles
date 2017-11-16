package io.sokolvault.wayofturtles.model.xtensions


sealed class CountUnit(val step: Int) {
    abstract val countUnit: String

    class Piece(step: Int) : CountUnit(step){
        override val countUnit: String = "шт."
    }
    class Times(step: Int) : CountUnit(step){
        override val countUnit: String = "раз"
    }
//    class Session(step: Int) : CountUnit(step)
    class Money(step: Int, currencyChar: Char) : CountUnit(step){
        override val countUnit: String = currencyChar.toString()
    }
}