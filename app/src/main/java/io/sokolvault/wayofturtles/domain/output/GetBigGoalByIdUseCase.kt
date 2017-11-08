package io.sokolvault.wayofturtles.domain.output

import android.arch.lifecycle.LiveData
import io.sokolvault.wayofturtles.model.complex.CompositeGoal
import io.sokolvault.wayofturtles.repositories.BigGoalRepositoryImpl
import io.sokolvault.wayofturtles.domain.model.GetByIdUseCase


class GetBigGoalByIdUseCase(private val repository: BigGoalRepositoryImpl = BigGoalRepositoryImpl())
    :GetByIdUseCase<CompositeGoal> {

    override fun execute(id: Int): LiveData<CompositeGoal> {
        return repository.getGoalById(id)
    }
}