package io.sokolvault.wayofturtles.repositories

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import io.sokolvault.wayofturtles.AbsentLiveData
import io.sokolvault.wayofturtles.data.db.GoalsDatabase
import io.sokolvault.wayofturtles.data.db.model.BigGoalEntity
import io.sokolvault.wayofturtles.model.complex.CompositeGoal
import io.sokolvault.wayofturtles.model.xtensions.GoalCategory
import io.sokolvault.wayofturtles.model.complex.SubGoal
import io.sokolvault.wayofturtles.utils.DbOps
import io.sokolvault.wayofturtles.utils.DbOps.Companion.toEntityBigGoalConverter
import io.sokolvault.wayofturtles.utils.DbOps.Companion.toBigGoalConverter
import io.sokolvault.wayofturtles.utils.DbOps.Companion.asyncGet
import io.sokolvault.wayofturtles.utils.Status
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class BigGoalRepositoryImpl : BigGoalRepository {

    private val goalsDb : GoalsDatabase = getDbInstance()
    private val singleLiveData: LiveData<CompositeGoal> = AbsentLiveData.create()

    companion object {
        private lateinit var goalsDb: GoalsDatabase

        fun injectDbInstance(dbInstance: GoalsDatabase){
            goalsDb = dbInstance
        }

        fun getDbInstance(): GoalsDatabase{
            checkNotNull(goalsDb)
            return this.goalsDb
        }
    }

    override fun createNewGoal(goal: CompositeGoal) {
//        let { goalsDb is GoalsDatabase }
        var goalId: Int = -1
        val entity = toEntityBigGoalConverter(goal, BigGoalEntity("1", "2"))
        var bigGoalEntity: BigGoalEntity
        var goal: CompositeGoal? = null
        fun getBigGoal(id: Int) = goalsDb.bigGoalDAO().getBigGoalById(id)
        fun convert(bigGoalEntity: BigGoalEntity) = toBigGoalConverter(bigGoalEntity, CompositeGoal(1, "2"))

        fun uiJobs(await: Int, await1: BigGoalEntity) {
            goalId = await
            goal = convert(await1)

            singleLiveData as MutableLiveData<CompositeGoal>
            singleLiveData.postValue(goal)
        }

        async (UI) {
            val data = bg {
                goalsDb.bigGoalDAO().insertBigGoal(entity).toInt()
            }
            when(data.isCompleted) { true -> DbOps.status = Status.SUCCESS }

            val data2 = bg {
                getBigGoal(goalId)
            }
//            bigGoalEntity = data2.await()
//            uiJobs(data.await(), data2.await())

            singleLiveData as MutableLiveData<CompositeGoal>
            when(data.isCompleted && data2.isCompleted) {
                true -> singleLiveData.setValue(goal)
                true -> DbOps.status = Status.SUCCESS
                else -> {
                }
            }
        }.onAwait

        Log.d(singleLiveData::class.simpleName, singleLiveData::getValue.toString())
    }

    override fun updateGoal(goal: CompositeGoal): LiveData<CompositeGoal> {
        return this.updateGoal(goal)
    }

    override fun deleteGoal(goal: CompositeGoal): LiveData<CompositeGoal> {
        return this.deleteGoal(goal)
    }

    override fun getGoalById(id: Int): LiveData<CompositeGoal> {
        return checkNotNull(goalsDb.bigGoalDAO().asyncGet(id))
    }

    override fun getGoalsFilteredByCategoryTag(category: GoalCategory): Set<LiveData<CompositeGoal>> {
        return this.getGoalsFilteredByCategoryTag(category)
    }

    override fun addCategoryTagToGoal(category: GoalCategory) {
        return this.addCategoryTagToGoal(category)
    }

    override fun setCompleteStatus(status: Boolean) {
        return this.setCompleteStatus(status)
    }

    override fun setProgress(progress: Int) {
        return this.setProgress(progress)
    }

    override fun <T : SubGoal> getSubGoals(): List<T> {
        return this.getSubGoals()
    }



}