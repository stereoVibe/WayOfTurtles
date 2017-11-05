package io.sokolvault.wayofturtles.domain.interactors

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import io.sokolvault.wayofturtles.data.Resource
import io.sokolvault.wayofturtles.domain.model.BigGoal
import io.sokolvault.wayofturtles.domain.repository.BigGoalRepositoryImpl
import io.sokolvault.wayofturtles.domain.usecases.SingleInputParameterUseCase


class CreateNewBigGoalUseCase(private val repository: BigGoalRepositoryImpl = BigGoalRepositoryImpl())
    : SingleInputParameterUseCase<BigGoal> {

    override fun execute(goal: BigGoal, liveData: LiveData<Resource<BigGoal>>) {
        liveData as MutableLiveData

        repository.createNewGoal(goal)
    }

}