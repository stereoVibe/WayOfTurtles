package io.sokolvault.wayofturtles.ui

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import io.sokolvault.wayofturtles.data.Resource
import io.sokolvault.wayofturtles.domain.presence.PresenceUseCase
import io.sokolvault.wayofturtles.domain.presence.PresenceInteractor
import io.sokolvault.wayofturtles.model.CompositeGoal
import io.sokolvault.wayofturtles.repositories.presence.PresenceRepositoryData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BigGoalViewModel
@Inject constructor(private val repository: PresenceRepositoryData) : GoalViewModel<CompositeGoal>(),
        PresenceUseCase<CompositeGoal> {

    var singleGoal: LiveData<CompositeGoal> = MutableLiveData<CompositeGoal>()
    override lateinit var goalsList: LiveData<Resource<List<CompositeGoal>>>

    override fun createGoal(goal: CompositeGoal) {

        Log.d("ViewModel Repo", repository.toString())

        PresenceInteractor
                .UseCaseForCompositeGoal(repository)
                .createGoal(goal)
    }

    override fun deleteGoal(goal: CompositeGoal) {
        PresenceInteractor
                .UseCaseForCompositeGoal(repository)
                .deleteGoal(goal)
    }


}