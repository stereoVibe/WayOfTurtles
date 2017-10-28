package io.sokolvault.wayofturtles.Utils

import io.sokolvault.wayofturtles.db.BigGoalDAO
import io.sokolvault.wayofturtles.db.JobDAO
import io.sokolvault.wayofturtles.model.BigGoal
import io.sokolvault.wayofturtles.model.Job
import io.sokolvault.wayofturtles.model.SubGoal

class DbOps {

    companion object {
         fun insertBigGoal(LOCK: Any, bigGoal: BigGoal, bigGoalDAO: BigGoalDAO){
             Thread {
                 synchronized(LOCK) {
                        bigGoalDAO.insertBigGoal(bigGoal)
//                    TODO: This block below of Java concurrent way should be replaced by concurrent Kotlin library
                        (LOCK as java.lang.Object).notify()
                 }
             }.start()
         }

        fun insertSubGoal(LOCK: Any, subGoal: Job, subGoalDAO: JobDAO){
            Thread {
                synchronized(LOCK) {
                    subGoalDAO.insertSubGoal(subGoal)
//                    TODO: This block below of Java concurrent way should be replaced by concurrent Kotlin library
                    (LOCK as java.lang.Object).notify()
                }
            }.start()
        }

//        fun insertBulkSubGoals(LOCK: Any, subGoals: List<Job>, subGoalDAO: JobDAO){
//            Thread {
//                synchronized(LOCK) {
//                    subGoalDAO.insertSubGoals(subGoals)
////                    TODO: This block below of Java concurrent way should be replaced by concurrent Kotlin library
//                    (LOCK as java.lang.Object).notify()
//                }
//            }.start()
//        }
    }

}