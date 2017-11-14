package io.sokolvault.wayofturtles.domain.presence

import android.arch.lifecycle.LiveData
import io.sokolvault.wayofturtles.domain.model.PresenceUseCase
import io.sokolvault.wayofturtles.model.CompositeGoal
import io.sokolvault.wayofturtles.model.Internable
import io.sokolvault.wayofturtles.model.SingleGoal
import io.sokolvault.wayofturtles.repositories.presence.PresenceRepositoryContract

class PresenceInteractor {

    class UseCaseForCompositeGoal(private val repository: PresenceRepositoryContract) : PresenceUseCase<CompositeGoal> {

        override fun createGoal(goal: CompositeGoal) =
                repository.createNewCompositeGoal(goal)

        override fun deleteGoal(goal: CompositeGoal) {
            return this.deleteGoal(goal)
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