//package io.sokolvault.wayofturtles.carriers
//
//import io.sokolvault.wayofturtles.model.xtensions.Compoundable
//import io.sokolvault.wayofturtles.model.HybridGoal
//import io.sokolvault.wayofturtles.model.MonotypeGoal
//import io.sokolvault.wayofturtles.model.xtensions.Internable
//
//
//data class DataCompositeGoal(override var title: String)
//    : HybridGoal(), Compoundable<MonotypeGoal> {
//    override var id: Int = 0
//    override var description: String = ""
//    override var subGoals: ArrayList<Internable<MonotypeGoal>> = ArrayList()
//
//    override fun addSubGoalToList(internable: Internable<MonotypeGoal>){
//        if (internable.hybridGoalId == id){ this.subGoals.add(internable) }
//    }
//
//    override fun addAllSubGoals(internables: List<Internable<MonotypeGoal>>){ this.subGoals.addAll(internables) }
//}