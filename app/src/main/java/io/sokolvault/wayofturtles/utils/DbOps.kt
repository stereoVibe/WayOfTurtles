package io.sokolvault.wayofturtles.utils

import io.sokolvault.wayofturtles.db.BigGoalDAO
import io.sokolvault.wayofturtles.db.JobDAO
import io.sokolvault.wayofturtles.model.BigGoal
import io.sokolvault.wayofturtles.model.Job
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg


class DbOps {

    companion object {
        fun BigGoalDAO.asyncInsert(bigGoal: BigGoal) {
            async(UI) {
                bg {
                    this@asyncInsert.insertBigGoal(bigGoal)
                }
            }
        }

        fun JobDAO.asyncSubGoalInsert(jobSubGoal: Job){
            async(UI) {
                bg {
                    this@asyncSubGoalInsert.insertJobSubGoal(jobSubGoal)
                }
            }
        }
    }
}