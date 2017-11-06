package io.sokolvault.wayofturtles.ui

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.content.Context
import io.sokolvault.wayofturtles.RequestToUseCaseListener
import io.sokolvault.wayofturtles.data.Resource
import io.sokolvault.wayofturtles.domain.model.Goal
import io.sokolvault.wayofturtles.domain.repository.RepositoryContract

abstract class GoalViewModel<D:Goal> :
        ViewModel(), RepositoryContract<D, D>{

    abstract var goalsList: LiveData<Resource<List<D>>>
    val objectResult: LiveData<D> = MutableLiveData<D>()
    val listener: RequestToUseCaseListener? = null

}