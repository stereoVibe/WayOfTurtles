package io.sokolvault.wayofturtles

/**
 * Created by Vault on 23/10/2017.
 */
interface CompositeGoal<T> {
    var subGoalsList: ArrayList<SubGoal<T>>
}