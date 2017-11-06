package io.sokolvault.wayofturtles.domain.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import io.sokolvault.wayofturtles.AbsentLiveData
import io.sokolvault.wayofturtles.data.db.GoalsDatabase
import io.sokolvault.wayofturtles.data.db.model.BigGoalEntity
import io.sokolvault.wayofturtles.domain.model.BigGoal
import io.sokolvault.wayofturtles.domain.model.GoalCategory
import io.sokolvault.wayofturtles.domain.model.SubGoal
import io.sokolvault.wayofturtles.utils.DbOps
import io.sokolvault.wayofturtles.utils.DbOps.Companion.toEntityBigGoalConverter
import io.sokolvault.wayofturtles.utils.DbOps.Companion.toBigGoalConverter
import io.sokolvault.wayofturtles.utils.Status
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.runBlocking
import org.jetbrains.anko.coroutines.experimental.bg

class BigGoalRepositoryImpl : BigGoalRepository {

    private val goalsDb : GoalsDatabase = getDbInstance()
    private val singleLiveData: LiveData<BigGoal> = AbsentLiveData.create()

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

    override fun createNewGoal(goal: BigGoal) {
//        let { goalsDb is GoalsDatabase }
        var goalId: Int = -1
        val entity = toEntityBigGoalConverter(goal, BigGoalEntity("1", "2"))
        var bigGoalEntity: BigGoalEntity
        var goal: BigGoal? = null
        fun getBigGoal(id: Int) = goalsDb.bigGoalDAO().getBigGoalById(id)
        fun convert(bigGoalEntity: BigGoalEntity) = toBigGoalConverter(bigGoalEntity, BigGoal(1, "2"))

        fun uiJobs(await: Int, await1: BigGoalEntity) {
            goalId = await
            goal = convert(await1)

            singleLiveData as MutableLiveData<BigGoal>
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
            uiJobs(data.await(), data2.await())

            singleLiveData as MutableLiveData<BigGoal>
            when(data.isCompleted && data2.isCompleted) {
                true -> singleLiveData.setValue(goal)
                true -> DbOps.status = Status.SUCCESS
                else -> {
                }
            }
        }.onAwait

        Log.d(singleLiveData::class.simpleName, singleLiveData::getValue.toString())
    }

    override fun updateGoal(goal: BigGoal): LiveData<BigGoal> {
        return this.updateGoal(goal)
    }

    override fun deleteGoal(goal: BigGoal): LiveData<BigGoal> {
        return this.deleteGoal(goal)
    }

    override fun getGoalById(id: Int): LiveData<BigGoal> {
        return this.getGoalById(id)
    }

    override fun getGoalsFilteredByCategoryTag(category: GoalCategory): Set<LiveData<BigGoal>> {
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