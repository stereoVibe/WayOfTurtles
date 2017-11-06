package io.sokolvault.wayofturtles.domain.interactors

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import io.sokolvault.wayofturtles.AbsentLiveData
import io.sokolvault.wayofturtles.data.Resource
import io.sokolvault.wayofturtles.data.Resource.Companion.success
import io.sokolvault.wayofturtles.domain.model.BigGoal
import io.sokolvault.wayofturtles.domain.repository.BigGoalRepositoryImpl
import io.sokolvault.wayofturtles.domain.usecases.SingleInputParameterUseCase
import io.sokolvault.wayofturtles.utils.DbOps
import io.sokolvault.wayofturtles.utils.Status
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg


class CreateNewBigGoalUseCase(private val repository: BigGoalRepositoryImpl = BigGoalRepositoryImpl())
    : SingleInputParameterUseCase<BigGoal> {

    override fun execute(goal: BigGoal, liveData: LiveData<BigGoal>) {
        DbOps.status = Status.LOADING
        val bigGoal = BigGoal(5, "fromExecute")
        liveData as MutableLiveData
        async (UI){
            bg {
                liveData.postValue(bigGoal)
            }.await()
            DbOps.status = Status.SUCCESS
        }

//        repository.createNewGoal(goal)
    }

}