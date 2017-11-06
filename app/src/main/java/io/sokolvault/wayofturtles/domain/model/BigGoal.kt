package io.sokolvault.wayofturtles.domain.model


class BigGoal(override var id: Int,
              title: String) : Goal(title) {

    var subGoals: Set<SubGoal> = LinkedHashSet()

//    fun getSelfSubGoals(): Set<SubGoal> {
//        return subGoals
//    }
}