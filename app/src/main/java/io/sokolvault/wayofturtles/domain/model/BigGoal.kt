package io.sokolvault.wayofturtles.domain.model


class BigGoal(override val id: Int,
              title: String) : Goal(title) {

    private var subGoals: Set<SubGoal> = LinkedHashSet()

    fun getSelfSubGoals(): Set<SubGoal> {
        return subGoals
    }
}