package io.sokolvault.wayofturtles.domain.presence

import io.sokolvault.wayofturtles.domain.model.PresenceUseCase
import io.sokolvault.wayofturtles.model.CompositeGoal
import io.sokolvault.wayofturtles.model.Internable
import io.sokolvault.wayofturtles.model.SingleGoal
import io.sokolvault.wayofturtles.repositories.presence.PresenceRepositoryData

class PresenceInteractor {

    class UseCaseForCompositeGoal(private val repository: PresenceRepositoryData) : PresenceUseCase<CompositeGoal> {

        override fun createGoal(goal: CompositeGoal) =
                repository.createNewCompositeGoal(goal)

        override fun deleteGoal(goal: CompositeGoal) {
            repository.deleteCompositeGoal(goal)
        }
    }

    class PresenceSingleGoalInteractor:PresenceUseCase<SingleGoal> {
        override fun createGoal(goal: SingleGoal) {
            return this.createGoal(goal)
        }

        override fun deleteGoal(goal: SingleGoal) {
            return this.deleteGoal(goal)
        }
    }

    class PresenceSubGoalInteractor:PresenceUseCase<Internable<SingleGoal>> {
        override fun createGoal(goal: Internable<SingleGoal>) {
            if (goal.compositeGoalId > 0)
                return this.createGoal(goal)
        }

        override fun deleteGoal(goal: Internable<SingleGoal>) {
            return this.deleteGoal(goal)
        }
    }
}