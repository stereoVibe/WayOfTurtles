package io.sokolvault.wayofturtles.model

/* An interface responsible for composite goals, like BigGoal class.
*  It's kind of overengineering :) */

interface CompositeGoal {
    var subGoalsList: ArrayList<SubGoal<in CompositeGoal>>
}