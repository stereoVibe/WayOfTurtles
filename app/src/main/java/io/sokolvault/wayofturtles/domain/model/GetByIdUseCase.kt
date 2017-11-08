package io.sokolvault.wayofturtles.domain.model

import android.arch.lifecycle.LiveData
import io.sokolvault.wayofturtles.model.base.Goal

interface GetByIdUseCase<P: Goal> {
    fun execute(id: Int): LiveData<P>
}