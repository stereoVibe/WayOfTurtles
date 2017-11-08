package io.sokolvault.wayofturtles.domain.presence

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import io.sokolvault.wayofturtles.model.complex.CompositeGoal
import io.sokolvault.wayofturtles.repositories.BigGoalRepositoryImpl
import io.sokolvault.wayofturtles.domain.model.SingleInputParameterUseCase
import io.sokolvault.wayofturtles.utils.DbOps
import io.sokolvault.wayofturtles.utils.Status
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg


class CreateNewBigGoalUseCase(private val repository: BigGoalRepositoryImpl = BigGoalRepositoryImpl())
    : SingleInputParameterUseCase<CompositeGoal> {

    override fun execute(goal: CompositeGoal, liveData: LiveData<CompositeGoal>) {
        DbOps.status = Status.LOADING
//        val bigGoal = BigGoal(5, "fromExecute")
        liveData as MutableLiveData
        async (UI){
            bg {
                liveData.postValue(goal)
            }.await()
            DbOps.status = Status.SUCCESS
        }

//        repository.createNewGoal(goal)
    }

}