package io.sokolvault.wayofturtles.carriers

import io.sokolvault.wayofturtles.model.xtensions.Compoundable
import io.sokolvault.wayofturtles.model.CompositeGoal
import io.sokolvault.wayofturtles.model.SingleGoal
import io.sokolvault.wayofturtles.model.Internable


data class DataCompositeGoal(override var id: Int,
                             override var title: String)
    : CompositeGoal(), Compoundable<SingleGoal> {
    override var description: String = ""
    override var subGoals: ArrayList<Internable<SingleGoal>> = ArrayList()

    override fun addSubGoalToList(internable: Internable<SingleGoal>){
        if (internable.compositeGoalId == id){ this.subGoals.add(internable.id, internable) }
    }

    override fun addAllSubGoals(internables: List<Internable<SingleGoal>>){ this.subGoals.addAll(internables) }
}