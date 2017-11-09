package io.sokolvault.wayofturtles.repositories

import android.annotation.SuppressLint
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import io.sokolvault.wayofturtles.data.db.GoalsDatabase
import io.sokolvault.wayofturtles.data.db.model.CompositeGoalRoom
import io.sokolvault.wayofturtles.data.db.model.JobSubGoalRoom
import io.sokolvault.wayofturtles.data.db.model.TaskSubGoalRoom
import io.sokolvault.wayofturtles.model.CompositeGoal
import io.sokolvault.wayofturtles.model.Goal
import io.sokolvault.wayofturtles.model.xtensions.GoalCategory
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg
import org.jetbrains.anko.custom.async

class BigGoalRepositoryImpl : BigGoalRepository {
//    override fun <T : Internable<SingleGoal>> getSubGoals(): List<T> {
//        return this.getSubGoals()
//    }

    private val goalsDb : GoalsDatabase = getDbInstance()
    private val singleLiveData: LiveData<CompositeGoal> = MutableLiveData()

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

//    @SuppressLint("RestrictedApi")
    override fun createNewGoal(goal: CompositeGoal) {
        val compGoal = CompositeGoalRoom("Составная цель")
        val taskGoal = TaskSubGoalRoom(1, "Таск", 1)
        val jobGoal = JobSubGoalRoom(1, "Жоб", 10, 1)

        async (UI){
            val bigGoalDAO = bg {
                goalsDb.bigGoalDAO().insertBigGoal(compGoal)
            }.await()
//            val data2 = bg {
//                goalsDb.jobsDAO().insertJobSubGoal(jobGoal)
//            }
//            val data3 = bg {
//                goalsDb.taskDAO().insertTaskSubGoal(taskGoal)
//            }
//
//            Log.d(Goal::class.simpleName, " ${bigGoalDAO.await()} 1.. " +
//                    "+ ${data2.await()} 2... " +
//                    "+ ${data3.await()} 3... ")
        }
    }
//    override fun createNewGoal(goal: CompositeGoal) {
////        let { goalsDb is GoalsDatabase }
//        var goalId: Int = -1
//        val entity = toEntityBigGoalConverter(goal, BigGoalEntity("1", "2"))
//        var bigGoalEntity: BigGoalEntity
//        var goal: CompositeGoal? = null
//        fun getBigGoal(id: Int) = goalsDb.bigGoalDAO().getBigGoalById(id)
//        fun convert(bigGoalEntity: BigGoalEntity) = toBigGoalConverter(bigGoalEntity, CompositeGoal(1, "2"))
//
//        fun uiJobs(await: Int, await1: BigGoalEntity) {
//            goalId = await
//            goal = convert(await1)
//
//            singleLiveData as MutableLiveData<CompositeGoal>
//            singleLiveData.postValue(goal)
//        }
//
//        async (UI) {
//            val data = bg {
//                goalsDb.bigGoalDAO().insertBigGoal(entity).toInt()
//            }
//            when(data.isCompleted) { true -> DbOps.status = Status.SUCCESS }
//
//            val data2 = bg {
//                getBigGoal(goalId)
//            }
////            bigGoalEntity = data2.await()
////            uiJobs(data.await(), data2.await())
//
//            singleLiveData as MutableLiveData<CompositeGoal>
//            when(data.isCompleted && data2.isCompleted) {
//                true -> singleLiveData.setValue(goal)
//                true -> DbOps.status = Status.SUCCESS
//                else -> {
//                }
//            }
//        }.onAwait
//
//        Log.d(singleLiveData::class.simpleName, singleLiveData::getValue.toString())
//    }

    override fun updateGoal(goal: CompositeGoal): LiveData<CompositeGoal> {
        return this.updateGoal(goal)
    }

    override fun deleteGoal(goal: CompositeGoal): LiveData<CompositeGoal> {
        return this.deleteGoal(goal)
    }

    override fun getGoalById(id: Int): LiveData<CompositeGoal> {
//        return checkNotNull(goalsDb.bigGoalDAO().asyncGet(id))
        return MutableLiveData<CompositeGoal>()
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

}