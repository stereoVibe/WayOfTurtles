package io.sokolvault.wayofturtles.domain.presence

import io.sokolvault.wayofturtles.model.HybridGoal
import io.sokolvault.wayofturtles.model.xtensions.Internable
import io.sokolvault.wayofturtles.model.MonotypeGoal
import io.sokolvault.wayofturtles.repositories.presence.PresenceRepositoryData

class PresenceInteractor {

    class UseCaseForCompositeGoal(private val repository: PresenceRepositoryData) : PresenceUseCase<HybridGoal> {

        override fun createGoal(goal: HybridGoal) =
                repository.createNewCompositeGoal(goal)

        override fun deleteGoal(goal: HybridGoal) {
            repository.deleteCompositeGoal(goal)
        }
    }

    class PresenceSingleGoalInteractor: PresenceUseCase<MonotypeGoal> {
        override fun createGoal(goal: MonotypeGoal) {
        }

        override fun deleteGoal(goal: MonotypeGoal) {
        }
    }

    class PresenceSubGoalInteractor: PresenceUseCase<Internable<MonotypeGoal>> {
        override fun createGoal(goal: Internable<MonotypeGoal>) {
            if (goal.hybridGoalId > 0)
                return this.createGoal(goal)
        }

        override fun deleteGoal(goal: Internable<MonotypeGoal>) {
        }
    }
}