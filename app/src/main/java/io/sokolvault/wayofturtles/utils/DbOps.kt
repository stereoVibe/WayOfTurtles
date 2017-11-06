package io.sokolvault.wayofturtles.utils

import android.util.Log
import io.sokolvault.wayofturtles.data.db.dao.BigGoalDAO
import io.sokolvault.wayofturtles.data.db.dao.JobDAO
import io.sokolvault.wayofturtles.data.db.model.BigGoalEntity
import io.sokolvault.wayofturtles.data.db.model.JobEntity
import io.sokolvault.wayofturtles.domain.model.BigGoal
import io.sokolvault.wayofturtles.domain.model.Goal
import kotlinx.coroutines.experimental.NonCancellable.isCompleted
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.withTimeoutOrNull
import org.jetbrains.anko.coroutines.experimental.bg
import org.jetbrains.anko.doAsyncResult


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

        fun BigGoalDAO.asyncGet(bigGoalId: Int): BigGoalEntity{
            status = Status.LOADING
            var bigGoal: BigGoalEntity? = null

            val async = async(UI) {
                bg {
                    bigGoal = this@asyncGet.getBigGoalById(bigGoalId)
                    return@bg bigGoal
                }
            }
            status = Status.IDLE
            return bigGoal as BigGoalEntity
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


        private fun <I:Goal, O:Goal>baseGoalConverter(inputGoal:I, outputGoal: O): O{

            val assign: (I, O) -> Unit = { i, o -> apply {
                o.id = i.id
                o.description = i.description
                o.title = i.title
                o.mGoalCategory = i.mGoalCategory
                o.mProgress = i.mProgress
                o.isComplete = i.isComplete
            }}
            assign(inputGoal, outputGoal)
            return outputGoal
        }

        fun toEntityBigGoalConverter(inputGoal: BigGoal, outputGoal: BigGoalEntity): BigGoalEntity{
            baseGoalConverter(inputGoal, outputGoal)
            outputGoal.subGoalsList = inputGoal.subGoals
            return outputGoal
        }

        fun toBigGoalConverter(inputGoal: BigGoalEntity, outputGoal: BigGoal): BigGoal{
            baseGoalConverter(inputGoal, outputGoal)
            outputGoal.subGoals = inputGoal.subGoalsList
            return outputGoal
        }
    }

}