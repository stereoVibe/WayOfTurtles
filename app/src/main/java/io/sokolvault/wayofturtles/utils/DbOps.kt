package io.sokolvault.wayofturtles.utils

import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.util.Log
import io.sokolvault.wayofturtles.db.BigGoalDAO
import io.sokolvault.wayofturtles.db.JobDAO
import io.sokolvault.wayofturtles.db.model.BigGoalEntity
import io.sokolvault.wayofturtles.db.model.JobEntity
import io.sokolvault.wayofturtles.model.AbstractGoal
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg


class DbOps {

    companion object {
        fun BigGoalDAO.asyncInsert(bigGoalEntity: BigGoalEntity): Int {
            var bigID: Long = 0L
            async(UI) {
                bg {
                    bigID = this@asyncInsert.insertBigGoal(bigGoalEntity)
                    Log.d(this.toString(), "Большая цель в базе!")
                }
            }
            return bigID.toInt()
        }

        fun JobDAO.asyncSubGoalInsert(jobEntitySubGoal: JobEntity){
            async(UI) {
                bg {
                    this@asyncSubGoalInsert.insertJobSubGoal(jobEntitySubGoal)
                    Log.d(this.toString(), "Подцель в базе!")
                }
            }
        }
    }

}