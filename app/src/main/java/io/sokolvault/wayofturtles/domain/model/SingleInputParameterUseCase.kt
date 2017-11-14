package io.sokolvault.wayofturtles.domain.model

import android.arch.lifecycle.LiveData


interface SingleInputParameterUseCase<P> {
    fun execute(goal: P, liveData: LiveData<P>)
}