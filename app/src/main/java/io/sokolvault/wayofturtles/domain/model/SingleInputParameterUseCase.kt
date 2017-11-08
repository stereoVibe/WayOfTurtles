package io.sokolvault.wayofturtles.domain.model

import android.arch.lifecycle.LiveData


interface SingleInputParameterUseCase<P> {

//    interface Callback<in P>{
//
//        fun onSuccess(returned: P)
//        fun onError(throwable: Throwable)
//    }

//    fun execute(goal: P, liveData: LiveData<P>, setListener: (P) -> P)
    fun execute(goal: P, liveData: LiveData<P>)
//    fun setListener(liveData: LiveData<P>)
//    fun execute(goal: BigGoal, liveData: LiveData<BigGoal>)
}