package io.sokolvault.wayofturtles.dto

import io.sokolvault.wayofturtles.model.xtensions.Compoundable
import io.sokolvault.wayofturtles.model.complex.CompositeGoal
import io.sokolvault.wayofturtles.model.straight.SingleGoal
import io.sokolvault.wayofturtles.model.complex.SubGoal


data class DataBigGoal(override var id: Int,
                       override var title: String): CompositeGoal(id, title), Compoundable<SingleGoal> {

    override var subGoals: LinkedHashMap<Int, SubGoal<SingleGoal>> = LinkedHashMap()

    override fun addSubGoalToMap(subGoal: SubGoal<SingleGoal>){
        if (subGoal.compositeGoalId == id){
            subGoals.put(subGoal.id, subGoal)
        }
    }

    override fun addAllSubGoals(subGoals: Set<SubGoal<SingleGoal>>){
        this.subGoals.map { subGoals.associateBy ({it.id}, {it}) }
    }

    override fun calculateSumProgress(): Int {
        return if (subGoals.isNotEmpty()) {
            progress.toInt()
            for (subProgress in subGoals.values) {
                progress += subProgress.progress.toInt()
            }
            progress.toInt()
        } else 0
    }

//    public inline fun <K, V> Map<out K, V>.forEach(action: (Map.Entry<K, V>) -> Unit): Unit {
//        for (element in this) action(element)
//    }
}