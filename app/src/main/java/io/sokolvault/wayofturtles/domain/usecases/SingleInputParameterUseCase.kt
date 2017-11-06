package io.sokolvault.wayofturtles.domain.usecases

import android.arch.lifecycle.LiveData
import io.sokolvault.wayofturtles.R
import io.sokolvault.wayofturtles.data.Resource
import io.sokolvault.wayofturtles.domain.model.BigGoal


interface SingleInputParameterUseCase<P> {

//    interface Callback<in P>{
//
//        fun onSuccess(returned: P)
//        fun onError(throwable: Throwable)
//    }

//    fun execute(goal: P, liveData: LiveData<P>, setListener: (P) -> P)

    fun execute(goal: P, liveData: LiveData<P>)
//    fun setListener(liveData: LiveData<P>)
    fun execute(goal: BigGoal, liveData: LiveData<BigGoal>)
}