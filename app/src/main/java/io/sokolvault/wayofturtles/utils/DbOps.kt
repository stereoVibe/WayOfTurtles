package io.sokolvault.wayofturtles.utils

import android.arch.lifecycle.LiveData
import android.util.Log
import io.sokolvault.wayofturtles.data.db.dao.BigGoalDAO
import io.sokolvault.wayofturtles.data.db.dao.JobDAO
import io.sokolvault.wayofturtles.data.db.model.BigGoalEntity
import io.sokolvault.wayofturtles.data.db.model.JobEntity
import io.sokolvault.wayofturtles.model.complex.CompositeGoal
import io.sokolvault.wayofturtles.dto.DataBigGoal
import io.sokolvault.wayofturtles.model.base.Goal
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg


class DbOps {

    companion object {
        var status = Status.IDLE

        private fun setSuccess(){
            status = Status.SUCCESS
        }

        fun BigGoalDAO.asyncInsert(bigGoalEntity: BigGoalEntity): Int {
            status = Status.LOADING
            var bigID: Long = 0L
            async(UI) {
                bg {
                    bigID = this@asyncInsert.insertBigGoal(bigGoalEntity)
                    Log.d(this.toString(), "Большая цель в базе!")
                }
            }
            if (bigID.toInt() > 0) {
                status = Status.SUCCESS
                return bigID.toInt()
            }
            else status = Status.ERROR

            status = Status.IDLE
            return -1
        }

        fun BigGoalDAO.asyncGet(bigGoalId: Int): LiveData<CompositeGoal>? {
            var compositeGoal: LiveData<CompositeGoal>? = null

            async(UI) {
                val data = bg {
                    status = Status.LOADING
                    this@asyncGet.getBigGoalById(bigGoalId)
                }
                compositeGoal = data.await()
            }
            status = Status.IDLE
            return compositeGoal
        }

        fun JobDAO.asyncSubGoalInsert(jobEntitySubGoal: JobEntity){
            async(UI) {
                bg {
                    this@asyncSubGoalInsert.insertJobSubGoal(jobEntitySubGoal)
                    Log.d(this.toString(), "Подцель в базе!")
                }
            }
        }

        fun checkStatus(): Status {
            return status
        }


        private fun <I: Goal, O: Goal>baseGoalConverter(inputGoal:I, outputGoal: O): O{

            val assign: (I, O) -> Unit = { i, o -> apply {
                o.id = i.id
                o.description = i.description
                o.title = i.title
                o.goalCategory = i.goalCategory
                o.progress = i.progress
                o.isComplete = i.isComplete
            }}
            assign(inputGoal, outputGoal)
            return outputGoal
        }

        fun toEntityBigGoalConverter(inputGoal: DataBigGoal, outputGoal: BigGoalEntity): BigGoalEntity{
            baseGoalConverter(inputGoal, outputGoal)
//            outputGoal.subGoalsList = inputGoal.subGoals
            return outputGoal
        }

        fun toBigGoalConverter(inputGoal: BigGoalEntity, outputGoal: DataBigGoal): CompositeGoal {
            baseGoalConverter(inputGoal, outputGoal)
//            outputGoal.subGoalsSet = inputGoal.subGoalsList
            return outputGoal
        }
    }

}