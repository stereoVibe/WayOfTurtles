//package io.sokolvault.wayofturtles.domain.presence
//
//import io.sokolvault.wayofturtles.domain.model.PresenceUseCase
//import io.sokolvault.wayofturtles.model.Internable
//import io.sokolvault.wayofturtles.model.SingleGoal
//
//
//class PresenceSubGoalInteractor:PresenceUseCase<Internable<SingleGoal>> {
//    override fun createGoal(goal: Internable<SingleGoal>) {
//        if (goal.compositeGoalId > 0)
//        return this.createGoal(goal)
//    }
//
//    override fun deleteGoal(goal: Internable<SingleGoal>) {
//        return this.deleteGoal(goal)
//    }
//}